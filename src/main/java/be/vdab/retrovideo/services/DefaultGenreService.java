package be.vdab.retrovideo.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.retrovideo.entities.Genre;
import be.vdab.retrovideo.repositories.GenreRepository;

@Service
@Transactional(readOnly=true, isolation=Isolation.READ_COMMITTED)
public class DefaultGenreService implements GenreService {
	
	private GenreRepository genreRepository; 
	
	public DefaultGenreService(GenreRepository genreRepository) {
		this.genreRepository=genreRepository; 
	}
	
	@Override
	public List<Genre> findGenres() {
		return genreRepository.findGenres(); 
	}
}
