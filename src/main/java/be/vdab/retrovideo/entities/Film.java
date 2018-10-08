package be.vdab.retrovideo.entities;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

public class Film {
	
	private long id;
	private Genre genre;
	private String titel;
	private long voorraad;
	private long gereserveerd;
	@NumberFormat(pattern="0.00") private BigDecimal prijs;
	
	public Film() {
	}
	
	public Film(long id, String titel) {
		this.id=id;
		this.titel=titel; 
	}

	public Film(long id, String titel, int voorraad, int gereserveerd, BigDecimal prijs) {
		this.id=id;
		this.titel=titel; 
		this.voorraad=voorraad;
		this.gereserveerd=gereserveerd;
		this.prijs=prijs; 
	}

	public Film(long id, Genre genre, String titel, int voorraad, int gereserveerd, BigDecimal prijs) {
		this.id=id;
		this.genre=genre;
		this.titel=titel; 
		this.voorraad=voorraad;
		this.gereserveerd=gereserveerd;
		this.prijs=prijs; 
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public long getVoorraad() {
		return voorraad;
	}

	public void setVoorraad(long voorraad) {
		this.voorraad = voorraad;
	}

	public long getGereserveerd() {
		return gereserveerd;
	}

	public void setGereserveerd(long gereserveerd) {
		this.gereserveerd = gereserveerd;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}
	
	public long getBeschikbaar() {
		return voorraad-gereserveerd; 
	}
}
