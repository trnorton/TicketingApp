import java.util.ArrayList;
import java.util.Scanner;

/**
 * TicketingAppUI.java - Represents the driver class for this app.
 * 
 * @author Taylor Norton
 *
 */
public class TicketingAppUI {

	private static final String WELCOME_MESSAGE = "Welcome to our Ticketing App";
	private Scanner scanner;
	private String nextLine;
	private int comm;
	private User user;
	MainSystem main;
	private ArrayList<String> mainMenuOptions;
	private String[] watchlistMenuOptions = { "Add to watchlist", "Remove from watchlist", "Back" };
	private String[] venueMenuOptions = { "Add to venue list", "Remove from venue list", "Back" };
	private String[] showSearchMenuOptions = { "Display info", "Book tickets", "Rate", "Write review",
			"Add to watchlist", "Return to main menu" };
	private String[] venueSearchMenuOptions = { "Make this venue your home venue", "Return to main menu" };

	TicketingAppUI() {
		scanner = new Scanner(System.in);
		main = new MainSystem();
		mainMenuOptions = new ArrayList<String>();
		fillBasicMainMenuOptions();
	}

	/**
	 * Makes the this ticketing app run.
	 */
	public void run() {
		System.out.println(WELCOME_MESSAGE);

		// ask if user is guest or not, login/proceed as needed
		logIn();

		while (true) {
			// display Main Menu, which has options on how to proceed
			displayMenuOptions();

			// value for user commands
			comm = getUserCommand(mainMenuOptions.size());

			// invalid value goes to a continue
			if (comm == -1) {
				System.out.println("Not a valid command");
				continue;
			}

			// break if logging out
			if (comm == mainMenuOptions.size() - 1) {
				System.out.println("Goodbye");
				break;
			}

			// switch case for different cases
			if(user instanceof Customer) {//TODO
			takeActionCustomer();
			} else if(user instanceof Employee) {
			takeActionEmployee();
			} else if(user instanceof Admin) {
			takeActionAdmin();
			} else {
			takeActionGuest();
			}
		}

	}

	/**
	 * Checks to see if the user is an existing user within the system or a guest.
	 */
	private void logIn() {
		while (true) {
			System.out.println("If you are a returning user, type in your account ID. Otherwise, type 0");
			nextLine = scanner.nextLine();
			try {
				if (nextLine.equals("0")) {
					user = new User("Guest", "01/01/2020", "0", "", "");// ------> basic guest user
					fillRemainingMenuOptions();
					break;
				} else {
					int acctId = Integer.parseInt(nextLine);
					user = main.findUser(acctId); //put some users into the main system
					fillRemainingMenuOptions();
					break;
				}
			} catch (Exception e) {
				System.out.println("Not a valid input");
				continue;
			}
		}
	}

	/**
	 * These are the basic main menu options available for all types of users.
	 */
	private void fillBasicMainMenuOptions() {
		mainMenuOptions.add("Search for Movie");
		mainMenuOptions.add("Search for Play");
		mainMenuOptions.add("Search for Concert");
		mainMenuOptions.add("View Watchlist");
		mainMenuOptions.add("Search for Venue");
		mainMenuOptions.add("View Ticket Purchases");
		mainMenuOptions.add("Request Ticket Refund");
		mainMenuOptions.add("Purchase Concessions");
		mainMenuOptions.add("View Customer Support Information");
	}

	/**
	 * The rest of the main menu options are filled in depending on the type of User currently using this app.
	 */
	private void fillRemainingMenuOptions() {
		if(user instanceof Customer) {//TODO
		mainMenuOptions.add("Logout");
		}
		else if(user instanceof Employee) {
		mainMenuOptions.add("Input Event");
		mainMenuOptions.add("Input Discount");
		mainMenuOptions.add("Input Ticket Prices");
		mainMenuOptions.add("Refund Ticket");
		mainMenuOptions.add("Logout");
		} else if(user instanceof Admin) {
		mainMenuOptions.add("Input Event");
		mainMenuOptions.add("Manage Venues");
		mainMenuOptions.add("Logout");
		} else {
		mainMenuOptions.add(0, "Create Account");
		mainMenuOptions.add("Exit");
		}
	}

