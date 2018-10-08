package be.vdab.retrovideo.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.GenreService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {
	private IndexController controller;
	@Mock private FilmService filmService;
	@Mock private GenreService genreService;

	@Before
	public void before() {
		controller = new IndexController(genreService, filmService);
	}

	@Test
	public void methodIndexWerktSamenMetIndexDotJsp() {
		ModelAndView modelAndView = controller.index();
		assertEquals("index", modelAndView.getViewName());
	}

	@Test
	public void methodIndexGeeftGenresDoor() {
		ModelAndView modelAndView = controller.index();
		assertTrue(modelAndView.getModel().containsKey("genres"));
	}
}
