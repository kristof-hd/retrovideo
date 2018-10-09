package be.vdab.retrovideo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import be.vdab.retrovideo.entities.Klant;

@Repository
class JdbcKlantRepository implements KlantRepository {
	private final JdbcTemplate template;
	
	JdbcKlantRepository(JdbcTemplate template) {
		this.template=template; 
	}
	
	private static final String SELECT_BY_FAMILIENAAM_BEVAT="select id, familienaam, voornaam, straatNummer, postcode, gemeente from klanten where familienaam like ? order by familienaam";
	private static final String READ="select id, familienaam, voornaam, straatNummer, postcode, gemeente from klanten where id=?"; 
	private final RowMapper<Klant> klantRowMapper = (resultSet, rowNum) -> new Klant(resultSet.getLong("id"), resultSet.getString("familienaam"), resultSet.getString("voornaam"), resultSet.getString("straatNummer"), resultSet.getString("postcode"), resultSet.getString("gemeente")); 

	@Override
	public Optional<Klant> readKlant(long id) {
		try {return Optional.of(template.queryForObject(READ, klantRowMapper, id));}
		catch (IncorrectResultSizeDataAccessException ex) {
			return Optional.empty(); 
		}
	}

	@Override
	public List<Klant> findByFamilienaamBevat(String familienaamBevat) {
		return template.query(SELECT_BY_FAMILIENAAM_BEVAT, klantRowMapper, '%'+familienaamBevat+'%'); 
	}
}
