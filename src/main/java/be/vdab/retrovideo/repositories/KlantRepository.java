package be.vdab.retrovideo.repositories;

import java.util.List;
import java.util.Optional;

import be.vdab.retrovideo.entities.Klant;

public interface KlantRepository {
	Optional<Klant> readKlant(long id); 
	List<Klant> findByFamilienaamBevat(String familienaamBevat);  
}
