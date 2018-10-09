package be.vdab.retrovideo.repositories;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import be.vdab.retrovideo.entities.Reservatie;

@Repository
public class JdbcReservatieRepository implements ReservatieRepository {
	
	private final SimpleJdbcInsert insert; 

	JdbcReservatieRepository(JdbcTemplate template) {
		this.insert=new SimpleJdbcInsert(template);
		insert.withTableName("reservaties");
	}
	
	@Override 
	public void create(Reservatie reservatie) {
		Map<String, Object> kolomWaarden = new HashMap<>(); 
		kolomWaarden.put("klantId", reservatie.getKlantId()); 
		kolomWaarden.put("filmId", reservatie.getFilmId());
		kolomWaarden.put("reservatie", reservatie.getReservatie());
		insert.execute(kolomWaarden);
	}
}
