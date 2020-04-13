import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ShowTest {

	@Test
	public void testGetName() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		assertEquals(show.getName(), "Frozen");
	}

	@Test
	public void testSetValidName() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		show.setName("Frozen 2");
		assertEquals(show.getName(), "Frozen 2");
	}
	
	@Test
	public void testSetInvalidName() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		show.setName(null);
		assertNull(show.getName());
	}

	@Test
	public void testGetOffRating() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		assertEquals(show.getOffRating(), 5);
	}

	@Test
	public void testSetValidOffRating() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		show.setOffRating(4);
		assertEquals(show.getOffRating(), 4);
	}
	
	@Test
	public void testSetInvalidOffRating() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		show.setOffRating(0);
		assertEquals(show.getOffRating(), 0, "Official rating can't be anything outside the range 1-10");
	}

	@Test
	public void testGetAgeRating() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		assertEquals(show.getAgeRating(), 8);
	}

	@Test
	public void testSetValidAgeRating() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		show.setAgeRating(13);
		assertEquals(show.getAgeRating(), 13);
	}
	
	@Test
	public void testSetInvalidAgeRating() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		show.setAgeRating(0);
		assertEquals(show.getAgeRating(), 0, "Age rating can't be anything other than a normal age of a person");
	}

	@Test
	public void testDisplayReviews() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
		
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		show.addReview("review");
		show.displayReviews();
		assertEquals(outContent.toString(), "review\n");
	}

	@Test
	public void testAddReview() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
		
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		show.addReview("review");
		show.displayReviews();
		assertEquals(outContent.toString(), "review\n");
	}

	@Test
	public void testRemoveReview() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
		
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		show.addReview("review");
		show.removeReview("review");
		show.displayReviews();
		assertEquals(outContent.toString(), "");
	}

	@Test
	public void testDisplayCustRatings() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
		
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		show.addCustRating(3);
		show.displayCustRatings();
		assertEquals(outContent.toString(), "3\n");
	}

	@Test
	public void testAddCustRating() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
		
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		show.addCustRating(3);
		show.displayCustRatings();
		assertEquals(outContent.toString(), "3\n");
	}

	@Test
	public void testRemoveCustRating() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
		
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		show.addCustRating(3);
		show.removeCustRating(3);
		show.displayCustRatings();
		assertEquals(outContent.toString(), "");
	}

	@Test
	public void testAddProducer() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		show.addProducer("Disney+");
		assertEquals(show.getProducers().get(show.getProducers().size()-1), "Disney+");
	}

	@Test
	public void testRemoveProducer() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		show.addProducer("Disney+");
		show.removeProducer("Disney");
		assertEquals(show.getProducers().get(0), "Disney+");
	}

	@Test
	public void testSetValidProducers() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		
		ArrayList<String> newProducers = new ArrayList<String>();
		newProducers.add("Fox");
		show.setProducers(newProducers);
		assertEquals(show.getProducers(), newProducers);
	}
	
	@Test
	public void testSetInvalidProducers() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		
		ArrayList<String> newProducers = null;
		show.setProducers(newProducers);
		assertNull(show.getProducers());
	}

	@Test
	public void testGetProducers() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		assertNotNull(show.getProducers());
	}

	@Test
	public void testGetReviews() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		assertNotNull(show.getReviews());
	}

	@Test
	public void testSetValidReviews() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		
		ArrayList<String> newReviews = new ArrayList<String>();
		newReviews.add("New review");
		show.setReviews(newReviews);
		assertEquals(show.getReviews(), newReviews);
	}
	
	@Test
	public void testSetInvalidReviews() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		
		ArrayList<String> newReviews = null;
		show.setReviews(newReviews);
		assertNull(show.getReviews());
	}

	@Test
	public void testGetCustRatings() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		assertNotNull(show.getCustRatings());
	}

	@Test
	public void testSetValidCustRatings() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		
		ArrayList<Integer> newRatings = new ArrayList<Integer>();
		newRatings.add(1);
		show.setCustRatings(newRatings);
		assertEquals(show.getCustRatings(), newRatings);
	}
	
	@Test
	public void testSetInvalidCustRatings() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		
		ArrayList<Integer> newRatings = null;
		show.setCustRatings(newRatings);
		assertNull(show.getCustRatings());
	}

	@Test
	public void testToString() {
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		show.addCustRating(5);
		assertEquals(show.toString(), "Movie Name: Frozen\nIMDB Rating: 5\nAge Rating: 8\nOverall Viewer Rating: 5\nProducers: Disney \nGenre: Animated\nFamous Actors: Elsa \n");
	}

}
