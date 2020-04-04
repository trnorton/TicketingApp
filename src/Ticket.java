/**
 * Defines Ticket type
 * @author Lukacs Ablonczy
 */
public class Ticket {

	private String name;
	private double price;
	private Event event;
	private Seat seat;
	private Venue venue;

	/**
	 * Default Constructor
	 */
	public Ticket(){

	}

	/**
	 * Constrcutor for Ticket type
	 * @param name
	 * @param price
	 * @param event
	 * @param seat
	 * @param venue
	 */
	public Ticket(String name, double price, Event event, Seat seat, Venue venue){
		this.setName(name);
		this.setPrice(price);
		this.setEvent(event);
		this.setSeat(seat);
		this.setVenue(venue);
	}

	/**
	 * returns Ticket name
	 * @return String name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets ticket name
	 * @param name String name
	 */
	public void setName(String name) {
		if(name == null || name.equals("")){
			this.name = "NO NAME GIVEN";
			return;
		}

		this.name = name;
	}

	/**
	 * Returns ticket price
	 * @return double price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets ticket price
	 * @param price double price
	 */
	public void setPrice(double price) {
		if(price < 0){
			this.price = 0;
			return;
		}

		this.price = price;
		this.price = price;
	}

	/**
	 * Returns the even the ticket is for
	 * @return Event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * Gives Event ownership of ticket
	 * @param event Event
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 * Returns seat ticket is for
	 * @return Seat
	 */
	public Seat getSeat() {
		return seat;
	}

	/**
	 * Sets the seat the ticket is for
	 * @param seat Seat
	 */
	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	/**
	 * Returns the venue the ticket is for
	 * @return Venue
	 */
	public Venue getVenue() {
		return venue;
	}

	/**
	 * Sets the venue of the ticket
	 * @param venue
	 */
	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	/**
	 * Returns a printout of the ticket
	 * @return Ticket for "Harry Potter" at the Ford Theater\nSeat: 54A\nPaid: $12.00
	 */
	public String toString(){
		return "Ticket for \'" + name + "\"" + " at the " + venue + " Theater" + "\n" + event + "\nSeat: " + seat  + "\nPaid: $" + price;
	}

}
