package be.vdab.retrovideo.repositories;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import be.vdab.retrovideo.entities.Film;
import be.vdab.retrovideo.entities.Genre;

@Repository
public class JdbcFilmRepository implements FilmRepository {
	private final JdbcTemplate template;
	private static final String SELECT_ALL_GENRES="select id, naam from genres order by naam";
	private static final String SELECT_FILMS_BY_GENRE="select id, titel from films where genreid=?"; 
	private final RowMapper<Genre> genreRowMapper=(resultSet, rowNum) -> new Genre(resultSet.getLong("id"), resultSet.getString("naam")); 
	private final RowMapper<Film> filmRowMapper=(resultSet, rowNum) -> new Film(resultSet.getLong("id"), resultSet.getString("titel"));

	JdbcFilmRepository(JdbcTemplate template) {
		this.template=template; 
	}
	
	@Override
	public List<Genre> findGenres() {
		
		return template.query(SELECT_ALL_GENRES, genreRowMapper); 
		
	}

	@Override
	public List<Film> findFilmsByGenre(long id) {
		return template.query(SELECT_FILMS_BY_GENRE, filmRowMapper, id); 
	} 
	
}
