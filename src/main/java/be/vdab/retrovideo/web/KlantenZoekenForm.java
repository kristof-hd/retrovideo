package be.vdab.retrovideo.web;

import javax.validation.constraints.NotBlank;

public class KlantenZoekenForm {
	@NotBlank private String familienaamBevat;

	public String getFamilienaamBevat() {
		return familienaamBevat;
	}

	public void setFamilienaamBevat(String familienaamBevat) {
		this.familienaamBevat = familienaamBevat;
	}
	
}