	/**
	 * Shows the main menu options for this user.
	 */
	private void displayMenuOptions() {
		System.out.println("\n********** Main Menu **********");
		for (int i = 0; i < mainMenuOptions.size(); i++) {
			System.out.println((i + 1) + ". " + mainMenuOptions.get(i));
		}
		System.out.println();
	}

	/**
	 * This method helps in selecting a menu option.
	 * 
	 * @param numCommands - The number of commands in the menu
	 * @return int - The option chosen by the user.
	 */
	private int getUserCommand(int numCommands) {
		System.out.println("Type the number corresponding to the action you want to take");
		nextLine = scanner.nextLine();
		int command = Integer.parseInt(nextLine) - 1;
		if (command >= 0 && command <= numCommands - 1)
			return command;
		return -1;

	}

	/**
	 * Handles different cases for the chosen command for a customer's (existing user's) menu options
	 */
	private void takeActionCustomer() {
		switch (comm) {
		case (0):
			findMovie();
			break;
		case (1):
			findPlay();
			break;
		case (2):
			findConcert();
			break;
		case (3):
			viewWatchlist();
			break;
		case (4):
			findVenue();
			break;
		case (5):
			viewPurchasedTickets();
			break;
		case (6):
			requestRefund();
			break;
		case (7):
			purchaseConcessions();
			break;
		case (8):
			viewCustomerSupport();
			break;
		}
	}

	/**
	 * Handles different cases for the chosen command for an employee's menu options
	 */
	private void takeActionEmployee() {
		switch (comm) {
		case (0):
			findMovie();
			break;
		case (1):
			findPlay();
			break;
		case (2):
			findConcert();
			break;
		case (3):
			viewWatchlist();
			break;
		case (4):
			findVenue();
			break;
		case (5):
			viewPurchasedTickets();
			break;
		case (6):
			requestRefund();
			break;
		case (7):
			purchaseConcessions();
			break;
		case (8):
			viewCustomerSupport();
			break;
		case (9):
			inputEvent();
			break;
		case (10):
			inputDiscount();
			break;
		case (11):
			inputTicketPrices();
			break;
		case (12):
			refundTickets();
			break;
		}
	}

	/**
	 * Handles different cases for the chosen command for an admin's menu options
	 */
	private void takeActionAdmin() {
		switch (comm) {
		case (0):
			findMovie();
			break;
		case (1):
			findPlay();
			break;
		case (2):
			findConcert();
			break;
		case (3):
			viewWatchlist();
			break;
		case (4):
			findVenue();
			break;
		case (5):
			viewPurchasedTickets();
			break;
		case (6):
			requestRefund();
			break;
		case (7):
			purchaseConcessions();
			break;
		case (8):
			viewCustomerSupport();
			break;
		case (9):
			inputEvent();
			break;
		case (10):
			manageVenues();
			break;
		}
	}

	/**
	 * Handles different cases for the chosen command for a guest's menu options
	 */
	private void takeActionGuest() {
		switch (comm) {
		case (0):
			createAccount();
			break;
		case (1):
			findMovie();
			break;
		case (2):
			findPlay();
			break;
		case (3):
			findConcert();
			break;
		case (4):
			viewWatchlist();
			break;
		case (5):
			findVenue();
			break;
		case (6):
			viewPurchasedTickets();
			break;
		case (7):
			requestRefund();
			break;
		case (8):
			purchaseConcessions();
			break;
		case (9):
			viewCustomerSupport();
			break;
		}
	}

	/**
	 * This occurs when a guest wants to create a new account
	 */
	private void createAccount() {
		System.out.println("\n********** Create New Account **********");
		System.out.println("Type in your name");
		String name = scanner.nextLine();

		System.out.println("Type in your birthday (MM/DD/YYYY");
		String birthday = scanner.nextLine();

		System.out.println("Type in your phone number");
		String phoneNum = scanner.nextLine();

		System.out.println("Type in your home address");
		String address = scanner.nextLine();

		System.out.println("Type in your email");
		String email = scanner.nextLine();

		user = new Customer(name, birthday, phoneNum, address, email);//TODO acctID assignment
		main.addUserToDatabase(user);
		//user.createAccount(name, birthday, phoneNum, address, email, main.getUsers());// ----- (turns a guest into a Customer)

		System.out.println("Your account has been created");
	}

