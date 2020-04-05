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
    public Admin(String name, String birthday, String phoneNumber, String address, String email) {
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
    public void addVenue(String venue, ArrayList<Venue> venues) {
        for(Venue v : venues) {
        	if(venue.equals(v.getName())) {
        		venueList.add(v);
        		return;
        	}
        }
    }

    /**
     * Removes a venue from the venue list
     * @param venue venue to be removed
     */
    public void removeVenue(String venue) {
        for(Venue v : venueList) {
        	if(venue.equals(v.getName())) {
        		venueList.remove(v);
        		return;
        	}
        }
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

    /**
     * Displays a list of user info
     * @return information about the admin
     */
    public String toString() {
        return super.toString();
    }
}
