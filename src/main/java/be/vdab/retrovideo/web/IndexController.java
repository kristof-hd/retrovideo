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
	
//	@GetMapping
//	String index() {
//		return "/WEB-INF/JSP/index.jsp"; 
//	}

	@GetMapping
	ModelAndView index() {
		//String boodschap="hallo hallo";
		//return new ModelAndView("/WEB-INF/JSP/index.jsp", "boodschap", boodschap);
		//return new ModelAndView("index", "boodschap", boodschap);
		//List<Genre> genres=Arrays.asList(new Genre(1,"Test"));
		List<Genre> genres = filmService.findGenres(); 
		return new ModelAndView("index", "genres", genres); 
	}

	@GetMapping("genres/{id}")
	ModelAndView index(@PathVariable long id) {
		List<Film> films = filmService.findFilmsByGenre(id);
		List<Genre> genres = filmService.findGenres();
		//List<Film> films = Arrays.asList(new Film(1, "test"));
		//return new ModelAndView("index", "films", films); 
		ModelAndView modelAndView = new ModelAndView("index", "films", films);
		modelAndView.addObject("genres", genres);
		return modelAndView;
	}
	
	
}