	/**
	 * Searches for a movie within the system.
	 */
	private void findMovie() {
		System.out.println("\n********** Search for Movie **********");
		System.out.println("Type in the movie you want to search for");
		
		String movie = "";
		while(true) {
			movie = scanner.nextLine();
			try {
				//user.searchForEvent(movie);
				user.searchForMovie(movie);
				break;
			} catch(Exception e) {
				System.out.println("Movie not found. Type in another movie");
				continue;
			}
		}

		while (true) {
			displaySubMenuOptions(showSearchMenuOptions);

			comm = getUserCommand(showSearchMenuOptions.length);

			if (comm == -1) {
				System.out.println("Not a valid command");
				continue;
			}

			if (comm == showSearchMenuOptions.length - 1)
				break;

			switch (comm) {
			case (0):
				displayMovieInfo(movie);
				break;
			case (1):
				bookMovieTickets(movie);
				break;
			case (2):
				rateMovie(movie);
				break;
			case (3):
				writeMovieReview(movie);
				break;
			case (4):
				addToWatchlist(movie);
				break;
			}
		}
	}

	/**
	 * Displays the basic information about a movie.
	 * @param movie - The movie searched by the user.
	 */
	private void displayMovieInfo(String movie) {
		System.out.println("Movie Info:");
		user.lookAtBasicEventInfo(movie);
	}

	/**
	 * Books tickets for the desired movie.
	 * @param movie - The movie searched by the user.
	 */
	private void bookMovieTickets(String movie) {
		System.out.println("Type in the date you want to see this movie (MM/DD/YYYY");
		String date = scanner.nextLine();

		System.out.println("Type in the time you want to see this movie");
		String time = scanner.nextLine();

		System.out.println("Type in the number of adult tickets you want to purchase");
		int numAdultTickets = Integer.parseInt(scanner.nextLine());

		System.out.println("Type in the number of child tickets you want to purchase");
		int numChildTickets = Integer.parseInt(scanner.nextLine());
		
		main.displayAvailableTheater();
		
		System.out.println("Where would you like to sit?");
		System.out.println("Type in character corresponding to the row you want to sit on");
		char row = scanner.nextLine().charAt(0);
		
		System.out.println("Type in number corresponding to the seat you want to sit on at that row");
		int col = Integer.parseInt(scanner.nextLine());
		
		//TODO use those input to update the status of the seat, possibly in bookTickets method
		//TODO seat parameter
		user.bookTickets(movie, date, time, numAdultTickets, numChildTickets);

		System.out.println("Tickets have been booked!");

		System.out.println("Would you like a receipt? (Y/N)");
		while (true) {
			nextLine = scanner.nextLine();
			if (nextLine.equals("Y")) {
				user.createReceipt();
				System.out.println("Your receipt has been created");
			} else if (nextLine.equals("N")) {
				break;
			} else {
				System.out.println("Invalid input, only Y/N");
			}
		}
	}

	/**
	 * Rates the movie on a scale of 1 to 5.
	 * @param movie - The movie searched by the user.
	 */
	private void rateMovie(String movie) {
		System.out.println("Type in your rating for this movie on a scale of 1 to 5");
		
		int rating = 0;
		while(true) {
			try {
				rating = Integer.parseInt(scanner.nextLine());
				if(rating < 1 || rating > 5) {
					System.out.println("Please rate it on a scale of 1 to 5 only");
					continue;
				}
				break;
			} catch(Exception e) {
				System.out.println("Please rate it on a scale of 1 to 5 only");
				continue;
			}
		}
		
		user.rateEvent(movie, rating);
		System.out.println("Rating has been recorded");
	}

