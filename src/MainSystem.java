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
		
		loadSampleData();
		
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
	 
	 public void displayAvailableTheater(Event e) {
			for(Venue v : venues) {
				if(v.hasAvailableTheater(e)) {
					v.getAvailableTheater(e).displaySeats();
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

	public void displayAvailableTheater(String event, String date, String time) {
		Show s = null;
		ArrayList<Movie> movies = JsonParser.loadMovies();
        for(Movie m : movies) {
        	if(event.equals(m.getName())) {
        		s = m;
        		break;
        	}
        }
        
        ArrayList<Play> plays = JsonParser.loadPlays();
        for(Play p : plays) {
        	if(event.equals(p.getName())) {
        		s = p;
        		break;
        	}
        }
        
        ArrayList<Concert> concerts = JsonParser.loadConcerts();
        for(Concert c : concerts) {
        	if(event.equals(c.getName())) {
        		s = c;
        		break;
        	}
        }
        Event e = new Event(s, date, time);
		
		for(Venue v : venues) {
			if(v.getAvailableTheater(e).getEvent(e).getShow().getName().equals(event)) {
				v.getAvailableTheater(e).displaySeats();
				break;
			}
		}
		
	}
	
	private void loadSampleData() {
		venues.add(new Venue("Venue", "123 Movie St", 12));
		venues.add(new Venue("PlaysRUs", "123 Play St", 12));
		for(int i = 0; i<venues.get(0).getTheaters().length;i++) {
			if(i<5) {
				ArrayList<Movie> movies = JsonParser.loadMovies();
				Movie movie = null;
				for(Movie m : movies) {
					if(m.getName().equals("Frozen 2"))
						movie = m;
				}
				Event e = new Event(movie, "04/10/2020", "12:00pm");
				venues.get(0).getTheaters()[i].addEvent(e);
			}
			else {
				ArrayList<Movie> movies = JsonParser.loadMovies();
				Movie movie = null;
				for(Movie m : movies) {
					if(m.getName().equals("The Jungle Book"))
						movie = m;
				}
				Event e = new Event(movie, "04/10/2020", "12:00pm");
				venues.get(0).getTheaters()[i].addEvent(e);
			}
		}
		
		users.add(new Customer("Customer", "01/01/1980", "(803)123-4567", "123 Simple St", "customer@gmail.com"));
		users.add(new Employee("Employee", "01/01/1980", "(803)123-7890", "123 Circle Dr", "employee@yahoo.com", venues.get(0)));
		users.add(new Admin("Admin", "01/10/1980", "(803)456-7890", "456 Tree Rd", "admin@aol.com"));
		for(int i = 0; i<users.size(); i++) {
			users.get(i).setAccountID(i+1);
		}
		users.get(1).setVenue(venues.get(1));
	}
}
