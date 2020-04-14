import org.junit.Assert;
import org.junit.jupiter.api.Test;

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

		//Class<?> adminClass = admin.getClass();
		//Field field = adminClass.getDeclaredField(venueList);
		//field.setAccessible(true);





	}

}
