package be.vdab.retrovideo.web;

import java.util.Set;

public interface Mandje {

	void addFilmId(long filmId);
	void verwijderFilmId(long filmId);
	void verwijderFilmIds(long[] filmIds);
	Set<Long> getFilmIds(); 
	int telAantalArtikelsInMandje();
	boolean bevat(long filmId);
	
}
