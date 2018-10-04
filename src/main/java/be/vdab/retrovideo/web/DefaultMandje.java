package be.vdab.retrovideo.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class DefaultMandje implements Serializable, Mandje {

	private static final long serialVersionUID=1L; 
	private final List<Long> filmIds=new ArrayList<>(); 
	
	@Override
	public void addFilmId(long filmId) {
		filmIds.add(filmId); 
	}
	
	@Override
	public List<Long> getFilmIds(){
		return filmIds; 
	}
	
}
