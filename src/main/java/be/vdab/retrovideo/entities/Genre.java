package be.vdab.retrovideo.entities;

public class Genre {  // TODO setters weg, default constructor weg

	public Genre(){
	}
	
	public Genre(long id, String naam) {
		this.id=id;
		this.naam=naam; 
	}
	
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
	
}
