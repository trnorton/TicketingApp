import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class MainSystemTest {

	@Test
	public void testMainSystem() {
		MainSystem main = new MainSystem();
		assertNotNull(main);
	}

	
	@Test
	public void testSearchForValidLocation() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
        MainSystem main = new MainSystem();
        main.searchForLocation("Venue");
        assertEquals("Name: Venue\n" + "Address: 123 Movie St\n" + "Overall Customer Rating: 0\n", outContent.toString());
	}
	
	@Test
	public void testSearchForInvalidLocation() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
        MainSystem main = new MainSystem();
        main.searchForLocation("No venue");
        assertEquals("", outContent.toString());
	}

	
	@Test
	public void testDisplaySupportInfo() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
        MainSystem main = new MainSystem();
        main.displaySupportInfo();
        assertEquals("This is the customer support page. If you are experiencing issues with the app, call the number below:\n" + "(123)456-7890\n", outContent.toString());
	}

	@Test
	public void testDistributeConcessions() {
		String name = "Popcorn";
		int quantity = 4;
		assertNotNull(MainSystem.distributeConcessions(name, quantity));
	}

	
	@Test
	public void testDisplayConcessions() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
        MainSystem main = new MainSystem();
        main.displayConcessions();
        assertEquals("Popcorn - Quantity: 50 - Price: $4.0\n" + "Soda - Quantity: 50 - Price: $3.0\n" + "Twizzlers - Quantity: 50 - Price: $1.5\n", outContent.toString());
	}

	@Test
	public void testFindValidUser() {
		MainSystem main = new MainSystem();
		int acctID = 1;
		assertNotNull(main.findUser(acctID));
	}
	
	@Test
	public void testFindInvalidUser() {
		MainSystem main = new MainSystem();
		int acctID = 5;
		assertNull(main.findUser(acctID));
	}

	@Test
	public void testGetUsers() {
		MainSystem main = new MainSystem();
		assertNotNull(main.getUsers());
	}

	@Test
	public void testGetVenues() {
		MainSystem main = new MainSystem();
		assertNotNull(main.getVenues());
	}

	
	@Test
	public void testDisplayAvailableTheaterValidEvent() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
        MainSystem main = new MainSystem();
        
        ArrayList<Movie> movies = JsonParser.loadMovies();
		Movie movie = null;
		for (Movie m : movies) {
			if (m.getName().equals("Frozen 2"))
				movie = m;
		}
        main.displayAvailableTheater(new Event(movie, "04/10/2020", "12:00pm"));
        assertNotNull(outContent.toString());
	}
	
	@Test
	public void testDisplayAvailableTheaterInvalidEvent() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
        MainSystem main = new MainSystem();
        
        ArrayList<Concert> concerts = JsonParser.loadConcerts();
		Concert concert = null;
		for (Concert c : concerts) {
			if (c.getName().equals("The Beatles Are Back (from the grave)"))
				concert = c;
		}
        main.displayAvailableTheater(new Event(concert, "04/10/2020", "12:00pm"));
        assertEquals("", outContent.toString());
	}

	@Test
	public void testAddValidUserToDatabase() {
		MainSystem main = new MainSystem();
		User newUser = new User("New User", "01/01/1980", "(803)123-4445", "123 Filler Ct", "newuser@gmail.com");
		main.addUserToDatabase(newUser);
		assertEquals(newUser, main.getUsers().get(main.getUsers().size()-1));
	}
	
	@Test
	public void testAddInvalidUserToDatabase() {
		/*MainSystem main = new MainSystem();
		User newUser = null;
		main.addUserToDatabase(newUser);
		assertEquals(newUser, main.getUsers().get(main.getUsers().size()-1));*/
		MainSystem main = new MainSystem();
		Throwable exception = assertThrows(
	            NullPointerException.class, () -> {
	            	//MainSystem main = new MainSystem();
	        		//User newUser = null;
	        		//main.addUserToDatabase(newUser);
	        		main.addUserToDatabase(null);
	            }
	    );
	 
	   // assertEquals("null", exception.getMessage());
		assertNull(main.getUsers().get(main.getUsers().size()-1));
	}

	
	@Test
	public void testDisplayAvailableTheaterValidStringStringString() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
        MainSystem main = new MainSystem();
      
        main.displayAvailableTheater("Frozen 2", "04/10/2020", "12:00pm");
        assertNotNull(outContent.toString());
	}
	
	@Test
	public void testDisplayAvailableTheaterInvalidStringStringString() {
		/*final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
        MainSystem main = new MainSystem();
      
        main.displayAvailableTheater("The Beatles Are Back (from the grave)", "04/10/2020", "12:00pm");
        assertEquals("", outContent.toString());*/
        
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  		 
        System.setOut(new PrintStream(outContent));
        MainSystem main = new MainSystem();
        Throwable exception = assertThrows(
	            NullPointerException.class, () -> {
	         
	              
	                main.displayAvailableTheater("The Beatles Are Back (from the grave)", "04/10/2020", "12:00pm");
	            }
	    );
	 
        assertEquals("", outContent.toString());
	}

}
