package be.vdab.retrovideo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.services.FilmService;

@Controller
@RequestMapping("films")
public class FilmController {

	private static final String FILM_VIEW="film";
	private final FilmService filmService;
	
	public FilmController(FilmService filmService) {
		this.filmService=filmService; 
	}
	@GetMapping("{id}")
	ModelAndView film(@PathVariable long id) {
		ModelAndView modelAndView = new ModelAndView(FILM_VIEW); 
		filmService.read(id).ifPresent(film -> modelAndView.addObject(film));
		return modelAndView; 
	}
	
}
