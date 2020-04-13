import org.junit.Assert;
import org.junit.Test;

public class TicketTest {

	@Test
	public void setDiscountMultiplierTestValid(){
		Ticket ticket = new Ticket();
		ticket.setDiscountMultiplier(.01);

		Assert.assertEquals(0.01, ticket.getDiscountMultiplier(), 0.001);
	}

	@Test
	public void setNameTestValid(){
		Ticket ticket = new Ticket();
		ticket.setName("Harry Potter");ada

		Assert.assertEquals("Harry Potter", ticket.getName());
	}

	@Test
	public void setNameTestInvalidBlank(){
		Ticket ticket = new Ticket();
		ticket.setName("");

		Assert.assertEquals("NO NAME GIVEN", ticket.getName());
	}

	@Test
	public void setNameTestInvalidNull(){
		Ticket ticket = new Ticket();
		ticket.setName(null);

		Assert.assertEquals("NO NAME GIVEN", ticket.getName());
	}

	@Test
	public void setEventTestValid(){
		Ticket ticket = new Ticket();
		Event event = new Event(new Movie("Harry Potter", 0, 0, "fake", null, null), "01/01/2001", "4:20pm");

		ticket.setEvent(event);
		Assert.assertSame(event, ticket.getEvent());

	}

	@Test
	public void setPriceTestValid(){
		Ticket ticket = new Ticket();
		ticket.setPrice(10.0);

		Assert.assertEquals(10.0, ticket.getPrice(), 0.001);
	}

	@Test
	public void setPriceTestValidZero(){
		Ticket ticket = new Ticket();
		final double PRICE = 0.0;
		ticket.setPrice(PRICE);

		Assert.assertEquals(PRICE, ticket.getPrice(), 0.001);
	}

	@Test
	public void setPriceTestInvalidNegative(){
		Ticket ticket = new Ticket();
		final double PRICE = -1.0;
		ticket.setPrice(PRICE);

		Assert.assertEquals(0.0, ticket.getPrice(), 0.001);
	}

	@Test
	public void toStringTestValid(){
		Ticket ticket;
		final String NAME = "luke";
		final Venue VENUE = new Venue("Test Venue", null, 0);
		final Theater THEATER = new Theater('A', 0, 0);
		final Event EVENT = new Event(new Movie("Test Show", 0, 0, null, null, null), null, null);
		final Seat SEAT = new Seat('A', 1);
		final double PRICE = 5.0;
		ticket = new Ticket( NAME, PRICE, EVENT, SEAT, THEATER, VENUE);
		final String EXPECTED = "Ticket for \"" + NAME + "\"" + " at the " + VENUE + "\nTheater " + THEATER + "\n" + EVENT + "\nSeat: "
				+ SEAT + "\nPaid: $" + PRICE + "0\n";

		Assert.assertEquals(EXPECTED, ticket.toString());
	}

}
