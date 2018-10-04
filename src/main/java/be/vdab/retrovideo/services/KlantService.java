package be.vdab.retrovideo.services;

import java.util.List;
import java.util.Optional;

import be.vdab.retrovideo.entities.Klant;

public interface KlantService {

	Optional<Klant> readKlant(long id); 
	List<Klant> findByFamilienaamBevat(String familienaamBevat); 
}
