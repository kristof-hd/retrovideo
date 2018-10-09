package be.vdab.retrovideo.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.entities.Film;
import be.vdab.retrovideo.entities.Genre;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.GenreService;

@Controller
@RequestMapping("/")
public class IndexController {

	private final GenreService genreService;
	private final FilmService filmService;
	
	public IndexController(GenreService genreService, FilmService filmService) {
		this.genreService=genreService;
		this.filmService=filmService; 
	}

	@GetMapping
	ModelAndView index() {
		List<Genre> genres = genreService.findGenres(); 
		return new ModelAndView("index", "genres", genres); 
	}

	@GetMapping("genres/{id}")
	ModelAndView index(@PathVariable long id) {
		List<Film> films = filmService.findFilmsByGenre(id);
		List<Genre> genres = genreService.findGenres();
		ModelAndView modelAndView = new ModelAndView("filmsPerGenre", "films", films);
		modelAndView.addObject("genres", genres);
		long geselecteerdeId=id; 
		modelAndView.addObject("geselecteerdeId", geselecteerdeId);
		return modelAndView;
	}
		
}
