package be.vdab.retrovideo.repositories;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import be.vdab.retrovideo.entities.Klant;

@Repository
public class JdbcKlantRepository implements KlantRepository {
	private final JdbcTemplate template;
	
	JdbcKlantRepository(JdbcTemplate template) {
		this.template=template; 
	}
	
	private static final String SELECT_BY_FAMILIENAAM_BEVAT="select id, familienaam from klanten where familienaam like ?";
	private final RowMapper<Klant> klantRowMapper = (resultSet, rowNum) -> new Klant(resultSet.getLong("id"), resultSet.getString("familienaam")); 
	
	@Override
	public List<Klant> findByFamilienaamBevat(String familienaamBevat) {
		return template.query(SELECT_BY_FAMILIENAAM_BEVAT, klantRowMapper, '%'+familienaamBevat+'%'); 
	}
}
