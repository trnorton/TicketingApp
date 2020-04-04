import java.util.ArrayList;

/**
 * @author Seidberg Jyles Tagra
 * @author https://github.com/AkashiCat
 */
public class Admin extends User {
    private ArrayList<Venue> venueList;

    /**
     * Parametrized constructor for Admin
     * @param name the admin's name
     * @param birthday the admin's birthday in ddmmyyyy format
     * @param phoneNumber the admin's phone number
     * @param address the admin's full address
     * @param email the admin's email address
     */
    public Admin(String name, int birthday, int phoneNumber, String address, String email) {
        super(name, birthday, phoneNumber, address, email);
        venueList = new ArrayList<Venue>();
    }

    /**
     * Method which prints out a list of venues that the admin oversees
     */
    public void displayVenues() {
        for(Venue v : venueList) {
            System.out.println(v);
        }
    }

    /**
     * Adds a venue to the venue list
     * @param venue venue to be added
     */
    public void addVenue(Venue venue) {
        venueList.add(venue);
    }

    /**
     * Removes a venue from the venue list
     * @param venue venue to be removed
     */
    public void removeVenue(Venue venue) {
        venueList.remove(venue);
    }

    // Probably need json for this?
    public void inputEvent(String type, String name, String date, String time, int rating, int ageRating) {

    }

    /**
     * Displays a list of user info
     * @return information about the admin
     */
    public String toString() {
        return super.toString();
    }
}
