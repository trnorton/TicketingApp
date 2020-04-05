import java.util.ArrayList;

public class MainSystem {

	private ArrayList<Venue> venues;
	private ArrayList<User> users;
	private FilterSystem filter;
	private CustomerSupportSystem custSupport;
	private static ConcessionsSystem concess;
	private boolean online;
	
	public MainSystem() {
		venues = new ArrayList<Venue>();
		users = new ArrayList<User>();
		filter = new FilterSystem();
		custSupport = new CustomerSupportSystem();
		concess = new ConcessionsSystem();
		online = true;
		
		venues.add(new Venue("Venue", "123 Movie St", 12));
		
		users.add(new Customer("Customer", "01/01/1980", "(803)123-4567", "123 Simple St", "customer@gmail.com"));
		users.add(new Employee("Employee", "01/01/1980", "(803)123-7890", "123 Circle Dr", "employee@yahoo.com", venues.get(0)));
		for(int i = 0; i<users.size(); i++) {
			users.get(i).setAccountID(i+1);
		}
	}
	
	// TODO, but might not need this
	public void searchForEvent(String event) {
		
	}
	
	public void searchForLocation(String location) {
		for(Venue v : venues) {
			if(v.getName().equals(location)) {
				System.out.println(v);
			}
		}
	}
	
	//TODO ask about this
	private void applySearchFilters() {
		
	}
	
	//TODO ask about this
	private void applyRestrictionFilters() {
		
	}
	
	public boolean checkIfOnline() {
		return online;
	}
	
	public void displaySupportInfo() {
		System.out.println(custSupport);
	}
	
	public static Concession distributeConcessions(String name, int quantity) {
		Concession concession = new Concession(name, 0.0, quantity);;
		for(Concession c : concess.getConcessions()) {
			if(c.getName().equals(name)) {
				if(c.getQuantity() <= 0) concess.getConcessions().remove(c);
				c.setQuantity(c.getQuantity() - quantity);
				if(c.getQuantity() <= 0) concess.getConcessions().remove(c);
				concession.setPrice(c.getPrice());
			}
		}
		return concession;
		
	}
	
	public void displayConcessions() {
		concess.showConcessions();
	}
	
	public User findUser(int acctID) {
		for(User u : users) {
			if(u.getAccountID() == acctID) {
				return u;
			}
		}
		return null;
	}
	
	//TODO findEmployee
	 public ArrayList<User> getUsers(){
		 return users;
	 }
	 
	 public ArrayList<Venue> getVenues(){
		 return venues;
	 }
	 
	 public void displayAvailableTheater() {
			for(Venue v : venues) {
				if(v.hasAvailableTheater()) {
					v.getAvailableTheater().displaySeats();
				}
			}
		}
	 
	 public void addUserToDatabase(User u) {
		 users.add(u);
		 for(int i = 0; i<users.size(); i++) {
				if(users.get(i).getAccountID() == 0)
					users.get(i).setAccountID(i+1);
			}
	 }
}
