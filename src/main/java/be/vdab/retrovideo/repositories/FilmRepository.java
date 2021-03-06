package be.vdab.retrovideo.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import be.vdab.retrovideo.entities.Film;

public interface FilmRepository {
	List<Film> findFilmsByGenre(long id); 
	Optional<Film> read(long id); 
	void update(long id); 
	List<Film> readFilmsInMandje(Set<Long> ids); 
}
