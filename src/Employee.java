import java.util.ArrayList;

/**
 * This class defines the Employee User type
 * @author Lukacs Ablonczy
 */
public class Employee extends User {

	private Venue workVenue;
	private ArrayList<Ticket> ticketsToRefundToCustomers;

	/**
	 * Parametrized constructor for User
	 * @param name        the user's name
	 * @param birthday    the user's birthday in ddmmyyyy format
	 * @param phoneNumber the user's phone number
	 * @param address     the user's full address
	 * @param email       the users email address
	 */
	public Employee(String name, String birthday, String phoneNumber, String address, String email, Venue workVenue) {
		super(name, birthday, phoneNumber, address, email);
		this.setWorkVenue(workVenue);
	}

	public void inputEvent(String venueName, ArrayList<Venue> venuesToSearch, String date, String time, Show show){
		//find venue mainsystem
		Venue venueToAddEventTo = null;
		for(Venue venue : venuesToSearch)
			if(venueName.equals(venue.getName())){
				venueToAddEventTo = venue;
				break;
			}

		if(venueToAddEventTo == null){
			System.out.println("No Venue matching that name...Event not added");
		}

		//add new show to new event in found venue
		Theater[] venueTheaters = venueToAddEventTo.getTheaters();

		Event newEvent = new Event(show, date, time);
		for(Theater theater : venueTheaters)
			theater.addEvent(newEvent);

	}

	public void inputTheaterInfo(String theater, String type, String show, String date, String time){

	}

	public void inputDiscount(double discount, User user){
		for(Ticket ticket : user.getTickets())
			ticket.setDiscountMultiplier(discount/100);
	}

	/*public void inputTicketPrices(double adultPrice, double childPriceMultiplier, ){

	}*/


	public void displayRefundedTickets(){
		System.out.println("Tickets Refunded: " + ticketsToRefundToCustomers.size());
		ticketsToRefundToCustomers.forEach(System.out::println);
	}

	public void addTicket(Ticket ticket){
		if(ticket == null){
			System.out.println("Cannot add null ticket");
			return;
		}

		ticketsToRefundToCustomers.add(ticket);
	}

	public void refundTickets(String refundRequester){
		//Print name
		//make list of tickets belonging to user only
		//do magic
		//print out total tickets refunded
		//

	}

	public String toString(){
		return "Employee\tName:" + this.getName() + "\nVenue: " + this.getWorkVenue() + "\nBirthday: " + this.getBirthday() + "\nPhone:  " + this.getPhoneNumber() + "\nHome Address: " + this.getAddress() + "\nEmail: " + this.getEmail();
	}

	public Venue getWorkVenue() {
		return workVenue;
	}

	public void setWorkVenue(Venue workVenue) {
		this.workVenue = workVenue;
	}

	public ArrayList<Ticket> getTicketsToRefundToCustomers() {
		return ticketsToRefundToCustomers;
	}

	public void setTicketsToRefundToCustomers(ArrayList<Ticket> ticketsToRefundToCustomers) {
		this.ticketsToRefundToCustomers = ticketsToRefundToCustomers;
	}
}
