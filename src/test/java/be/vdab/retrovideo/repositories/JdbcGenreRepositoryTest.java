package be.vdab.retrovideo.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

import be.vdab.retrovideo.entities.Genre;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Import(JdbcGenreRepository.class)
@Sql("/insertGenre.sql")

public class JdbcGenreRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
		private static final String GENRES="genres";
		@Autowired
		private JdbcGenreRepository repository;
		
		@Test
		public void findGenres() {
			List<Genre> genres = repository.findGenres();
			assertEquals(super.countRowsInTable(GENRES), genres.size());
			String vorigeNaam="";
			for (Genre genre: genres) {
				assertTrue(genre.getNaam().compareToIgnoreCase(vorigeNaam)>=0);
				vorigeNaam=genre.getNaam(); 
			}
		}

}
