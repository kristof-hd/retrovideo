package be.vdab.retrovideo.entities;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class Reservatie { 
	private long klantId;
	private long filmId;
	@DateTimeFormat(style="SS") private LocalDateTime reservatie;

	public Reservatie(long klantId, long filmId) {
		this.klantId=klantId;
		this.filmId=filmId; 		
		this.reservatie=LocalDateTime.now();
	}
		
	public long getKlantId() {
		return klantId;
	}

	public long getFilmId() {
		return filmId;
	}

	public LocalDateTime getReservatie() {
		return reservatie;
	}
	
}
