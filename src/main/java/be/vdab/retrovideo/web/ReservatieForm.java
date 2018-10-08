package be.vdab.retrovideo.web;

import java.util.List;

public class ReservatieForm {
	
	private long klantId;
	private List<Long> filmIds;
	
	public long getKlantId() {
		return klantId;
	}
	public void setKlantId(long klantId) {
		this.klantId = klantId;
	}
	public List<Long> getFilmIds() {
		return filmIds;
	}
	public void setFilmIds(List<Long> filmIds) {
		this.filmIds = filmIds;
	} 
	
}
