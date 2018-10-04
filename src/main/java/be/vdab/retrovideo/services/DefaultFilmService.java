package be.vdab.retrovideo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.retrovideo.entities.Film;
import be.vdab.retrovideo.entities.Genre;
import be.vdab.retrovideo.repositories.FilmRepository;

@Service
@Transactional(readOnly=true, isolation=Isolation.READ_COMMITTED)
public class DefaultFilmService implements FilmService {

	private FilmRepository filmRepository; 
	
	public DefaultFilmService(FilmRepository filmRepository) {
		this.filmRepository=filmRepository; 
	}
	
	@Override
	public List<Genre> findGenres() {
		return filmRepository.findGenres(); 
	}
	
	@Override
	public List<Film> findFilmsByGenre(long id) {
		return filmRepository.findFilmsByGenre(id); 
	}
	
	@Override
	public Optional<Film> read(long id) {
		return filmRepository.read(id);
	}
	
	@Override
	@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
	public void update(Film film) {
		filmRepository.update(film);
	}
	
}
