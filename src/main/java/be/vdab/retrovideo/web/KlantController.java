package be.vdab.retrovideo.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.entities.Klant;
import be.vdab.retrovideo.services.KlantService;

@Controller
@RequestMapping("klant")
class KlantController {

	private final KlantService klantService; 
	private static final String KLANTEN_VIEW="klanten"; 
	
	KlantController(KlantService klantService) {
		this.klantService = klantService; 
	}
	
	@GetMapping
	ModelAndView klantenZoeken() {
		return new ModelAndView(KLANTEN_VIEW).addObject(new KlantenZoekenForm());  
	}
	
	@GetMapping(params="familienaamBevat")
	ModelAndView klantenZoeken(@Valid KlantenZoekenForm form, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView(KLANTEN_VIEW);
		if(bindingResult.hasErrors()) {
			return modelAndView; 
		}
		List<Klant> klanten = klantService.findByFamilienaamBevat(form.getFamilienaamBevat());
		if(klanten.isEmpty()) {
			bindingResult.reject("geenKlanten");
		} else {
			modelAndView.addObject("klanten", klanten);
		}
		return modelAndView; 
	}
}
