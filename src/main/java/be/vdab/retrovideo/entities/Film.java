package be.vdab.retrovideo.entities;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

public class Film { 
	
	private long id;
	private long genreId;
	private String titel;
	private long voorraad;
	private long gereserveerd;
	@NumberFormat(pattern="0.00") private BigDecimal prijs;
	
	public Film(long id, long genreId, String titel, int voorraad, int gereserveerd, BigDecimal prijs) {
		this.id=id;
		this.genreId=genreId;
		this.titel=titel; 
		this.voorraad=voorraad;
		this.gereserveerd=gereserveerd;
		this.prijs=prijs; 
	}

	public long getId() {
		return id;
	}

	public long getGenreId() {
		return genreId;
	}

	public String getTitel() {
		return titel;
	}

	public long getVoorraad() {
		return voorraad;
	}

	public long getGereserveerd() {
		return gereserveerd;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public long getBeschikbaar() {
		return voorraad-gereserveerd; 
	}
}
