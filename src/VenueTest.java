import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class VenueTest {

	@Test
	public void testVenue() {
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		assertNotNull(venue);
	}

	@Test
	public void testGetName() {
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		assertEquals(venue.getName(), "Venue");
	}

	@Test
	public void testSetValidName() {
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		venue.setName("New name");
		assertEquals(venue.getName(), "New name");
	}
	
	@Test
	public void testSetInvalidName() {
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		venue.setName(null);
		assertNull(venue.getName());
	}

	@Test
	public void testGetAddress() {
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		assertEquals(venue.getAddress(), "123 Movie St");
	}

	@Test
	public void testSetValidAddress() {
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		venue.setAddress("New address");
		assertEquals(venue.getAddress(), "New address");
	}
	
	@Test
	public void testSetInvalidAddress() {
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		venue.setAddress(null);
		assertNull(venue.getAddress());
	}

	@Test
	public void testDisplayReviews() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
 		 
        System.setOut(new PrintStream(outContent));
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		venue.addReview("review");
		venue.displayReviews();
		assertEquals(outContent.toString(), "review\n");
	}

	@Test
	public void testAddValidReview() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		venue.addReview("review");
		venue.displayReviews();
		assertEquals(outContent.toString(), "review\n");
	}
	
	@Test
	public void testAddInvalidReview() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		venue.addReview(null);
		venue.displayReviews();
		assertEquals(outContent.toString(), "null\n");
	}

	@Test
	public void testRemoveValidReview() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		venue.addReview("review");
		venue.removeReview("review");
		venue.displayReviews();
		assertEquals(outContent.toString(), "");
	}
	
	@Test
	public void testRemoveInvalidReview() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		venue.addReview("review");
		venue.removeReview(null);
		venue.displayReviews();
		assertEquals(outContent.toString(), "review\n");
	}

	@Test
	public void testDisplayCustRatings() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		venue.addCustRating(3);
		venue.displayCustRatings();
		assertEquals(outContent.toString(), "3\n");
	}

	@Test
	public void testAddValidCustRating() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		venue.addCustRating(3);
		venue.displayCustRatings();
		assertEquals(outContent.toString(), "3\n");
	}
	
	@Test
	public void testAddInvalidCustRating() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		venue.addCustRating(-1);
		venue.displayCustRatings();
		assertEquals(outContent.toString(), "-1\n", "Can't ever be outside of 1-5 range");
	}

	@Test
	public void testRemoveCustRating() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		venue.addCustRating(3);
		venue.removeCustRating(0);
		venue.displayCustRatings();
		assertEquals(outContent.toString(), "");
	}

	@Test
	public void testGetTheaters() {
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		assertNotNull(venue.getTheaters());
	}

	@Test
	public void testHasAvailableTheater() {
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		ArrayList<Movie> movies = JsonParser.loadMovies();
		Movie movie = null;
		for (Movie m : movies) {
			if (m.getName().equals("Frozen 2"))
				movie = m;
		}
		assertFalse(venue.hasAvailableTheater(new Event(movie, "04/10/2020", "12:00pm")));
	}

	@Test
	public void testGetAvailableTheater() {
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		ArrayList<Movie> movies = JsonParser.loadMovies();
		Movie movie = null;
		for (Movie m : movies) {
			if (m.getName().equals("Frozen 2"))
				movie = m;
		}
		assertNull(venue.getAvailableTheater(new Event(movie, "04/10/2020", "12:00pm")));
	}

	@Test
	public void testToString() {
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		assertEquals(venue.toString(), "Name: Venue\nAddress: 123 Movie St\nOverall Customer Rating: 0");
	}

}
