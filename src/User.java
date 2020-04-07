import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Representation of a user of the ticketing app and all the functionality available to users
 * If invoked directly, this class will be used for guest users
 * @author Seidberg Jyles Tagra
 * @author https://github.com/AkashiCat
 */
public class User {
    public static final int MIN = 0;
    private String name;
    private int age;
    private String birthday;
    private String phoneNumber;
    private String address;
    private String email;
    private ArrayList<Ticket> tickets;
    private ArrayList<Show> watchlist;
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
        this.tickets = new ArrayList<Ticket>();
        this.watchlist = new ArrayList<Show>();
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

        int age = date.getYear() - year;

        if(month < date.getMonthValue() || (month == date.getMonthValue() && day < date.getDayOfMonth())) {
            age--;
        }

        return age;
    }

    /**
     * Method which searches the movie database for a specific movie
     * @param movieName name of the movie to be searched for
     * @return a Movie that has a name matching the parameter, if any
     */
    public Movie searchForMovie(String movieName) {
		if(movieName == null){
			System.out.println("Null movie name given");
			return null;
		}

    	ArrayList<Movie> movies = JsonParser.loadMovies();
		if(movies == null || movies.isEmpty()){
			System.out.println("List of movies is empty or doesn't exist");
			return null;
		}

        for(Movie movie : movies) {
        	if(movieName.trim().equalsIgnoreCase(movie.getName())) {
        		return movie;
        	}
        }

        return null;
    }

    /**
     * Method which searches the play database for a specific play
     * @param playName name of the play to be searched for
     * @return a Play that has a name matching the parameter, if any
     */
    public Play searchForPlay(String playName) {
		if(playName == null){
			System.out.println("Null play name given");
			return null;
		}

    	ArrayList<Play> plays = JsonParser.loadPlays();
		if(plays == null || plays.isEmpty()){
			System.out.println("List of plays is empty or doesn't exist");
			return null;
		}

        for(Play play : plays) {
        	if(playName.trim().equalsIgnoreCase(play.getName())) {
        		return play;
        	}
        }

        return null;
    }

    /**
     * Method which searches the concert database for a specific play
     * @param concertName name of the concert to be searched for
     * @return a Concert that has a name matching the parameter, if any
     */
    public Concert searchForConcert(String concertName) {
    	if(concertName == null){
			System.out.println("Null concert name given");
			return null;
		}

    	ArrayList<Concert> concerts = JsonParser.loadConcerts();
    	if(concerts == null || concerts.isEmpty()){
			System.out.println("List of concerts is empty or doesn't exist");
			return null;
		}

        for(Concert concert : concerts) {
        	if(concertName.trim().equalsIgnoreCase(concert.getName())) {
        		return concert;
        	}
        }

        return null;
    }

    /**
     * Method which searches a list of venues for a specific venue
     * @param venue name of venue to be searched for
     * @param venueList list of venues being searched
     * @return a Venue with a name that matches the venue parameter, if any
     */
    public Venue searchForVenue(String venue, ArrayList<Venue> venueList) {
    	if(venue == null){
    		System.out.println("Null venue name given");
    		return null;
		}

		if(venueList == null || venueList.isEmpty()){
			System.out.println("List of venues is empty or doesn't exist");
			return null;
		}

    	for(Venue v : venueList) {
    		if(venue.trim().equalsIgnoreCase(v.getName())) {
    			return v;
    		}
    	}

    	return null;
    }

    /**
     * Method which displays the information of any event that matches the parameter
     * @param event name of the event to be searched
     */
    public void lookAtBasicEventInfo(String event) {
    	ArrayList<Movie> movies = JsonParser.loadMovies();
        for(Movie m : movies) {
        	if(event.trim().equalsIgnoreCase(m.getName())) {
        		System.out.println(m);
        		return;
        	}
        }
        
        ArrayList<Play> plays = JsonParser.loadPlays();
        for(Play p : plays) {
        	if(event.trim().equalsIgnoreCase(p.getName())) {
        		System.out.println(p);
        		return;
        	}
        }
        
        ArrayList<Concert> concerts = JsonParser.loadConcerts();
        for(Concert c : concerts) {
        	if(event.trim().equalsIgnoreCase(c.getName())) {
        		System.out.println(c);
        		return;
        	}
        }
    }

    /**
     * Method which attempts to update the user's home venue, if it exists
     * @param venue name of the new home venue
     * @param venues list of venues to be searched for
     */
    public void updateHomeVenue(String venue, ArrayList<Venue> venues) {
    	for(Venue v : venues) {
    		if(venue.trim().equalsIgnoreCase(v.getName()))
    			nearestVenue = v;
    	}
    }

    /**
     * Method which creates new tickets with specific seat numbers
     * @param eventName name of the event the user is booking tickets for
     * @param date date of the event showing
     * @param time time of the event showing
     * @param adultTickets number of tickets for adults being purchased
     * @param childTickets number of tickets for children being purchased
     * @param seatRow the desired row in the seating arrangement
     * @param seatCol the desired column in the seating arrangement
     */
    public void bookTickets(String eventName, String date, String time, int adultTickets, int childTickets, char seatRow, int seatCol) {
    	for(int i = 0; i<adultTickets+childTickets; i++) {
    		Show show = null;
    		ArrayList<Movie> movies = JsonParser.loadMovies();
            for(Movie m : movies) {
            	if(eventName.trim().equalsIgnoreCase(m.getName())) {
            		show = m;
            		break;
            	}
            }
            
            ArrayList<Play> plays = JsonParser.loadPlays();
            for(Play play : plays) {
            	if(eventName.trim().equalsIgnoreCase(play.getName())) {
            		show = play;
            		break;
            	}
            }
            
            ArrayList<Concert> concerts = JsonParser.loadConcerts();
            for(Concert concert : concerts) {
            	if(eventName.trim().equalsIgnoreCase(concert.getName())) {
            		show = concert;
            		break;
            	}
            }
            Event event = new Event(show, date, time);
            Theater theater = new Theater('A', 25, 30);
            Seat seat = new Seat('a', 1);
            if(event.toString().trim().equalsIgnoreCase(nearestVenue.getAvailableTheater(event).getEvent(event).toString())) {
            	theater = nearestVenue.getAvailableTheater(event);
            	event = theater.getEvent(event);
            	seat = theater.getSeat(seatRow, seatCol);
            	seat.changeSeatAvailability();
            }
            Ticket ticket = new Ticket(this.name, 5.00, event, seat, theater, nearestVenue);
            tickets.add(ticket);
            applyRewardsPoints();
    	}
    }

    /**
     * Method for booking tickets without seat numbers on them
     * @param eventName name of the event the user is booking tickets for
     * @param date date of the event showing
     * @param time time of the event showing
     * @param adultTickets number of tickets for adults to be purchased
     * @param childTickets number of tickets for children to be purchased
     */
    public void bookTickets(String eventName, String date, String time, int adultTickets, int childTickets) {
    	for(int i = 0; i<adultTickets+childTickets; i++) {
    		Show show = null;
    		ArrayList<Movie> movies = JsonParser.loadMovies();
            for(Movie m : movies) {
            	if(eventName.trim().equalsIgnoreCase(m.getName())) {
            		show = m;
            		break;
            	}
            }
            
            ArrayList<Play> plays = JsonParser.loadPlays();
            for(Play play : plays) {
            	if(eventName.trim().equalsIgnoreCase(play.getName())) {
            		show = play;
            		break;
            	}
            }
            
            ArrayList<Concert> concerts = JsonParser.loadConcerts();
            for(Concert concert : concerts) {
            	if(eventName.trim().equalsIgnoreCase(concert.getName())) {
            		show = concert;
            		break;
            	}
            }
            Event event = new Event(show, date, time);
            Theater theater = new Theater('A', 25, 30);
            Seat seat = new Seat('a', 1);
            if(event.toString().trim().equalsIgnoreCase(nearestVenue.getAvailableTheater(event).getEvent(event).toString())) {
            	theater = nearestVenue.getAvailableTheater(event);
            	event = theater.getEvent(event);
            	seat = theater.getAvailableSeat();
            	seat.changeSeatAvailability();
            }
            Ticket ticket = new Ticket(this.name, 5.00, event, seat, theater, nearestVenue);
            tickets.add(ticket);
            applyRewardsPoints();
    	}
    }

    //TODO .txt file doesn't always appear
    /**
     * Method which takes all of the user's current tickets and concessions and adds up a total price for all of them
     * The sum, ticket information, and concession information are put into a .txt file
     */
    public void createReceipt() {
        try {
        	PrintWriter receiptWriter = new PrintWriter(new File("receipt.txt"));
            receiptWriter.println("********** Receipt **********");
            double totalPrice = 0;
            for (Ticket t : tickets) {
                receiptWriter.println(t);
                totalPrice += t.getPrice();
            }
            for (Concession c : concessions) {
                if(concessions.size() == 0)
                	break;
            	receiptWriter.println(c);
                totalPrice += c.getPrice();
            }
            receiptWriter.println("Total price: $" + totalPrice);
            receiptWriter.close();
        }
        catch(Exception e) {
           System.out.println("Error: Receipt unable to print");
        }
    }

    /**
     * Method which allows the user to post a rating for a specific event
     * @param event name of the event the user is rating
     * @param rating number from 1-10 which represents the user's rating of the event
     */
    public void rateEvent(String event, int rating) {
    	ArrayList<Movie> movies = JsonParser.loadMovies();
        for(Movie m : movies) {
        	if(event.trim().equalsIgnoreCase(m.getName())) {
        		m.addCustRating(rating);
        		JsonParser.saveData(movies);
        		return;
        	}
        }
        
        ArrayList<Play> plays = JsonParser.loadPlays();
        for(Play p : plays) {
        	if(event.trim().equalsIgnoreCase(p.getName())) {
        		p.addCustRating(rating);
        		JsonParser.saveData(plays);
        		return;
        	}
        }
        
        ArrayList<Concert> concerts = JsonParser.loadConcerts();
        for(Concert c : concerts) {
        	if(event.trim().equalsIgnoreCase(c.getName())) {
        		c.addCustRating(rating);
        		JsonParser.saveData(concerts);
        		return;
        	}
        }
    }

    /**
     * Method which allows the user to write a review for a specific event
     * @param event name of the event being reviewed
     * @param review content of the event review
     */
    public void writeEventReview(String event, String review) {
    	ArrayList<Movie> movies = JsonParser.loadMovies();
        for(Movie m : movies) {
        	if(event.trim().equalsIgnoreCase(m.getName())) {
        		m.addReview(review);
        		JsonParser.saveData(movies);
        		return;
        	}
        }
        
        ArrayList<Play> plays = JsonParser.loadPlays();
        for(Play p : plays) {
        	if(event.trim().equalsIgnoreCase(p.getName())) {
        		p.addReview(review);
        		JsonParser.saveData(plays);
        		return;
        	}
        }
        
        ArrayList<Concert> concerts = JsonParser.loadConcerts();
        for(Concert c : concerts) {
        	if(event.trim().equalsIgnoreCase(c.getName())) {
        		c.addReview(review);
        		JsonParser.saveData(concerts);
        		return;
        	}
        }
    }

    /**
     * Method which shows the user's current watchlist
     * Prints out each event in the list
     */
    public void displayWatchlist() {
        for (Show e : watchlist) {
            System.out.println(e);
        }
    }

    /**
     * Method which shows the user's current list of tickets
     * Prints out information about each ticket in the list
     */
    public void displayTickets() {

    	if(tickets == null || tickets.isEmpty()){
    		System.out.println("\nNo tickets to display\n");
    		return;
		}

    	for (Ticket t : tickets) {
    		System.out.println(t);
    	}
    }

    /**
     * Method which allows the user to request a refund for any tickets in their list
     * @param event name of the event the user is refunding tickets from
     * @param numTicketsRefunded number of tickets to be refunded.
     */
    public void requestRefund(String event, int numTicketsRefunded) {
    	
    	int ticketCount = 0;
    	for(Ticket ticket: tickets) {
    		if(ticket.getEvent().getShow().getName().trim().equalsIgnoreCase(event)) {
    			tickets.remove(ticket);
    			ticketCount++;
    			if(ticketCount == numTicketsRefunded)
    				break;
    		}
    	}
    }

    /**
     * Method which adds a show to the user's watchlist
     * @param show show to be added
     */
    public void addToWatchlist(String show) {
        ArrayList<Movie> movies = JsonParser.loadMovies();
        for(Movie m : movies) {
        	if(show.trim().equalsIgnoreCase(m.getName())) {
        		watchlist.add(m);
        		return;
        	}
        }
        
        ArrayList<Play> plays = JsonParser.loadPlays();
        for(Play p : plays) {
        	if(show.trim().equalsIgnoreCase(p.getName())) {
        		watchlist.add(p);
        		return;
        	}
        }
        
        ArrayList<Concert> concerts = JsonParser.loadConcerts();
        for(Concert c : concerts) {
        	if(show.trim().equalsIgnoreCase(c.getName())) {
        		watchlist.add(c);
        		return;
        	}
        }
    }

    /**
     * Method which removes a show from the user's watchlist
     * @param showName show to be removed
     */
    public void removeFromWatchlist(String showName) {
		watchlist.removeIf(show -> show.getName().equals(showName));
    }

    /**
     * Method which adds a rewards point to the user's rewards points total
     */
    private void applyRewardsPoints() {
    	rewardsPoints++;
    }

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

    /**
     * Calls the method in Main System that distributes the user's concessions
     * @param type type of concessions to be distributed
     * @param quantity number of concessions of that specific type to be distributed
     */
    public void receiveConcessions(String type, int quantity) {
    	MainSystem.distributeConcessions(type, quantity);
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
     * @return the user's current nearest venue
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

    /**
     * Standard getter for the user's name
     * @return the user's current name
     */
    public String getName() {
        return name;
    }

    /**
     * Standard setter for the user's name
     * @param name the user's new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Standard getter for the user's age
     * @return the user's current age
     */
    public int getAge() {
        return age;
    }

    /**
     * Standard setter for the user's age
     * @param age the user's new age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Standard getter for the user's birthday
     * @return the user's current birthday in dd/mm/yyyy format
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Standard setter for the user's birthday
     * @param birthday the user's new birthday. Please don't make a mistake when entering your birthday the first time
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * Standard getter for the user's phone number
     * @return the user's current phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Standard setter for the user's phone number
     * @param phoneNumber the user's new phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Standard getter for the user's address
     * @return the user's current address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Standard setter for the user's address
     * @param address the user's new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Standard getter for the user's email address
     * @return the user's current email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Standard setter for the user's email address
     * @param email the user's new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Standard getter for the user's list of tickets
     * @return the user's list of tickets
     */
    public ArrayList<Ticket> getTickets() {
        return this.tickets;
    }

    /**
     * Method which adds a ticket to the user's list of tickets
     * @param ticket ticket to be added
     */
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    /**
     * Method which removes a ticket from the user's list of tickets
     * @param ticket ticket to be removed
     */
    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
    }

    /**
     * Standard getter for the user's watchlist
     * @return the user's current watchlist
     */
    public ArrayList<Show> getWatchlist() {
        return watchlist;
    }

    /**
     * Method which adds a show to the user's watchlist
     * @param show show to be added to the watchlist
     */
    public void addToList(Show show) {
        watchlist.add(show);
    }

    /**
     * Method which removes a show from the user's watchlist
     * @param show show to be added to the watchlist
     */
    public void removeFromList(Show show) {
        watchlist.remove(show);
    }

    /**
     * Standard getter for the user's list of concessions
     * @return the user's current list of concessions
     */
    public ArrayList<Concession> getConcessions() {
        return this.concessions;
    }

    /**
     * Method which adds a concession to the user's list of concessions
     * @param conc concession to be added to the list
     */
    public void addConcession(Concession conc) {
        concessions.add(conc);
    }

    /**
     * Method which removes a concession from the user's list of concessions
     * @param conc concession to be removed from the list
     */
    public void removeConcession(Concession conc) {
        concessions.remove(conc);
    }

    /**
     * @return a summary of the user's information
     */
    public String toString() {
        return "placeholder";
    }
}
