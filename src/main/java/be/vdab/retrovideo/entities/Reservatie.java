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
	}

	public Reservatie(long klantId, long filmId, LocalDateTime reservatie) {
		this.klantId=klantId;
		this.filmId=filmId;
		this.reservatie=reservatie;
	}
		
	public long getKlantId() {
		return klantId;
	}
	public void setKlantId(long klantId) {
		this.klantId = klantId;
	}
	public long getFilmId() {
		return filmId;
	}
	public void setFilmId(long filmId) {
		this.filmId = filmId;
	}
	public LocalDateTime getReservatie() {
		return reservatie;
	}
	public void setReservatie(LocalDateTime reservatie) {
		this.reservatie = reservatie;
	} 
	
}
