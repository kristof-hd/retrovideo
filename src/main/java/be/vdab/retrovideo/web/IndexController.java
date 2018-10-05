package be.vdab.retrovideo.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.entities.Film;
import be.vdab.retrovideo.entities.Genre;
import be.vdab.retrovideo.services.FilmService;

@Controller
@RequestMapping("/")
public class IndexController {

	private final FilmService filmService;
	
	public IndexController(FilmService filmService) {
		this.filmService=filmService; 
	}
	
	@GetMapping
	ModelAndView index() {
		List<Genre> genres = filmService.findGenres(); 
		return new ModelAndView("index", "genres", genres); 
	}

	@GetMapping("genres/{id}")
	ModelAndView index(@PathVariable long id) {
		List<Film> films = filmService.findFilmsByGenre(id);
		List<Genre> genres = filmService.findGenres();
		ModelAndView modelAndView = new ModelAndView("filmsPerGenre", "films", films);
		modelAndView.addObject("genres", genres);
		return modelAndView;
	}
	
	
}
