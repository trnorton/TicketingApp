import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class AdminTest {

	@Test
	public void adminValuesTest(){
		final String name = "Luke";
		final String bday = "10/10/2020";
		final String phoneNumber = "1234567890";
		final String address = "123 Fake Street";
		final String email = "fake@fake.com";
		final Admin admin = new Admin(name, bday, phoneNumber, address, email);

		Assert.assertEquals(name, admin.getName());
		Assert.assertEquals(bday, admin.getBirthday());
		Assert.assertEquals(phoneNumber, admin.getPhoneNumber());
		Assert.assertEquals(address, admin.getAddress());
		Assert.assertEquals(email, admin.getEmail());
	}

	@Test
	public void toStringTest(){
		final String name = "Luke";
		final String bday = "10/10/2020";
		final String phoneNumber = "1234567890";
		final String address = "123 Fake Street";
		final String email = "fake@fake.com";
		final Admin admin = new Admin(name, bday, phoneNumber, address, email);

		Venue venue = new Venue("Test", "null", 0);
		ArrayList<Venue> venues = new ArrayList<>(Arrays.asList(venue));

		final String output = "\n\nName: " + name + "\nBirthday: " + bday + "\nPhone Number: "
				+ phoneNumber + "\nAddress: " + address + "\nEmail: " + email;

		Assert.assertEquals(output, admin.toString());


	}

	@Test
	public void venueListTest() throws IOException {
		final String name = "Luke";
		final String bday = "10/10/2020";
		final String phoneNumber = "1234567890";
		final String address = "123 Fake Street";
		final String email = "fake@fake.com";
		final Admin admin = new Admin(name, bday, phoneNumber, address, email);

		MainSystem mainSystem = new MainSystem();
		admin.addVenue("Venue", mainSystem.getVenues());
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		admin.displayVenues();
		Assert.assertEquals(mainSystem.getVenues().get(0).toString() + "\n", outContent.toString());
		outContent.close();
	}

	@Test
	public void addVenueTest(){
		final String name = "Luke";
		final String bday = "10/10/2020";
		final String phoneNumber = "1234567890";
		final String address = "123 Fake Street";
		final String email = "fake@fake.com";
		final Admin admin = new Admin(name, bday, phoneNumber, address, email);



		Venue venue = new Venue("Test", "null", 0);
		ArrayList<Venue> venuesNew = new ArrayList<>(Arrays.asList(venue));
		admin.addVenue(venue.getName(), venuesNew);


	}
	
	@Test
	public void removeVenueTest(){
		final String name = "Luke";
		final String bday = "10/10/2020";
		final String phoneNumber = "1234567890";
		final String address = "123 Fake Street";
		final String email = "fake@fake.com";
		final Admin admin = new Admin(name, bday, phoneNumber, address, email);



		Venue venue = new Venue("Test", "null", 0);
		ArrayList<Venue> venuesNew = new ArrayList<>(Arrays.asList(venue));
		admin.addVenue(venue.getName(), venuesNew);
		admin.removeVenue(venue.getName());

	}
	
	@Test
	public void testInputEvent() {
		final String name = "Luke";
		final String bday = "10/10/2020";
		final String phoneNumber = "1234567890";
		final String address = "123 Fake Street";
		final String email = "fake@fake.com";
		final Admin admin = new Admin(name, bday, phoneNumber, address, email);
		
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		admin.inputEvent(venue, "04/10/2020", "12:00pm", show);
		assertNotNull(venue.getAvailableTheater(new Event(show, "04/10/2020", "12:00pm")).getEvent(new Event(show, "04/10/2020", "12:00pm")));
	}

}
