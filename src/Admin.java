import java.util.ArrayList;

/**
 * @author Seidberg Jyles Tagra
 * @author https://github.com/AkashiCat
 */
public class Admin extends User {
	private ArrayList<Venue> venueList;

	/**
	 * Parametrized constructor for Admin
	 * 
	 * @param name        the admin's name
	 * @param birthday    the admin's birthday in ddmmyyyy format
	 * @param phoneNumber the admin's phone number
	 * @param address     the admin's full address
	 * @param email       the admin's email address
	 */
	public Admin(String name, String birthday, String phoneNumber, String address, String email) {
		super(name, birthday, phoneNumber, address, email);
		venueList = new ArrayList<Venue>();
	}

	/**
	 * Method which prints out a list of venues that the admin oversees
	 */
	public void displayVenues() {
		for (Venue v : venueList) {
			System.out.println(v);
		}
	}

	/**
	 * Adds a venue to the venue list
	 * 
	 * @param venue venue to be added
	 */
	public void addVenue(String venue, ArrayList<Venue> venues) {
		for (Venue v : venues) {
			if (venue.equals(v.getName())) {
				venueList.add(v);
				return;
			}
		}
	}

	/**
	 * Removes a venue from the venue list
	 * 
	 * @param venue venue to be removed
	 */
	public void removeVenue(String venue) {
		for (Venue v : venueList) {
			if (venue.equals(v.getName())) {
				venueList.remove(v);
				return;
			}
		}
	}

	/**
	 * Allows an admin to create an event for a venue
	 * 
	 * @param venueName      The name of the venue in which to place the event
	 * @param venuesToSearch The list of venues from which to find the matching
	 *                       venue
	 * @param date           The date of the event in MM/DD/YYYY
	 * @param time           The time of the event in 12hr h:m format
	 * @param show           The show type to add
	 */
	public <T extends Show> void inputEvent(Venue venue, String date, String time, T show) {

		ArrayList<T> shows = null;
		if (show instanceof Movie)
			shows = (ArrayList<T>) JsonParser.loadMovies();
		else if (show instanceof Play)
			shows = (ArrayList<T>) JsonParser.loadPlays();
		else if (show instanceof Concert)
			shows = (ArrayList<T>) JsonParser.loadConcerts();
		else {
			System.out.println("Uh oh");
			return;
		}

		if (shows != null) {
			shows.add(show);
			JsonParser.saveData(shows);
		}

		// add new show to new event in found venue
		Theater[] venueTheaters = venue.getTheaters();

		Event newEvent = new Event(show, date, time);
		for (Theater theater : venueTheaters)
			theater.addEvent(newEvent);

	}

	/**
	 * Displays a list of user info
	 * 
	 * @return information about the admin
	 */
	public String toString() {
		return super.toString();
	}
}
