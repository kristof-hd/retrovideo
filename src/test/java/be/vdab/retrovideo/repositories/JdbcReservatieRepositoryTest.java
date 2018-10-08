package be.vdab.retrovideo.repositories;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

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

import be.vdab.retrovideo.entities.Reservatie; 

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(JdbcReservatieRepository.class)
@Sql("/insertReservatie.sql")
public class JdbcReservatieRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	private static final String RESERVATIES = "reservaties"; 

	@Autowired private JdbcReservatieRepository repository;

	@Test
	public void create() {
	int aantalReservaties=super.countRowsInTable(RESERVATIES); 
	Reservatie reservatie = new Reservatie(1, 2, LocalDateTime.now()); 
	repository.create(reservatie); 
	assertEquals(aantalReservaties+1, this.countRowsInTable(RESERVATIES));  
	}

}
