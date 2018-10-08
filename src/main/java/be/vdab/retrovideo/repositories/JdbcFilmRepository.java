package be.vdab.retrovideo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import be.vdab.retrovideo.entities.Film;
import be.vdab.retrovideo.exceptions.FilmNietGevondenException;

@Repository
public class JdbcFilmRepository implements FilmRepository {

	private final JdbcTemplate template;
	private static final String SELECT_FILMS_BY_GENRE="select id, titel, voorraad, gereserveerd, prijs from films where genreid=?"; 
	private static final String READ = "select id, titel, voorraad, gereserveerd, prijs from films where id=?"; 
	private static final String UPDATE_FILM="update films set gereserveerd=gereserveerd+1 where id=?"; 
	private final RowMapper<Film> filmRowMapper=(resultSet, rowNum) -> new Film(resultSet.getLong("id"), resultSet.getString("titel"), resultSet.getInt("voorraad"), resultSet.getInt("gereserveerd"), resultSet.getBigDecimal("prijs"));

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
			return Optional.of(template.queryForObject(READ,  filmRowMapper, id));
		}
		catch (IncorrectResultSizeDataAccessException ex) {
			return Optional.empty(); 
		}
	}
	
	@Override
	public void update(Film film) {
		if (template.update(UPDATE_FILM, film.getId())==0) {
			throw new FilmNietGevondenException();
			} 
	}
	
}