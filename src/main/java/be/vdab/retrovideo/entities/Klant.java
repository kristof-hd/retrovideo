package be.vdab.retrovideo.entities;

public class Klant {
	private long id;
	private String familienaam;

	public Klant(long id, String familienaam) {
		this.id=id;
		this.familienaam=familienaam; 
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFamilienaam() {
		return familienaam;
	}
	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}
	
}
