package be.vdab.retrovideo.entities;

public class Genre {  

	public Genre(long id, String naam) {
		this.id=id;
		this.naam=naam; 
	}
	
	private long id;
	private String naam;
	
	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}
	
}
