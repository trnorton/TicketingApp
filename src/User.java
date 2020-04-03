import java.util.ArrayList;

public class User {
    public static final int CURRENT_YEAR = 2020;
    public static final int MIN = 0;

    private String name;
    private int age;
    private int birthday;
    private int phoneNumber;
    private String address;
    private String email;
    // private ArrayList<Ticket> tickets;
    private ArrayList<Event> watchlist;
    private ArrayList<Concession> concessions;
    private int userDiscount;
    private int rewardsPoints;
    private int accountID;
    private Venue nearestVenue;

    public User(String name, int birthday, int phoneNumber, String address, String email) {
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

    private  int calculateAge(int birthday) {
        return 0;
    }

    // Finding locations where an event is held?
    public void searchForEvent(String event) {

    }

    public void lookAtBasicEventInfo(String event) {

    }

    public void updateHomeVenue(String venue) {

    }

    // Creating new ticket
    public void bookTickets(String event, String date, String time, int adultTickets, int childTickets) {

    }

    // Ask about implementation later
    public void createReceipt() {

    }

    public void displayWatchlist() {

    }

    public void addToWatchlist(Event event) {

    }

    public void removeFromWatchlist(Event event) {

    }

    public void purchaseTickets(Event event, int NumTix) {

    }

    private void applyRewardsPoints() {

    }

    public void purchaseTicketsDelayed(Event event, int numTix) {

    }

    /* public void displayTicket(Ticket lastPurchased) {

    } */

    public void setUserDiscount(int disc) {
        if(!(disc > 0)) {
            this.userDiscount = disc;
        }
    }

    public int getUserDiscount() {
        return this.userDiscount;
    }

    public void requestRefund(String event, int numTix) {

    }

    // Remove concession from array list?
    public void receiveConcession(Concession concession) {

    }

    public int getRewardsPoints() {
        return this.rewardsPoints;
    }

    public void setRewardsPoints(int points) {
        if (!(points < 0)) {
            this.rewardsPoints = points;
        }
    }

    public int getAccountID() {
        return this.accountID;
    }

    public void setAccountID(int id) {
        if(!(id < 0)) {
            this.accountID = id;
        }
    }

    public Venue getVenue() {
        return this.nearestVenue;
    }

    public void setVenue(Venue venue) {
        this.nearestVenue = venue;
    }

    public String toString() {
        return "placeholder";
    }
}
