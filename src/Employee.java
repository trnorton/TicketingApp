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
	public Employee(String name, int birthday, int phoneNumber, String address, String email, Venue workVenue) {
		super(name, birthday, phoneNumber, address, email);
		this.setWorkVenue(workVenue);
	}

	public void inputEvent(String type, String name, String date, String time, int rating, int ageRating){

	}

	public void inputTheaterInfo(String theater, String type, String show, String date, String time){

	}

	public void inputDiscount(int discount){

	}

	public void inputTicketPrices(double adult, double child){

	}

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
