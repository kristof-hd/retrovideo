package be.vdab.retrovideo.repositories;

import java.util.List;
import java.util.Optional;

import be.vdab.retrovideo.entities.Film;
import be.vdab.retrovideo.entities.Genre;

public interface FilmRepository {
	List<Genre> findGenres();
	List<Film> findFilmsByGenre(long id); 
	Optional<Film> read(long id); 
}
