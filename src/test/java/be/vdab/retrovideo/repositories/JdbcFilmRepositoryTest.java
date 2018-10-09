package be.vdab.retrovideo.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.retrovideo.entities.Film;
import be.vdab.retrovideo.exceptions.FilmNietGevondenException;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(JdbcFilmRepository.class)
@Sql("/insertFilm.sql")
public class JdbcFilmRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

	private static final String FILMS = "films";
	@Autowired
	private JdbcFilmRepository repository;

	private long idVanTestFilm() {
		return super.jdbcTemplate.queryForObject("select id from films where titel='test'", Long.class);
	}

	@Test
	public void findFilmsByGenre() {
		List<Film> films = repository.findFilmsByGenre(1);
		String vorigeTitel="";
		for (Film film : films) {
			assertEquals(1, film.getGenreId());
			assertTrue(vorigeTitel.compareTo(film.getTitel())<=0);
			vorigeTitel=film.getTitel(); 
		}
		assertEquals(super.countRowsInTableWhere(FILMS, "genreid=1"), films.size());
	}

	@Test
	public void read() {
		assertEquals("test", repository.read(idVanTestFilm()).get().getTitel());
	}

	@Test
	public void readOnbestaandeFilm() {
		assertFalse(repository.read(-1).isPresent());
	}

	@Test
	public void update() {
		long id = idVanTestFilm();
		Film film = new Film(id, 1, "test", 1, 0, BigDecimal.valueOf(4));
		repository.update(film);
		assertEquals(Long.valueOf(1L), super.jdbcTemplate.queryForObject("select gereserveerd from films where id=?", Long.class, id));
	}

	@Test(expected = FilmNietGevondenException.class)
	public void updateOnbestaandeFilm() {
		repository.update(new Film(-1, 1, "test", 1, 0, BigDecimal.valueOf(4)));
	}

}