	/**
	 * Writes a movie review for the desired movie.
	 * @param movie - The movie searched by the user.
	 */
	private void writeMovieReview(String movie) {
		System.out.println("Type in your review for this movie");
		String review = scanner.nextLine();
		user.writeEventReview(movie, review);
		System.out.println("Review has been recorded");
	}

	/**
	 * Searches for a play within the system.
	 */
	private void findPlay() {
		System.out.println("\n********** Search for Play **********");
		System.out.println("Type in the play you want to search for");
		
		String play = "";
		while(true) {
			try {
				play = scanner.nextLine();
				//TODO user.searchForEvent(play);
				user.searchForPlay(play);
				break;
			} catch(Exception e) {
				System.out.println("Play not found. Type in another play");
				continue;
			}
		}

		while (true) {
			displaySubMenuOptions(showSearchMenuOptions);

			comm = getUserCommand(showSearchMenuOptions.length);

			if (comm == -1) {
				System.out.println("Not a valid command");
				continue;
			}

			if (comm == showSearchMenuOptions.length - 1)
				break;

			switch (comm) {
			case (0):
				displayPlayInfo(play);
				break;
			case (1):
				bookPlayTickets(play);
				break;
			case (2):
				ratePlay(play);
				break;
			case (3):
				writePlayReview(play);
				break;
			case (4):
				addToWatchlist(play);
				break;
			}
		}
	}

	/**
	 * Displays the basic information about a play.
	 * @param play - The play searched by the user.
	 */
	private void displayPlayInfo(String play) {
		System.out.println("Play Info:");
		user.lookAtBasicEventInfo(play);
	}

	/**
	 * Books tickets for the desired play.
	 * @param play - The play searched by the user.
	 */
	private void bookPlayTickets(String play) {
		System.out.println("Type in the date you want to see this play (MM/DD/YYYY");
		String date = scanner.nextLine();

		System.out.println("Type in the time you want to see this play");
		String time = scanner.nextLine();

		System.out.println("Type in the number of adult tickets you want to purchase");
		int numAdultTickets = Integer.parseInt(scanner.nextLine());

		System.out.println("Type in the number of child tickets you want to purchase");
		int numChildTickets = Integer.parseInt(scanner.nextLine());

		user.bookTickets(play, date, time, numAdultTickets, numChildTickets);

		System.out.println("Tickets have been booked!");

		System.out.println("Would you like a receipt? (Y/N)");
		while (true) {
			nextLine = scanner.nextLine();
			if (nextLine.equals("Y")) {
				user.createReceipt();
				System.out.println("Your receipt has been created");
			} else if (nextLine.equals("N")) {
				continue;
			} else {
				System.out.println("Invalid input, only Y/N");
			}
		}
	}

	/**
	 * Rates the play on a scale of 1 to 5.
	 * @param play - The play searched by the user.
	 */
	private void ratePlay(String play) {
		System.out.println("Type in your rating for this play on a scale of 1 to 5");
		
		int rating = 0;
		while(true) {
			try {
				rating = Integer.parseInt(scanner.nextLine());
				if(rating < 1 || rating > 5) {
					System.out.println("Please rate it on a scale of 1 to 5 only");
					continue;
				}
				break;
			} catch(Exception e) {
				System.out.println("Please rate it on a scale of 1 to 5 only");
				continue;
			}
		}
		
		user.rateEvent(play, rating);
		System.out.println("Rating has been recorded");
	}

	/**
	 * Writes a play review for the desired play.
	 * @param play - The play searched by the user.
	 */
	private void writePlayReview(String play) {
		System.out.println("Type in your review for this play");
		String review = scanner.nextLine();
		user.writeEventReview(play, review);
		System.out.println("Review has been recorded");
	}

