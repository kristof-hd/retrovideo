package be.vdab.retrovideo.entities;

public class Genre {
	private long id;
	private String naam;
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	public Genre(){
	}
	
	public Genre(long id, String naam) {
		this.id=id;
		this.naam=naam; 
	}
}
