package be.vdab.retrovideo.services;

import java.util.List;

import be.vdab.retrovideo.entities.Film;
import be.vdab.retrovideo.entities.Genre;

public interface FilmService {

	List<Genre> findGenres(); 
	List<Film> findFilmsByGenre(long id);	
	
}