	/**
	 * Searches for a concert within the system.
	 */
	private void findConcert() {
		System.out.println("\n********** Search for Concert **********");
		System.out.println("Type in the concert you want to search for");
		
		String concert = "";
		while(true) {
			try {
				concert = scanner.nextLine();
				//user.searchForEvent(concert);
				user.searchForConcert(concert);
				break;
			} catch(Exception e) {
				System.out.println("Concert not found. Type in another concert");
				continue;
			}
		}

		while (true) {
			displaySubMenuOptions(showSearchMenuOptions);

			comm = getUserCommand(showSearchMenuOptions.length);

			if (comm == -1) {
				System.out.println("Not a valid command");
				continue;
			}

			if (comm == showSearchMenuOptions.length - 1)
				break;

			switch (comm) {
			case (0):
				displayConcertInfo(concert);
				break;
			case (1):
				bookConcertTickets(concert);
				break;
			case (2):
				rateConcert(concert);
				break;
			case (3):
				writeConcertReview(concert);
				break;
			case (4):
				addToWatchlist(concert);
				break;
			}
		}
	}

	/**
	 * Displays the basic information about a concert.
	 * @param concert - The concert searched by the user.
	 */
	private void displayConcertInfo(String concert) {
		System.out.println("Concert Info:");
		user.lookAtBasicEventInfo(concert);
	}

	/**
	 * Books tickets for the desired concert.
	 * @param concert - The concert searched by the user.
	 */
	private void bookConcertTickets(String concert) {
		System.out.println("Type in the date you want to see this concert (MM/DD/YYYY");
		String date = scanner.nextLine();

		System.out.println("Type in the time you want to see this concert");
		String time = scanner.nextLine();

		System.out.println("Type in the number of adult tickets you want to purchase");
		int numAdultTickets = Integer.parseInt(scanner.nextLine());

		System.out.println("Type in the number of child tickets you want to purchase");
		int numChildTickets = Integer.parseInt(scanner.nextLine());

		user.bookTickets(concert, date, time, numAdultTickets, numChildTickets);

		System.out.println("Tickets have been booked!");

		System.out.println("Would you like a receipt? (Y/N)");
		while (true) {
			nextLine = scanner.nextLine();
			if (nextLine.equals("Y")) {
				user.createReceipt();
				System.out.println("Your receipt has been created");
			} else if (nextLine.equals("N")) {
				continue;
			} else {
				System.out.println("Invalid input, only Y/N");
			}
		}
	}

	/**
	 * Rates the concert on a scale of 1 to 5.
	 * @param concert - The concert searched by the user.
	 */
	private void rateConcert(String concert) {
		System.out.println("Type in your rating for this concert on a scale of 1 to 5");
		
		int rating = 0;
		while(true) {
			try {
				rating = Integer.parseInt(scanner.nextLine());
				if(rating < 1 || rating > 5) {
					System.out.println("Please rate it on a scale of 1 to 5 only");
					continue;
				}
				break;
			} catch(Exception e) {
				System.out.println("Please rate it on a scale of 1 to 5 only");
				continue;
			}
		}
		
		user.rateEvent(concert, rating);
		System.out.println("Rating has been recorded");
	}

	/**
	 * Writes a concert review for the desired concert.
	 * @param concert - The concert searched by the user.
	 */
	private void writeConcertReview(String concert) {
		System.out.println("Type in your review for this concert");
		String review = scanner.nextLine();
		user.writeEventReview(concert, review);
		System.out.println("Review has been recorded");
	}

	/**
	 * Adds an event to the user's watchlist
	 * @param event - The event (movie, play, or concert) inputted by the user.
	 */
	private void addToWatchlist(String event) {
		user.addToWatchlist(event);
		System.out.println("Added to watchlist");
	}

	/**
	 * Displays the user's watchlist.
	 */
	private void viewWatchlist() {
		while (true) {
			System.out.println("\n********** Watchlist **********");
			user.displayWatchlist();

			displaySubMenuOptions(watchlistMenuOptions);

			comm = getUserCommand(watchlistMenuOptions.length);

			if (comm == -1) {
				System.out.println("Not a valid command");
				continue;
			}

			if (comm == watchlistMenuOptions.length - 1)
				break;

			switch (comm) {
			case (0):
				System.out.println("Type in the name of the show you want to add to the watchlist");
				String showAdded = scanner.nextLine();
				user.addToWatchlist(showAdded);
			case (1):
				System.out.println("Type in the name of the show you want to remove from the watchlist");
				String showRemoved = scanner.nextLine();
				user.removeFromWatchlist(showRemoved);
			}
		}
	}

