package be.vdab.retrovideo.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

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

import be.vdab.retrovideo.entities.Klant;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(JdbcKlantRepository.class)
@Sql("/insertKlant.sql")
public class JdbcKlantRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private JdbcKlantRepository repository;

	private long idVanTestKlant() {
		return super.jdbcTemplate.queryForObject("select id from klanten where familienaam='Janssen'", Long.class);
	}

	@Test
	public void read() {
		assertEquals("Janssen", repository.readKlant(idVanTestKlant()).get().getFamilienaam());
	}

	@Test
	public void readOnbestaandeKlant() {
		assertFalse(repository.readKlant(-1).isPresent());
	}

	@Test
	public void findByFamilienaamBevat() {
		List<Klant> klanten = repository.findByFamilienaamBevat("co");
		String vorigeNaam = "";
		for (Klant klant : klanten) {
			assertTrue(klant.getFamilienaam().toLowerCase().contains("co"));
			assertTrue(vorigeNaam.compareToIgnoreCase(klant.getFamilienaam()) <= 0);
			vorigeNaam = klant.getFamilienaam();
		}
		long aantalKlanten = super.jdbcTemplate.queryForObject(
				"select count(*) from klanten where familienaam like '%co%' order by familienaam", Long.class);
		assertEquals(aantalKlanten, klanten.size());
	}

}
