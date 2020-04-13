import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class EventTest {

	@Test
	public void testEvent() {
		Event event = new Event();
		assertNotNull(event);
	}

	@Test
	public void testEventShowStringString() {
		Event event = new Event(null, "Date", "Time");
		assertNotNull(event);
	}

	@Test
	public void testGetShow() {
		ArrayList<Movie> movies = JsonParser.loadMovies();
		Movie movie = null;
		for (Movie m : movies) {
			if (m.getName().equals("Frozen 2"))
				movie = m;
		}
		Event event = new Event(movie, "04/10/2020", "12:00pm");
		assertEquals(event.getShow(), movie);
	}

	@Test
	public void testSetValidShow() {
		ArrayList<Movie> movies = JsonParser.loadMovies();
		Movie movie = null;
		for (Movie m : movies) {
			if (m.getName().equals("Frozen 2"))
				movie = m;
		}
		Event event = new Event(null, "04/10/2020", "12:00pm");
		event.setShow(movie);
		assertEquals(event.getShow(), movie);
	}
	
	@Test
	public void testSetInvalidShow() {
		ArrayList<Movie> movies = JsonParser.loadMovies();
		Movie movie = null;
		for (Movie m : movies) {
			if (m.getName().equals("Frozen 2"))
				movie = m;
		}
		Event event = new Event(movie, "04/10/2020", "12:00pm");
		event.setShow(null);
		assertNull(event.getShow());
	}

	@Test
	public void testGetDate() {
		Event event = new Event(null, "04/10/2020", "12:00pm");
		assertEquals(event.getDate(), "04/10/2020");
	}

	@Test
	public void testSetValidDate() {
		Event event = new Event(null, "04/10/2020", "12:00pm");
		event.setDate("06/05/2020");
		assertEquals(event.getDate(), "06/05/2020");
	}
	
	@Test
	public void testSetInvalidDate() {
		Event event = new Event(null, "04/10/2020", "12:00pm");
		event.setDate("Date");
		assertNotNull(event.getDate(), "Date can't be anything but MM/DD/YYYY");
	}

	@Test
	public void testGetTime() {
		Event event = new Event(null, "04/10/2020", "12:00pm");
		assertEquals(event.getTime(), "12:00pm");
	}

	@Test
	public void testSetValidTime() {
		Event event = new Event(null, "04/10/2020", "12:00pm");
		event.setTime("06:00pm");
		assertEquals(event.getTime(), "06:00pm");
	}
	
	@Test
	public void testSetInvalidTime() {
		Event event = new Event(null, "04/10/2020", "12:00pm");
		event.setTime("Time");
		assertNotNull(event.getTime(), "Time can't be anything but HH:MM[am/pm]");
	}

	@Test
	public void testToString() {
		ArrayList<Movie> movies = JsonParser.loadMovies();
		Movie movie = null;
		for (Movie m : movies) {
			if (m.getName().equals("Frozen 2"))
				movie = m;
		}
		Event event = new Event(movie, "04/10/2020", "12:00pm");
		assertEquals(event.toString(), "Frozen 2 - 04/10/2020 - 12:00pm");
	}

}
