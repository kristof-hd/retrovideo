package be.vdab.retrovideo.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.entities.Klant;
import be.vdab.retrovideo.services.KlantService;

@Controller
@RequestMapping("klant")
public class KlantController {

	private final KlantService klantService; 
	private static final String KLANTEN_VIEW="klanten"; 
	
	public KlantController(KlantService klantService) {
		this.klantService=klantService; 
	}
	
	@GetMapping
	ModelAndView klantenZoeken() {
		return new ModelAndView(KLANTEN_VIEW).addObject(new KlantenZoekenForm());  
	}
	
	@GetMapping(params="familienaamBevat")
	ModelAndView klantenZoeken(KlantenZoekenForm form) {
		ModelAndView modelAndView = new ModelAndView(KLANTEN_VIEW);
		//List<Klant> klanten = Arrays.asList(new Klant(1,"test"));
		List<Klant> klanten = klantService.findByFamilienaamBevat(form.getFamilienaamBevat());
		modelAndView.addObject("klanten", klanten); 
		return modelAndView; 
	}
}