	/**
	 * Searches for a venue within the system.
	 */
	private void findVenue() {// TODO expand on this?
		System.out.println("\n********** Search for Concert **********");
		System.out.println("Type in the concert you want to search for");
		
		String venue = "";
		while(true) {
			try {
				venue = scanner.nextLine();
				user.searchForVenue(venue, main.getVenues());
				break;
			} catch(Exception e) {
				System.out.println("Venue not found. Type in another venue");
				continue;
			}
		}

		while (true) {
			displaySubMenuOptions(venueSearchMenuOptions);

			comm = getUserCommand(venueSearchMenuOptions.length);

			if (comm == -1) {
				System.out.println("Not a valid command");
				continue;
			}

			if (comm == venueSearchMenuOptions.length - 1)
				break;

			switch (comm) {
			case (0):
				user.updateHomeVenue(venue);
				System.out.println("Your home venue has been updated");
			}
		}
	}
	
	/**
	 * Displays the user's tickets.
	 */
	private void viewPurchasedTickets() {
		System.out.println("\n********** Your Tickets **********");
		user.displayTickets();
		while (true) {
			System.out.println("Type 'Back' to go back to the main menu");
			nextLine = scanner.nextLine();
			if (nextLine.equals("Back")) {
				break;
			} else {
				System.out.println("Not a valid input");
				continue;
			}
		}
	}

	/**
	 * This occurs when a user request for a refund.
	 */
	private void requestRefund() {
		System.out.println("\n********** Request Refund **********");
		System.out.println("Type in the name of the event you want a refund from");
		String eventRefunded = scanner.nextLine();
		System.out.println("Type in the number of tickets you want to refund");
		int numTicketsRefunded = Integer.parseInt(scanner.nextLine());
		user.requestRefund(eventRefunded, numTicketsRefunded);
		System.out.println("Your refund request has been sent to be processed");
	}
	
	/**
	 * This occurs when a user wants to purchase concessions.
	 */
	private void purchaseConcessions() {
		System.out.println("\n********** Purchase Concessions **********");
		main.displayConcessions();
		System.out.println("Type in the concession you want");
		String type = scanner.nextLine();
		System.out.println("Quantity of that concession you want");
		int quantity = Integer.parseInt(scanner.nextLine());
		user.receiveConcessions(type, quantity);
		System.out.println("A receipt has been made for you. Show this to an employee at the concession counter");
	}

	/**
	 * Displays the customer support page.
	 */
	private void viewCustomerSupport() {
		System.out.println("\n********** Customer Support **********");
		main.displaySupportInfo();

		while (true) {
			System.out.println("Type 'Back' to go back to the main menu");
			nextLine = scanner.nextLine();
			if (nextLine.equals("Back")) {
				break;
			} else {
				System.out.println("Not a valid input");
				continue;
			}
		}
	}

