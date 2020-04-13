import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class EmployeeTest {

	@Test
	public void inputDiscountTest(){
		final String name = "Luke";
		final String bday = "10/10/2020";
		final String phoneNumber = "1234567890";
		final String address = "123 Fake Street";
		final String email = "fake@fake.com";
		final Venue workVenue = null;
		final Employee employee = new Employee(name, bday, phoneNumber, address, email, workVenue);
		final double fullPrice = 100.0;


		final String test = "test ";
		final double discountPercent = 50.0;
		final Ticket ticket = new Ticket(null, fullPrice, null, null, null, null);
		User testUser = new User(test, bday, phoneNumber, address, email);
		testUser.addTicket(ticket);
		employee.inputDiscount(discountPercent, testUser);

		Assert.assertEquals(fullPrice * (discountPercent/100), testUser.getTickets().get(0).getPriceWithDiscount(), 0.0001);

	}

	@Test
	public void inputDiscountTestNoTickets(){
		final String name = "Luke";
		final String bday = "10/10/2020";
		final String phoneNumber = "1234567890";
		final String address = "123 Fake Street";
		final String email = "fake@fake.com";
		final Venue workVenue = null;
		final Employee employee = new Employee(name, bday, phoneNumber, address, email, workVenue);
		final double fullPrice = 100.0;



		final String test = "test ";
		final double discountPercent = 50.0;

		User testUser = new User(test, bday, phoneNumber, address, email);
		employee.inputDiscount(discountPercent, testUser);
		Assertions.assertThrows(java.lang.IndexOutOfBoundsException.class, () -> Assert.assertEquals(fullPrice * (discountPercent/100), testUser.getTickets().get(0).getPriceWithDiscount(), 0.0001));

	}


	@Test
	public void toStringTest(){
		final String name = "Luke";
		final String bday = "10/10/2020";
		final String phoneNumber = "1234567890";
		final String address = "123 Fake Street";
		final String email = "fake@fake.com";
		final Venue workVenue = null;
		final Employee employee = new Employee(name, bday, phoneNumber, address, email, workVenue);

		final String expected = "Employee\tName:" + name + "\nVenue: " + workVenue + "\nBirthday: "
				+ bday + "\nPhone:  " + phoneNumber + "\nHome Address: " + address
				+ "\nEmail: " + email;

		Assert.assertEquals(expected, employee.toString());
	}

	@Test
	public void setWorkVenueTestFails(){
		final String name = "Luke";
		final String bday = "10/10/2020";
		final String phoneNumber = "1234567890";
		final String address = "123 Fake Street";
		final String email = "fake@fake.com";
		Venue workVenue = new Venue("Test", "null", 0);
		Employee employee = new Employee(name, bday, phoneNumber, address, email, null);
		employee.setWorkVenue(workVenue);

		Assert.assertEquals(null, employee.getWorkVenue());
	}

	@Test
	public void setTicketsToRefundTest(){
		final String name = "Luke";
		final String bday = "10/10/2020";
		final String phoneNumber = "1234567890";
		final String address = "123 Fake Street";
		final String email = "fake@fake.com";
		Venue workVenue = new Venue("Test", "null", 0);
		Employee employee = new Employee(name, bday, phoneNumber, address, email, null);
		employee.setWorkVenue(workVenue);
		final double fullPrice = 100.0;

		final Ticket[] toRefund  = {new Ticket("Potter", fullPrice, null, null, null, null), new Ticket("harry", fullPrice, null, null, null, null)};
		final ArrayList<Ticket> ticketsToRefund = new ArrayList<Ticket>(Arrays.asList(toRefund));
		employee.setTicketsToRefundToCustomers(ticketsToRefund);

		Assert.assertEquals(ticketsToRefund, employee.getTicketsToRefundToCustomers());
	}

}
