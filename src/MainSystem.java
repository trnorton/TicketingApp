import java.util.ArrayList;

/**
 * MainSystem.java - A class to represent the main system of this app.
 * 
 * @author Taylor Norton
 *
 */
public class MainSystem {

	private ArrayList<Venue> venues;
	private ArrayList<User> users;
	private CustomerSupportSystem custSupport;
	private static ConcessionsSystem concess;

	/**
	 * Default constructor.
	 */
	public MainSystem() {
		venues = new ArrayList<Venue>();
		users = new ArrayList<User>();
		custSupport = new CustomerSupportSystem();
		concess = new ConcessionsSystem();

		loadSampleData();

	}

	/**
	 * Prints out the toString of the Venue that has a match between its name and
	 * the given String.
	 * 
	 * @param location - A variable of type String.
	 */
	public void searchForLocation(String location) {
		for (Venue venue : venues) {
			if (venue.getName().trim().equalsIgnoreCase(location)) {
				System.out.println(venue);
			}
		}
	}

	/**
	 * Prints out the customer support page.
	 */
	public void displaySupportInfo() {
		System.out.println(custSupport);
	}

	/**
	 * Returns the desired Concession from the concessions system to a user's
	 * possession.
	 * 
	 * @param name     - A variable of type String.
	 * @param quantity - A variable of type int.
	 * @return Concession - The Uuer's desired Concession
	 */
	public static Concession distributeConcessions(String name, int quantity) {
		Concession concession = new Concession(name, 0.0, quantity);
		;
		for (Concession c : concess.getConcessions()) {
			if (c.getName().equals(name)) {
				if (c.getQuantity() <= 0)
					concess.getConcessions().remove(c);
				c.setQuantity(c.getQuantity() - quantity);
				if (c.getQuantity() <= 0)
					concess.getConcessions().remove(c);
				concession.setPrice(c.getPrice());
			}
		}
		return concession;

	}

	/**
	 * Prints out the Concessions in the concessions system.
	 */
	public void displayConcessions() {
		concess.showConcessions();
	}

	/**
	 * Returns the User that matches with the given account ID.
	 * 
	 * @param acctID - A variable of type int.
	 * @return User - The User with the same account ID.
	 */
	public User findUser(int acctID) {
		for (User user : users) {
			if (user.getAccountID() == acctID) {
				return user;
			}
		}
		return null;
	}

	/**
	 * Returns the list of existing users in the main system.
	 * 
	 * @return ArrayList<User> - The list of existing users in the main system.
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * Returns the list of existing venues in the main system.
	 * 
	 * @return ArrayList<Venue> - The list of existing venues in the main system.
	 */
	public ArrayList<Venue> getVenues() {
		return venues;
	}

	/**
	 * Prints out the seating map of the Theater showing the given Event.
	 * 
	 * @param e - A variable of type Event.
	 */
	public void displayAvailableTheater(Event event) {
		for (Venue venue : venues) {
			if (venue.hasAvailableTheater(event)) {
				venue.getAvailableTheater(event).displaySeats();
			}
		}
	}

	/**
	 * Adds the given User the list of exisiting users in the main system.
	 * 
	 * @param u - A variable of type User.
	 */
	public void addUserToDatabase(User user) {
		users.add(user);
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getAccountID() == 0)
				users.get(i).setAccountID(i + 1);
		}
	}

	/**
	 * Prints out the seating map of the Theater showing the Event of the same given
	 * name, date, and time.
	 * 
	 * @param event - A variable of type Event.
	 * @param date  - A variable of type String.
	 * @param time  - A variable of type String.
	 */
	public void displayAvailableTheater(String eventName, String date, String time) {
		Show show = null;
		ArrayList<Movie> movies = JsonParser.loadMovies();
		for (Movie movie : movies) {
			if (eventName.trim().equalsIgnoreCase(movie.getName())) {
				show = movie;
				break;
			}
		}

		ArrayList<Play> plays = JsonParser.loadPlays();
		for (Play play : plays) {
			if (eventName.trim().equalsIgnoreCase(play.getName())) {
				show = play;
				break;
			}
		}

		ArrayList<Concert> concerts = JsonParser.loadConcerts();
		for (Concert concert : concerts) {
			if (eventName.trim().equalsIgnoreCase(concert.getName())) {
				show = concert;
				break;
			}
		}
		Event event = new Event(show, date, time);

		for (Venue venue : venues) {
			if (venue.getAvailableTheater(event).getEvent(event).getShow().getName().trim().equalsIgnoreCase(eventName)) {
				venue.getAvailableTheater(event).displaySeats();
				break;
			}
		}

	}

	private void loadSampleData() {
		venues.add(new Venue("Venue", "123 Movie St", 12));
		venues.add(new Venue("PlaysRUs", "123 Play St", 12));
		for (int i = 0; i < venues.get(0).getTheaters().length; i++) {
			if (i < 5) {
				ArrayList<Movie> movies = JsonParser.loadMovies();
				Movie movie = null;
				for (Movie m : movies) {
					if (m.getName().equals("Frozen 2"))
						movie = m;
				}
				Event e = new Event(movie, "04/10/2020", "12:00pm");
				venues.get(0).getTheaters()[i].addEvent(e);
			} else {
				ArrayList<Movie> movies = JsonParser.loadMovies();
				Movie movie = null;
				for (Movie m : movies) {
					if (m.getName().equals("The Jungle Book"))
						movie = m;
				}
				Event e = new Event(movie, "04/10/2020", "12:00pm");
				venues.get(0).getTheaters()[i].addEvent(e);
			}
		}

		users.add(new Customer("Customer", "01/01/1980", "(803)123-4567", "123 Simple St", "customer@gmail.com"));
		users.add(new Employee("Employee", "01/01/1980", "(803)123-7890", "123 Circle Dr", "employee@yahoo.com",
				venues.get(0)));
		users.add(new Admin("Admin", "01/10/1980", "(803)456-7890", "456 Tree Rd", "admin@aol.com"));
		for (int i = 0; i < users.size(); i++) {
			users.get(i).setAccountID(i + 1);
		}
		users.get(1).setVenue(venues.get(1));
	}
}
