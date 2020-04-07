import java.util.ArrayList;

/**
 * Representation of a venue, a location where shows are held
 * 
 * @author Taylor Norton
 */
public class Venue {

	private String name;
	private String address;
	private Theater[] theaters;
	private ArrayList<String> reviews;
	private ArrayList<Integer> custRatings;
	private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private static final int standardNumOfRows = 25;
	private static final int standardNumOfCols = 30;

	/**
	 * Parametrized constructor for Venue
	 * 
	 * @param name          - name of the venue
	 * @param address       - address of the venue
	 * @param numOfTheaters - number of theaters at that venue
	 */
	public Venue(String name, String address, int numOfTheaters) {
		this.name = name;
		this.address = address;
		theaters = new Theater[numOfTheaters];
		for (int i = 0; i < theaters.length; i++) {
			theaters[i] = new Theater(alphabet[i], standardNumOfRows, standardNumOfCols);
		}
		reviews = new ArrayList<String>();
		custRatings = new ArrayList<Integer>();
	}

	/**
	 * @return String - the venue's current name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name - the venue's new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return String - the venue's current address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address - the venue's new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Method which prints out the current list of user reviews for the venue
	 */
	public void displayReviews() {
		for (String r : reviews) {
			System.out.println(r);
		}
	}

	/**
	 * Method which adds a user review to the venue's review list
	 * 
	 * @param r - the review to be added to the review list
	 */
	public void addReview(String r) {
		reviews.add(r);
	}

	/**
	 * Method which removes a user review from the venue's review list
	 * 
	 * @param r - review to be removed from the review list
	 */
	public void removeReview(String r) {
		reviews.remove(r);
	}

	/**
	 * Method which prints out the current list of customer ratings for the venue
	 */
	public void displayCustRatings() {
		for (int r : custRatings) {
			System.out.println(r);
		}
	}

	/**
	 * Method which adds a customer rating to the venue's rating list
	 * 
	 * @param r - the customer rating to be added to the rating list
	 */
	public void addCustRating(int r) {
		custRatings.add(r);
	}

	/**
	 * Method which removes a customer rating from the venue's rating list
	 * 
	 * @param r - the customer rating to be removed from the rating list
	 */
	public void removeCustRating(int r) {
		custRatings.remove(r);
	}

	private int getAverageCustRating() {
		int sum = 0;
		for (int r : custRatings) {
			sum += r;
		}
		try {
			return sum / custRatings.size();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * @return Theater[] - the list of theatres available at the venue
	 */
	public Theater[] getTheaters() {
		return theaters;
	}

	/**
	 * Method which checks if an event is showing at one of the theatres
	 * 
	 * @param e - event to be searched
	 * @return boolean - true if the event is showing at one of the theatres, false
	 *         otherwise
	 */
	public boolean hasAvailableTheater(Event e) {
		for (int i = 0; i < theaters.length; i++) {
			if (theaters[i].getEvent(e) == null)
				continue;
			if (theaters[i].hasAvailableSeat()
					&& e.toString().trim().equalsIgnoreCase(theaters[i].getEvent(e).toString()))
				return true;
		}
		return false;
	}

	/**
	 * Method which shows which theatre an event is showing in
	 * 
	 * @param e - event to be searched
	 * @return Theater - the theatre that is showing the event e
	 */
	public Theater getAvailableTheater(Event e) {
		for (int i = 0; i < theaters.length; i++) {
			if (theaters[i].getEvent(e) == null)
				continue;
			if (theaters[i].hasAvailableSeat()
					&& e.toString().trim().equalsIgnoreCase(theaters[i].getEvent(e).toString()))
				return theaters[i];
		}
		return null;
	}

	/**
	 * @return String - Basic description of the venue, including name, address, and
	 *         average rating
	 */
	public String toString() {
		return "Name: " + name + "\nAddress: " + address + "\nOverall Customer Rating: " + getAverageCustRating();
	}
}
