package be.vdab.retrovideo.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

@Controller
@RequestMapping("/")
public class FilmController {

	private static final String FILM_VIEW="film";
	private static final String MANDJE_VIEW="mandje";
	private static final String BEVESTIGEN_VIEW="bevestigen";
	private static final String RAPPORT_VIEW="rapport";
	private static final String REDIRECT_NA_TOEVOEGEN="redirect:/mandje";
	private static final String REDIRECT_NA_VERWIJDEREN="redirect:/mandje";
	private static final String REDIRECT_NA_BEVESTIGEN="redirect:/rapport"; 
	
	private final FilmService filmService; 
	private final KlantService klantService;
	private final ReservatieService reservatieService;
	private final Mandje mandje;
	private List<Long> idsMislukteReservaties = new ArrayList<>();
	private List<String> titelsMislukteReservaties = new ArrayList<>(); 
	
	public FilmController(Mandje mandje, FilmService filmService, KlantService klantService, ReservatieService reservatieService) {
		this.mandje=mandje; 
		this.filmService=filmService; 
		this.klantService=klantService;
		this.reservatieService=reservatieService;
	}
	
	private List<Film> maakFilmsVanFilmIds(List<Long> filmIds) {
		List<Film> films = new ArrayList<>(filmIds.size());
		for (long id: filmIds) {
			filmService.read(id).ifPresent(film -> films.add(film));
		}
		return films; 
	}
	
	private BigDecimal berekenTotalePrijs(List<Long> filmIds) {
		BigDecimal totalePrijs=BigDecimal.ZERO; 
		for (long id: filmIds) {
			totalePrijs=totalePrijs.add(filmService.read(id).get().getPrijs());
		}
		return totalePrijs; 
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
		modelAndView.addObject("totalePrijs", berekenTotalePrijs(mandje.getFilmIds()));
		return modelAndView; 
	}
	
	@PostMapping("mandje")
	String verwijder(long[] verwijderid) {
		mandje.verwijderFilmIds(verwijderid);
		return REDIRECT_NA_VERWIJDEREN; 
	}	
	
	@GetMapping("bevestigen/{id}")
	ModelAndView bevestigen(@PathVariable long id) {
		mandje.setKlantId(id);
		ModelAndView modelAndView = new ModelAndView(BEVESTIGEN_VIEW); 
		klantService.readKlant(id).ifPresent(klant -> modelAndView.addObject(klant));
		modelAndView.addObject("aantalArtikelsInMandje", mandje.telAantalArtikelsInMandje()); 
		ReservatieForm reservatieForm = new ReservatieForm(); 
		reservatieForm.setKlantId(id);
		reservatieForm.setFilmIds(mandje.getFilmIds());
		modelAndView.addObject(reservatieForm); 
		return modelAndView;  
	}
	
	@PostMapping("bevestigen/{id}")
	ModelAndView bevestigen(ReservatieForm reservatieForm) {
		for (long filmId: mandje.getFilmIds()) {
			if(filmService.read(filmId).get().getBeschikbaar()>0) {
				Reservatie reservatie = new Reservatie(reservatieForm.getKlantId(), filmId);
				reservatieService.create(reservatie);
				Film film = filmService.read(filmId).get();
				filmService.update(film);
			}
			else {
				idsMislukteReservaties.add(filmId); 
			}
		}
		for(long id: idsMislukteReservaties) {
			String titel = filmService.read(id).get().getTitel();
			titelsMislukteReservaties.add(titel); 
		}
		return new ModelAndView(REDIRECT_NA_BEVESTIGEN); 
	}
	
	@GetMapping("rapport") 
	ModelAndView rapport() {
		return new ModelAndView(RAPPORT_VIEW, "titelsMislukteReservaties", titelsMislukteReservaties);
	}
}