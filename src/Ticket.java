/**
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

	public Ticket(String name, double price, Event event, Seat seat, Venue venue){

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}




}
