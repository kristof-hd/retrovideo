package be.vdab.retrovideo.web;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.entities.Film;
import be.vdab.retrovideo.entities.Reservatie;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.KlantService;
import be.vdab.retrovideo.services.ReservatieService;
import be.vdab.retrovideo.valueobjects.TotalePrijs;

@Controller
@RequestMapping("/")
class FilmController {

	private static final String FILM_VIEW="film";
	private static final String MANDJE_VIEW="mandje";
	private static final String BEVESTIGEN_VIEW="bevestigen";
	private static final String REDIRECT_NA_TOEVOEGEN="redirect:/mandje";
	private static final String REDIRECT_NA_VERWIJDEREN="redirect:/mandje";
	
	private final FilmService filmService; 
	private final KlantService klantService;
	private final ReservatieService reservatieService; 
	private final Mandje mandje;
	
	FilmController(Mandje mandje, FilmService filmService, KlantService klantService, ReservatieService reservatieService) {
		this.mandje = mandje; 
		this.filmService = filmService; 
		this.klantService = klantService;
		this.reservatieService=reservatieService;
	}

	@GetMapping("films/{id}")
	ModelAndView film(@PathVariable long id) {
		ModelAndView modelAndView = new ModelAndView(FILM_VIEW); 
		filmService.read(id).ifPresent(film -> modelAndView.addObject(film));
		modelAndView.addObject("inMandje", mandje.bevat(id));
		return modelAndView; 
	}

	@PostMapping("films/{id}")
	String voegFilmToeAanMandje(@PathVariable long id) {
		mandje.addFilmId(id);
		return REDIRECT_NA_TOEVOEGEN; 
	}
		
	@GetMapping("mandje")
	ModelAndView toonMandje() {
		ModelAndView modelAndView = new ModelAndView(MANDJE_VIEW);

		Set<Long> filmIds=mandje.getFilmIds(); 
		
		Set<Film> films = new HashSet<>(filmIds.size());
		BigDecimal totalePrijs=BigDecimal.ZERO;
		
		for (long id: filmIds) {
			Optional<Film> film = filmService.read(id); 
			film.ifPresent(film2 -> films.add(film2));
			totalePrijs = totalePrijs.add(film.get().getPrijs());			
		}
		
		modelAndView.addObject("filmsInMandje", films);
		modelAndView.addObject("totalePrijs", new TotalePrijs(totalePrijs));
		return modelAndView; 
	}

	@PostMapping("mandje")
	String verwijder(long[] verwijderid) {
		if(verwijderid!=null) {
			mandje.verwijderFilmIds(verwijderid);
		}
		return REDIRECT_NA_VERWIJDEREN; 
	}	
	
	@GetMapping("bevestigen/{id}")
	ModelAndView bevestigen(@PathVariable long id) {
		ModelAndView modelAndView = new ModelAndView(BEVESTIGEN_VIEW); 
		klantService.readKlant(id).ifPresent(klant -> modelAndView.addObject(klant));
		modelAndView.addObject("aantalArtikelsInMandje", mandje.telAantalArtikelsInMandje()); 
		return modelAndView;  
	}

	@PostMapping("bevestigen/{id}")
	ModelAndView bevestig(@PathVariable long id) {
		List<Long> fouten = new ArrayList<>(); 
		for (long filmId: mandje.getFilmIds()) {
			Reservatie reservatie = new Reservatie(id, filmId);
			try {
				reservatieService.reserveer(reservatie);
			}
			catch (Exception ex) {
				fouten.add(filmId);
			}
		}
		return new ModelAndView("rapport", "fouten", fouten); 
	}		
	
//	@PostMapping("bevestigen/{id}")
//	ModelAndView bevestig(@PathVariable long id) {
//		List<Long> fouten = new ArrayList<>(); 
//		for (long filmId: mandje.getFilmIds()) {
//			Reservatie reservatie = new Reservatie(id, filmId);
//			int aantalAangepasteRecords=filmService.update(reservatie, filmId);
//			if(aantalAangepasteRecords==0) {
//				fouten.add(filmId);
//			}
//		}
//		return new ModelAndView("rapport", "fouten", fouten); 
//	}		
	
}