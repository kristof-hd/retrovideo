package be.vdab.retrovideo.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.entities.Film;
import be.vdab.retrovideo.services.FilmService;

@Controller
@RequestMapping
public class FilmController {

	private static final String FILM_VIEW="film";
	private static final String MANDJE_VIEW="mandje";
	private static final String REDIRECT_NA_TOEVOEGEN="redirect:/mandje";
	
	private final FilmService filmService;
	private final Mandje mandje; 
	
	public FilmController(Mandje mandje, FilmService filmService) {
		this.mandje=mandje; 
		this.filmService=filmService; 
	}
	
	private List<Film> maakFilmsVanFilmIds(List<Long> filmIds) {
		List<Film> films = new ArrayList<>(filmIds.size());
		for (long id: filmIds) {
			filmService.read(id).ifPresent(film -> films.add(film));
		}
		return films; 
	}
	
	@GetMapping("films/{id}")
	ModelAndView film(@PathVariable long id) {
		ModelAndView modelAndView = new ModelAndView(FILM_VIEW); 
		filmService.read(id).ifPresent(film -> modelAndView.addObject(film));
		MandjeForm form=new MandjeForm(); 
		form.setFilmId(id); 
		modelAndView.addObject(form); 
		return modelAndView; 
	}
	
	@PostMapping("films/{id}")
	String voegFilmToeAanMandje(MandjeForm form) {
		mandje.addFilmId(form.getFilmId());
		return REDIRECT_NA_TOEVOEGEN; 
	}
	
	@GetMapping("mandje")
	ModelAndView toonMandje() {
		ModelAndView modelAndView = new ModelAndView(MANDJE_VIEW);
		modelAndView.addObject("filmsInMandje", maakFilmsVanFilmIds(mandje.getFilmIds()));
		return modelAndView; 
	}
	
}
