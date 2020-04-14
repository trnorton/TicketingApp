import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

public class EmployeeTest {

	@Test
	public void employeeValuesTest(){
		final String name = "Luke";
		final String bday = "10/10/2020";
		final String phoneNumber = "1234567890";
		final String address = "123 Fake Street";
		final String email = "fake@fake.com";
		final Venue workVenue = null;
		final Employee employee = new Employee(name, bday, phoneNumber, address, email, workVenue);

		Assert.assertEquals(name, employee.getName());
		Assert.assertEquals(bday, employee.getBirthday());
		Assert.assertEquals(phoneNumber, employee.getPhoneNumber());
		Assert.assertEquals(address, employee.getAddress());
		Assert.assertEquals(email, employee.getEmail());
	}

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
	
	@Test
	public void testInputEvent() {
		final String name = "Luke";
		final String bday = "10/10/2020";
		final String phoneNumber = "1234567890";
		final String address = "123 Fake Street";
		final String email = "fake@fake.com";
		final Venue workVenue = null;
		final Employee employee = new Employee(name, bday, phoneNumber, address, email, workVenue);
		
		Venue venue = new Venue("Venue", "123 Movie St", 12);
		ArrayList<String> majorActors = new ArrayList<String>();
		majorActors.add("Elsa");
		ArrayList<String> producers = new ArrayList<String>();
		producers.add("Disney");
		Show show = new Movie("Frozen", 5, 8, "Animated", majorActors, producers);
		employee.inputEvent(venue, "04/10/2020", "12:00pm", show);
		assertNotNull(venue.getAvailableTheater(new Event(show, "04/10/2020", "12:00pm")).getEvent(new Event(show, "04/10/2020", "12:00pm")));
	}
	
	@Test
	public void testAddTicketToRefund() {
		final String name = "Luke";
		final String bday = "10/10/2020";
		final String phoneNumber = "1234567890";
		final String address = "123 Fake Street";
		final String email = "fake@fake.com";
		final Venue workVenue = null;
		final Employee employee = new Employee(name, bday, phoneNumber, address, email, workVenue);
		
		Ticket ticket = new Ticket();
		employee.addTicketToRefund(ticket);
		assertEquals(ticket, employee.getTicketsToRefundToCustomers().get(0));
		
	}
	
	@Test
	public void testRefundTickets() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
		
		final String name = "Luke";
		final String bday = "10/10/2020";
		final String phoneNumber = "1234567890";
		final String address = "123 Fake Street";
		final String email = "fake@fake.com";
		final Venue workVenue = null;
		final Employee employee = new Employee(name, bday, phoneNumber, address, email, workVenue);
		
		Ticket ticket = new Ticket();
		ticket.setName("Name");
		employee.addTicketToRefund(ticket);
		employee.refundTickets("Name");
		assertEquals(outContent.toString(), "Refunding Name 1 tickets for a total amount of 0 dollars!!\n");
	}

}
