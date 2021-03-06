package be.vdab.retrovideo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.GenreService;

@Controller
@RequestMapping("/")
class IndexController {

	private final GenreService genreService;
	private final FilmService filmService;

	IndexController(GenreService genreService, FilmService filmService) {
		this.genreService = genreService;
		this.filmService = filmService;
	}

	@GetMapping
	ModelAndView index() {
		return new ModelAndView("index", "genres", genreService.findGenres());
	}

	@GetMapping("genres/{id}")
	ModelAndView index(@PathVariable long id) {
		ModelAndView modelAndView = new ModelAndView("filmsPerGenre", "films", filmService.findFilmsByGenre(id));
		modelAndView.addObject("genres", genreService.findGenres());
		modelAndView.addObject("geselecteerdeId", id);
		return modelAndView;
	}

}