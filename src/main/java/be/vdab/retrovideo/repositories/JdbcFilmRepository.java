package be.vdab.retrovideo.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import be.vdab.retrovideo.entities.Film;
import be.vdab.retrovideo.exceptions.ReservatieException;

@Repository
class JdbcFilmRepository implements FilmRepository {

	private final JdbcTemplate template;
	private static final String SELECT_FILMS_BY_GENRE="select id, genreid, titel, voorraad, gereserveerd, prijs from films where genreid=? order by titel"; 
	private static final String READ = "select id, genreid, titel, voorraad, gereserveerd, prijs from films where id=?"; 
	private static final String BEGIN_READ_FILMS_IN_MANDJE = "select id, genreid, titel, voorraad, gereserveerd, prijs from films where id in (";	
	private static final String UPDATE_FILM="update films set gereserveerd=gereserveerd+1 where id=? and voorraad>gereserveerd";	
	private final RowMapper<Film> filmRowMapper=(resultSet, rowNum) -> new Film(resultSet.getLong("id"), resultSet.getLong("genreid"), resultSet.getString("titel"), resultSet.getInt("voorraad"), resultSet.getInt("gereserveerd"), resultSet.getBigDecimal("prijs"));

	JdbcFilmRepository(JdbcTemplate template) {
		this.template=template; 
	}
	
	@Override
	public List<Film> findFilmsByGenre(long id) {
		return template.query(SELECT_FILMS_BY_GENRE, filmRowMapper, id); 
	} 
	
	@Override
	public Optional<Film> read(long id) {
		try {
			return Optional.of(template.queryForObject(READ, filmRowMapper, id));
		}
		catch (IncorrectResultSizeDataAccessException ex) {
			return Optional.empty(); 
		}
	}

	@Override
	public void update(long id) {
		if (template.update(UPDATE_FILM, id) == 0) {
			throw new ReservatieException(); 
		}
	}

	@Override
	@SuppressWarnings("unused")
	public List<Film> readFilmsInMandje(Set<Long> ids) {
		StringBuilder readFilmsInMandje = new StringBuilder(BEGIN_READ_FILMS_IN_MANDJE);  
		for (long id: ids) {
			readFilmsInMandje.append("?,");
		}
		readFilmsInMandje.setCharAt(readFilmsInMandje.length()-1, ')');
		return template.query(readFilmsInMandje.toString(), ids.toArray(), filmRowMapper);
	}		
	
}