import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Seidberg Jyles Tagra
 * @author https://github.com/AkashiCat
 */
public class User {
    public static final int DIGIT4_MOD = 10000;
    public static final int DIGIT2_MOD = 100;
    public static final int MIN = 0;

    private String name;
    private int age;
    private String birthday;
    private String phoneNumber;
    private String address;
    private String email;
    private ArrayList<Ticket> tickets;
    private ArrayList<Event> watchlist;
    private ArrayList<Concession> concessions;
    private double userDiscount;
    private int rewardsPoints;
    private int accountID;
    private Venue nearestVenue;

    /**
     * Parametrized constructor for User
     * @param name the user's name
     * @param birthday the user's birthday in mmddyyyy format
     * @param phoneNumber the user's phone number
     * @param address the user's full address
     * @param email the users email address
     */
    public User(String name, String birthday, String phoneNumber, String address, String email) {
        this.name = name;
        this.birthday = birthday;
        this.age = calculateAge(this.birthday);
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        // this.tickets = new ArrayList<Tickets>();
        this.watchlist = new ArrayList<Event>();
        this.concessions = new ArrayList<Concession>();
        this.userDiscount = MIN;
        this.rewardsPoints = MIN;
        this.accountID = MIN;

    }

    /**
     * Private method to calculate the age
     * @param birthday the user's birthday in mmddyyyy format (month/day/year)
     * @return age of the user in years
     */
    private int calculateAge(String birthday) {
        LocalDate date = LocalDate.now();

        String[] split = birthday.split("/");
        
        int year = Integer.parseInt(split[2]);
        int month = Integer.parseInt(split[0]);
        int day = Integer.parseInt(split[1]);
        /*int year = birthday % DIGIT4_MOD;
        int month = (birthday / DIGIT4_MOD) % DIGIT2_MOD;
        int day = ((birthday / DIGIT4_MOD) / DIGIT2_MOD) % DIGIT2_MOD;*/

        int age = date.getYear() - year;

        if(month < date.getMonthValue() || (month == date.getMonthValue() && day < date.getDayOfMonth())) {
            age--;
        }

        return age;
    }


    public void searchForEvent(String event) {

    }

    public void lookAtBasicEventInfo(String event) {

    }

    // Ask about it later
    public void updateHomeVenue(String venue) {

    }

    // Creating new ticket
    public void bookTickets(String event, String date, String time, int adultTickets, int childTickets) {

    }

    // Ask about implementation later
    public void createReceipt() {
    	
    }

    /**
     * Method which shows the user's current watchlist
     * Prints out each event in the list
     */
    public void displayWatchlist() {
        for (Event e : watchlist) {
            System.out.println(e);
        }
    }

    /**
     * Method which adds an event to the user's watchlist
     * @param event event to be added
     */
    public void addToWatchlist(Event event) {
        if (event != null) {
            watchlist.add(event);
        }
    }

    /**
     * Method which removes an event from the user's watchlist
     * @param event event to be removed
     */
    public void removeFromWatchlist(Event event) {
        watchlist.remove(event);
    }

    public void purchaseTickets(Event event, int NumTix) {

    }

    private void applyRewardsPoints() {

    }

    public void purchaseTicketsDelayed(Event event, int numTix) {

    }

    /* public void displayTicket(Ticket lastPurchased) {

    } */

    /**
     * Method which sets the user's purchase discount
     * @param disc the discount to be applied
     */
    public void setUserDiscount(double disc) {
        if(!(disc < 0)) {
            this.userDiscount = disc;
        }
    }

    /**
     * Standard getter for userDiscount
     * @return the current user's discount
     */
    public double getUserDiscount() {
        return this.userDiscount;
    }

    // No idea what this does yet
    public void requestRefund(String event, int numTix) {

    }

    /**
     * Method which removes a concession from the list, assuming that it is given to the user
     * @param concession concession to be removed from the list
     */
    public void receiveConcession(Concession concession) {
        concessions.remove(concession);
    }

    /**
     * Standard getter for the user's rewards points
     * @return the user's current rewards points total
     */
    public int getRewardsPoints() {
        return this.rewardsPoints;
    }

    /**
     * Standard setter for the user's rewards points
     * Cannot be negative
     * @param points new user points total
     */
    public void setRewardsPoints(int points) {
        if (!(points < 0)) {
            this.rewardsPoints = points;
        }
    }

    /**
     * Standard getter for the user's account ID
     * @return the user's account ID
     */
    public int getAccountID() {
        return this.accountID;
    }

    /**
     * Standard setter for the user's account ID
     * Cannot be negative
     * @param id the user's new ID
     */
    public void setAccountID(int id) {
        if(!(id < 0)) {
            this.accountID = id;
        }
    }

    /**
     * Standard getter for the user's nearest venue
     * @return the user's nearest venue
     */
    public Venue getVenue() {
        return this.nearestVenue;
    }

    /**
     * Standard setter for the user's nearest venue
     * @param venue the user's new nearest venue
     */
    public void setVenue(Venue venue) {
        this.nearestVenue = venue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return a summary of the user's information
     */
    public String toString() {
        return "placeholder";
    }
}
