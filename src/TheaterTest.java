import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TheaterTest {

	@Test
	public void testTheater() {
		Theater theater = new Theater('A', 10, 10);
		assertNotNull(theater);
	}

	@Test
	public void testGetName() {
		Theater theater = new Theater('A', 10, 10);
		assertEquals(theater.getName(), 'A');
	}

	@Test
	public void testSetValidName() {
		Theater theater = new Theater('A', 10, 10);
		theater.setName('B');
		assertEquals(theater.getName(), 'B');
	}
	
	@Test
	public void testSetInvalidName() {
		Theater theater = new Theater('A', 10, 10);
		theater.setName(Character.MIN_VALUE);
		assertEquals(theater.getName(), Character.MIN_VALUE);
	}

	@Test
	public void testDisplaySeats() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
        Theater theater = new Theater('A', 10, 10);
        theater.displaySeats();
        assertNotNull(outContent.toString());
	}

	@Test
	public void testAddValidEvent() {
		Theater theater = new Theater('A', 10, 10);
		ArrayList<Movie> movies = JsonParser.loadMovies();
		Movie movie = null;
		for (Movie m : movies) {
			if (m.getName().equals("Frozen"))
				movie = m;
		}
		theater.addEvent(new Event(movie, "04/10/2020", "12:00pm"));
		assertNotNull(theater.getEvent(new Event(movie, "04/10/2020", "12:00pm")));
	}
	
	@Test
	public void testAddInvalidEvent() {
		Theater theater = new Theater('A', 10, 10);
		ArrayList<Movie> movies = JsonParser.loadMovies();
		Movie movie = null;
		for (Movie m : movies) {
			if (m.getName().equals("Frozen"))
				movie = m;
		}
		Event newEvent = null;
		theater.addEvent(newEvent);
		assertNull(newEvent);
	}

	@Test
	public void testRemoveValidEvent() {
		Theater theater = new Theater('A', 10, 10);
		ArrayList<Movie> movies = JsonParser.loadMovies();
		Movie movie = null;
		for (Movie m : movies) {
			if (m.getName().equals("Frozen"))
				movie = m;
		}
		theater.removeEvent(new Event(movie, "04/10/2020", "12:00pm"));
		assertNull(theater.getEvent(new Event(movie, "04/10/2020", "12:00pm")));
	}
	
	@Test
	public void testRemoveInvalidEvent() {
		Theater theater = new Theater('A', 10, 10);
		ArrayList<Movie> movies = JsonParser.loadMovies();
		Movie movie = null;
		for (Movie m : movies) {
			if (m.getName().equals("Frozen"))
				movie = m;
		}
		theater.addEvent(new Event(movie, "04/10/2020", "12:00pm"));
		theater.removeEvent(null);
		assertNotNull(theater.getEvent(new Event(movie, "04/10/2020", "12:00pm")));
	}

	@Test
	public void testDisplayEvents() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
		Theater theater = new Theater('A', 10, 10);
		ArrayList<Movie> movies = JsonParser.loadMovies();
		Movie movie = null;
		for (Movie m : movies) {
			if (m.getName().equals("Frozen"))
				movie = m;
		}
		theater.addEvent(new Event(movie, "04/10/2020", "12:00pm"));
		theater.displayEvents();
		assertEquals(outContent.toString(), "Frozen - 04/10/2020 - 12:00pm\n");
	}

	@Test
	public void testGetValidEvent() {
		Theater theater = new Theater('A', 10, 10);
		ArrayList<Movie> movies = JsonParser.loadMovies();
		Movie movie = null;
		for (Movie m : movies) {
			if (m.getName().equals("Frozen"))
				movie = m;
		}
		theater.addEvent(new Event(movie, "04/10/2020", "12:00pm"));
		assertNotNull(theater.getEvent(new Event(movie, "04/10/2020", "12:00pm")));
	}

	@Test
	public void testHasAvailableSeat() {
		Theater theater = new Theater('A', 10, 10);
		assertTrue(theater.hasAvailableSeat());
	}

	@Test
	public void testGetAvailableSeat() {
		Theater theater = new Theater('A', 10, 10);
		assertNotNull(theater.getAvailableSeat());
	}

	@Test
	public void testGetValidSeat() {
		Theater theater = new Theater('A', 10, 10);
		assertNotNull(theater.getSeat('A', 1));
	}
	
	@Test
	public void testGetInvalidSeat() {
		Theater theater = new Theater('A', 10, 10);
		assertNull(theater.getSeat('Z', 1));
	}

	@Test
	public void testGetValidSeats() {
		Theater theater = new Theater('A', 10, 10);
		assertNotNull(theater.getSeats('A'));
	}
	
	@Test
	public void testGetInvalidSeats() {
		Theater theater = new Theater('A', 10, 10);
		assertNull(theater.getSeats('Z'));
	}

	@Test
	public void testToString() {
		Theater theater = new Theater('A', 10, 10);
		assertEquals(theater.toString(), "A");
	}

}
