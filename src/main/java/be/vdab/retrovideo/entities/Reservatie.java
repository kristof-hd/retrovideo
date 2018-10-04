package be.vdab.retrovideo.entities;

import java.time.LocalDateTime;

public class Reservatie {
	private long klantId;
	private long filmId;
	private LocalDateTime reservatie;
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
