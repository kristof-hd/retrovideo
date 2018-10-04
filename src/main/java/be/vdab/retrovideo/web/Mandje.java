package be.vdab.retrovideo.web;

import java.util.List;

public interface Mandje {

	void addFilmId(long filmId);
	List<Long> getFilmIds(); 
	
}
