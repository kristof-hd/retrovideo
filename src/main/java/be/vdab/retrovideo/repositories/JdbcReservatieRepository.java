package be.vdab.retrovideo.repositories;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import be.vdab.retrovideo.entities.Reservatie;

@Repository
public class JdbcReservatieRepository implements ReservatieRepository {
	private final JdbcTemplate template;
	private final SimpleJdbcInsert insert; 

	JdbcReservatieRepository(JdbcTemplate template) {
		this.template = template;
		this.insert=new SimpleJdbcInsert(template);
		insert.withTableName("reservaties");
	}
	
	@Override 
	public void create(Reservatie reservatie) {
		Map<String, Object> kolomWaarden = new HashMap<>(); 
		kolomWaarden.put("klantid", reservatie.getKlantId()); 
		kolomWaarden.put("filmid", reservatie.getFilmId());
		kolomWaarden.put("reservatie", LocalDateTime.now());
		insert.executeAndReturnKey(kolomWaarden); 
	}
}
