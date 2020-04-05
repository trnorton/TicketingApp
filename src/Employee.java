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

	/**
	 * Allows an employee to create an event for a venue
	 * @param venueName The name of the venue in which to place the event
	 * @param venuesToSearch  The list of venues from which to find the matching venue
	 * @param date The date of the event in ddmmyy?
	 * @param time The time of the event in 12hr h:m format
	 * @param show The show type to add
	 */
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


	/*public void inputTheaterInfo(String theater, String type, String show, String date, String time){

	}*/

	/**
	 * Sets the discount of each of a user's tickets to a certain multiplier. End result price will be (price * multiplier) aka (price * (discount/100))
	 * @param discount The percentage discount to give as a double
	 * @param user The user to allow that discount
	 */
	public void inputDiscount(double discount, User user){
		user.getTickets().forEach((ticket) -> ticket.setDiscountMultiplier(discount/100));
	}

	/*public void inputTicketPrices(double adultPrice, double childPriceMultiplier, ){

	}*/

	/**
	 * Allows employee to add a ticket into the list of tickets to refund
	 * @param ticket Any ticket object
	 */
	public void addTicketToRefund(Ticket ticket){
		if(ticket == null){
			System.out.println("Cannot add null ticket");
			return;
		}

		ticketsToRefundToCustomers.add(ticket);
	}

	/**
	 * Refunds a customer's tickets and displays the refunded tickets and price
	 * @param refundRequester The String name of a user requesting a refund
	 */
	public void refundTickets(String refundRequester){
		//ArrayList<Ticket> tickets = ticketsToRefundToCustomers.stream().filter((t) -> t.getName().equals(refundRequester)).collect(Collectors.toCollection(ArrayList::new));
		//Look at what I made ^^

		ArrayList<Ticket> tickets = new ArrayList<>();
		for(Ticket t : ticketsToRefundToCustomers)
			if(t.getName().equalsIgnoreCase(refundRequester))
				tickets.add(t);

		System.out.print("Refunding "+ refundRequester + " " + tickets.size() + " tickets ");
		int totalTicketPrice = 0;
		for(Ticket t: tickets)
			totalTicketPrice += t.getPriceWithDiscount();
		System.out.println("for a total amount of " + totalTicketPrice + " dollars!!");


	}

	/**
	 * Creates string representation of Employee
	 * @return Employee Name: Luke Venue: Regal 18 Birthday: 01011987 Phone: 9078675309 Home Address: 123 Fake Street Email: bloopy@gmail.com
	 */
	public String toString(){
		return "Employee\tName:" + this.getName() + "\nVenue: " + this.getWorkVenue() + "\nBirthday: " + this.getBirthday() + "\nPhone:  " + this.getPhoneNumber() + "\nHome Address: " + this.getAddress() + "\nEmail: " + this.getEmail();
	}

	/**
	 * Returns the venue the employee works at
	 * @return A venue object
	 */
	public Venue getWorkVenue() {
		return workVenue;
	}

	/**
	 * Sets the venue which the employee works at
	 * @param workVenue A venue object to assign to the employee
	 */
	public void setWorkVenue(Venue workVenue) {
		this.workVenue = workVenue;
	}

	/**
	 * An arraylist of tickets that will be refunded
	 * @return The arraylist of ticket objects to be refunded
	 */
	public ArrayList<Ticket> getTicketsToRefundToCustomers() {
		return ticketsToRefundToCustomers;
	}

	/**
	 * Allows assigning of an arraylist of ticket objects to an employee
	 * @param ticketsToRefundToCustomers An arraylist of ticket objects to assign to an employee
	 */
	public void setTicketsToRefundToCustomers(ArrayList<Ticket> ticketsToRefundToCustomers) {
		this.ticketsToRefundToCustomers = ticketsToRefundToCustomers;
	}
}