	/**
	 * Allows an employee to input a new event into the system.
	 */
	private void inputEvent() {
		//determines whether user is admin or employee and temporarily treats them as such within this method
		Employee employeeUser = null;
		if (!((user instanceof Employee)||(user instanceof Admin))){
			System.out.println("You do not have the permissions to use this function");
			return;
		}
		employeeUser = (Employee)user;


		System.out.println("\nType in the type of the show to be inputted (Movie, Play, or Concert)");
		String showType = scanner.nextLine();

		System.out.println("Type in the name of the show");
		String showName = scanner.nextLine();

		System.out.println("Type in the date this show is occurring");
		String date = scanner.nextLine();

		System.out.println("Type in the time this show is occurring on this date");
		String time = scanner.nextLine();

		System.out.println("Type in the official rating of this show");
		int rating = Integer.parseInt(scanner.nextLine());

		System.out.println("Type in the age rating of this show");
		int ageRating = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Type in the major producers of this show. Type 'Done' when finished");
		ArrayList<String> producers = new ArrayList<String>();
		while(true) {
			String producer = scanner.nextLine();
			if(producer.equals("Done")) break;
			producers.add(producer);
		}
		
		Show show = null;
		
		if(showType.equals("Movie")) {
			System.out.println("Type in the genre of this movie");
			String genre = scanner.nextLine();
			System.out.println("Type in the major actors in this movie. Type 'Done' when finished");
			ArrayList<String> actors = new ArrayList<String>();
			while(true) {
				String actor = scanner.nextLine();
				if(actor.equals("Done")) break;
				actors.add(actor);
			}
			show = new Movie(showName, ageRating, genre, actors, producers);
		}
		else if(showType.equals("Play")) {
			System.out.println("Type in the major actors in this play. Type 'Done' when finished");
			ArrayList<String> actors = new ArrayList<String>();
			while(true) {
				String actor = scanner.nextLine();
				if(actor.equals("Done")) break;
				actors.add(actor);
			}
			show = new Play(showName, ageRating, actors, producers);
		}
		else if(showType.equals("Concert")) {
			System.out.println("Type in the major performers in this concert. Type 'Done' when finished");
			ArrayList<String> performers = new ArrayList<String>();
			while(true) {
				String performer = scanner.nextLine();
				if(performer.equals("Done")) break;
				performers.add(performer);
			}
			show = new Concert(showName, ageRating, performers, producers);
		}

		//TODO fix constructor here
		employeeUser .inputEvent(show, date, time);

		System.out.println("This event has been added");
	}

	/**
	 * Allows an employee to apply a discount to the prices of tickets.
	 */
	private void inputDiscount() {
		Employee employeeUser = null;
		if(!(user instanceof Employee)){
			System.out.println("Only an employee level user can use this function");
			return;
		}
		employeeUser = (Employee)user;

		System.out.println("\nType in the percent discount");
		double discount = Double.parseDouble(scanner.nextLine());//change to double

		//TODO User input
		User receivingDiscount = null; //TODO make not null lol
		employeeUser.inputDiscount(discount, receivingDiscount);
		// TODO user.inputDiscount(discount);


		System.out.println("Discount has been applied");
	}

	/**
	 * Allows an employee to change the actual price of a ticket.
	 */
	private void inputTicketPrices() {
		System.out.println("\nType in the price of an adult ticket");
		double adultTicketPrice = Double.parseDouble(scanner.nextLine());

		System.out.println("Type in the price of a child ticket");
		double childPriceMultiplier = Double.parseDouble(scanner.nextLine());

		// TODO 
		user.inputTicketPrices(adultTicketPrice, childPriceMultiplier);

		System.out.println("Ticket prices have been set");
	}

	/**
	 * Allows an employee to issue a refund to users who requested a refund.
	 */
	private void refundTickets() {
		user.displayTicketsRefunded();
		System.out.println("Type in the name of the customer to give a refund to");
		String customerName = scanner.nextLine();

		
		user.refundTickets(customerName, main.getUsers());

		System.out.println("Customer's refund has been processed");
	}

	/**
	 * Allows admins to view and manage their venues.
	 */
	private void manageVenues() {
		while (true) {
			System.out.println("\n********** Venues **********");
			user.displayVenues();

			displaySubMenuOptions(venueMenuOptions);

			comm = getUserCommand(venueMenuOptions.length);

			if (comm == -1) {
				System.out.println("Not a valid command");
				continue;
			}

			if (comm == venueMenuOptions.length - 1)
				break;

			switch (comm) {
			case (0):
				System.out.println("Type in the name of the venue you want to add to the list");
				String venueAdded = scanner.nextLine();
				user.addVenue(venueAdded);
			case (1):
				System.out.println("Type in the name of the venue you want to remove from the list");
				String venueRemoved = scanner.nextLine();
				user.removeVenue(venueRemoved);
			}
		}
	}

	/**
	 * Displays sub menu options under a particular main menu option.
	 * @param subMenuOptions - The menu options under a specific main menu option.
	 */
	private void displaySubMenuOptions(String[] subMenuOptions) {
		System.out.println("What would you like to do?");
		for (int i = 0; i < subMenuOptions.length; i++) {
			System.out.println((i + 1) + ". " + subMenuOptions[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		TicketingAppUI appInterface = new TicketingAppUI();
		appInterface.run();

	}

}
