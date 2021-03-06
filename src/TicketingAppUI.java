import java.util.ArrayList;
import java.util.Scanner;

/**
 * TicketingAppUI.java - Represents the driver class for this app.
 * 
 * @author Taylor Norton, Lukacs Ablonczy
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

	/**
	 * Default constructor for TicketingAppUI
	 */
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

		logIn();

		getHomeVenue();

		while (true) {
			displayMenuOptions();

			comm = getUserCommand(mainMenuOptions.size());

			if (comm == -1) {
				System.out.println("Not a valid command");
				continue;
			}

			if (comm == mainMenuOptions.size() - 1) {
				System.out.println("Goodbye");
				break;
			}

			if (user instanceof Customer) {
				takeActionCustomer();
			} else if (user instanceof Employee) {
				takeActionEmployee();
			} else if (user instanceof Admin) {
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
				if (nextLine.trim().equalsIgnoreCase("0")) {
					user = new User("Guest", "01/01/2020", "0", "", "");
					fillRemainingMenuOptions();
					break;
				} else {
					int acctId = Integer.parseInt(nextLine);
					user = main.findUser(acctId);
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
	 * Method which prompts the user to enter a home venue
	 */
	private void getHomeVenue() {
		System.out.println("Input your home venue");
		while (true) {
			try {
				nextLine = scanner.nextLine();
				user.updateHomeVenue(nextLine, main.getVenues());
				break;
			} catch (Exception e) {
				System.out.println("This venue doesn't exist in the system. Input another home venue");
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
	 * The rest of the main menu options are filled in depending on the type of User
	 * currently using this app.
	 */
	private void fillRemainingMenuOptions() {
		if (user instanceof Customer) {
			mainMenuOptions.add("Logout");
		} else if (user instanceof Employee) {
			mainMenuOptions.add("Input Event");
			mainMenuOptions.add("Input Discount");
			mainMenuOptions.add("Refund Ticket");
			mainMenuOptions.add("Logout");
		} else if (user instanceof Admin) {
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
		while (true) {
			try {
				nextLine = scanner.nextLine();
				int command = Integer.parseInt(nextLine) - 1;
				if (command >= 0 && command <= numCommands - 1)
					return command;
				return -1;
			} catch (Exception e) {
				System.out.println("This is not a number");
				System.out.println("Type the number corresponding to the action you want to take");
				continue;
			}
		}

	}

	/**
	 * Handles different cases for the chosen command for a customer's (existing
	 * user's) menu options
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
		if (name.trim().equalsIgnoreCase("")) {
			System.out.println("Bad name given");
			return;
		}

		System.out.println("Type in your birthday (MM/DD/YYYY)");
		String birthday = scanner.nextLine();
		if (name.trim().equalsIgnoreCase("")) {
			System.out.println("Bad BDAY given");
			return;
		}

		System.out.println("Type in your phone number");
		String phoneNum = scanner.nextLine();
		if (phoneNum.trim().equalsIgnoreCase("")) {
			System.out.println("Bad number given");
			return;
		}

		System.out.println("Type in your home address");
		String address = scanner.nextLine();
		if (address.trim().equalsIgnoreCase("")) {
			System.out.println("Bad address given");
			return;
		}

		System.out.println("Type in your email");
		String email = scanner.nextLine();
		if (email.trim().equalsIgnoreCase("")) {
			System.out.println("Bad email given");
			return;
		}

		user = new Customer(name, birthday, phoneNum, address, email);
		main.addUserToDatabase(user);
		mainMenuOptions.clear();
		fillBasicMainMenuOptions();
		fillRemainingMenuOptions();

		System.out.println("Your account has been created");
	}

	/**
	 * Searches for a movie within the system.
	 */
	private void findMovie() {
		System.out.println("\n********** Search for Movie **********");

		String movie = "";

		while (true) {
			do {
				System.out.println("Type in the movie you want to search for");
				movie = scanner.nextLine();
			} while (movie.trim().equalsIgnoreCase(""));

			Movie movieFound = user.searchForMovie(movie);
			if (movieFound == null) {
				System.out.println("Movie not found");
				return;
			} else {
				System.out.println("\nHere's what we found:\n" + movieFound.getName());
				break;
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
				bookTickets(movie);
				break;
			case (2):
				rateShow(movie);
				break;
			case (3):
				writeShowReview(movie);
				break;
			case (4):
				addToWatchlist(movie);
				break;
			}
		}
	}

	/**
	 * Displays the basic information about a movie.
	 * 
	 * @param movie - The movie searched by the user.
	 */
	private void displayMovieInfo(String movie) {
		System.out.println("Movie Info:");
		user.lookAtBasicEventInfo(movie);
	}

	/**
	 * Prompts the user to enter a rating for a show
	 * 
	 * @param showName - The name of the show being rated
	 */
	private void rateShow(String showName) {
		System.out.println("Type in your rating for this event on a scale of 1 to 5");

		int rating = 0;
		while (true) {
			try {
				rating = Integer.parseInt(scanner.nextLine());
				if (rating < 1 || rating > 5) {
					System.out.println("Please rate it on a scale of 1 to 5 only");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("Please rate it on a scale of 1 to 5 only");
				continue;
			}
		}

		user.rateEvent(showName, rating);
		System.out.println("Rating has been recorded");
	}

	/**
	 * Prompts the user to write a review for a show
	 * 
	 * @param showName - The name of the show being reviewed
	 */
	private void writeShowReview(String showName) {
		System.out.println("Type in your review for this movie");
		String review = scanner.nextLine();
		if (review.trim().equals("")) {
			System.out.println("Empty review not recorded");
			return;
		}

		user.writeEventReview(showName, review);
		System.out.println("Review has been recorded");
	}

	/**
	 * Searches for a play within the system.
	 */
	private void findPlay() {
		System.out.println("\n********** Search for Play **********");

		String play = "";

		while (true) {
			do {
				System.out.println("Type in the play you want to search for");
				play = scanner.nextLine();
			} while (play.trim().equalsIgnoreCase(""));

			Play playFound = user.searchForPlay(play);
			if (playFound == null) {
				System.out.println("Play not found");
				return;
			} else {
				System.out.println("\nHere's what we found:\n" + playFound.getName());
				break;
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
				bookTickets(play);
				break;
			case (2):
				rateShow(play);
				break;
			case (3):
				writeShowReview(play);
				break;
			case (4):
				addToWatchlist(play);
				break;
			}
		}
	}

	/**
	 * Displays the basic information about a play.
	 * 
	 * @param play - The play searched by the user.
	 */
	private void displayPlayInfo(String play) {
		System.out.println("Play Info:");
		user.lookAtBasicEventInfo(play);
	}

	/**
	 * Searches for a concert within the system.
	 */
	private void findConcert() {
		System.out.println("\n********** Search for Concert **********");

		String concert = "";

		while (true) {
			do {
				System.out.println("Type in the concert you want to search for");
				concert = scanner.nextLine();
			} while (concert.trim().equalsIgnoreCase(""));

			Concert concertFound = user.searchForConcert(concert);
			if (concertFound == null) {
				System.out.println("Concert not found");
				return;
			} else {
				System.out.println("\nHere's what we found:\n" + concertFound.getName());
				break;
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
				bookTickets(concert);
				break;
			case (2):
				rateShow(concert);
				break;
			case (3):
				writeShowReview(concert);
				break;
			case (4):
				addToWatchlist(concert);
				break;
			}
		}
	}

	/**
	 * Displays the basic information about a concert.
	 * 
	 * @param concert - The concert searched by the user.
	 */
	private void displayConcertInfo(String concert) {
		System.out.println("Concert Info:");
		user.lookAtBasicEventInfo(concert);
	}

	/**
	 * Allows the user to book tickets for a desired show
	 * 
	 * @param showName - Name of the show tickets are being booked for
	 */
	private void bookTickets(String showName) {
		System.out.println("Type in the date you want to see this show (MM/DD/YYYY)");
		String date = scanner.nextLine();
		if (date.trim().equalsIgnoreCase("")) {
			System.out.println("Invalid date given");
			return;
		}

		System.out.println("Type in the time you want to see this show (HH:MM[am/pm])");
		String time = scanner.nextLine();
		if (time.trim().equalsIgnoreCase("")) {
			System.out.println("Invalid time given");
			return;
		}

		System.out.println("Type in the number of adult tickets you want to purchase");
		int numAdultTickets = 0;
		try {
			numAdultTickets = Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			System.out.println("Invalid number of adult tickets");
			return;
		}
		if (numAdultTickets < 0)
			return;

		System.out.println("Type in the number of child tickets you want to purchase");
		int numChildTickets = 0;
		try {
			numChildTickets = Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			System.out.println("Invalid number of child tickets");
			return;
		}
		if (numChildTickets < 0)
			return;

		main.displayAvailableTheater(showName, date, time);

		System.out.println("Where would you like to sit?");
		System.out.println("Type in character corresponding to the row you want to sit on");
		char row = scanner.nextLine().charAt(0);
		if (row == ' ') {
			System.out.println("Invalid row given");
			return;
		}

		int col = 0;
		System.out.println("Type in number corresponding to the seat you want to sit on at that row");
		try {
			col = Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			System.out.println("Invalid number of seat number");
			return;
		}

		user.bookTickets(showName, date, time, numAdultTickets, numChildTickets, row, col);

		System.out.println("Tickets have been booked!");

		System.out.println("Would you like a receipt? (Y/N)");
		while (true) {
			nextLine = scanner.nextLine();
			if (nextLine.trim().equalsIgnoreCase("Y")) {
				user.createReceipt();
				System.out.println("Your receipt has been created");
				break;
			} else if (nextLine.trim().equalsIgnoreCase("N")) {
				break;
			} else {
				System.out.println("Invalid input, only Y/N");
			}
		}
	}

	/**
	 * Adds an event to the user's watchlist
	 * 
	 * @param eventName - The event (movie, play, or concert) inputted by the user.
	 */
	private void addToWatchlist(String eventName) {
		if (eventName == null || eventName.trim().equalsIgnoreCase("")) {
			System.out.println("Event name is blank or null");
		}

		user.addToWatchlist(eventName);
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
				break;
			case (1):
				System.out.println("Type in the name of the show you want to remove from the watchlist");
				String showRemoved = scanner.nextLine();
				user.removeFromWatchlist(showRemoved);
				break;
			}
		}
	}

	/**
	 * Searches for a venue within the system.
	 */
	private void findVenue() {
		System.out.println("\n********** Search for Venue **********");

		String venue = "";

		while (true) {
			do {
				System.out.println("Type in the venue you want to search for");
				venue = scanner.nextLine();
			} while (venue.trim().equalsIgnoreCase(""));

			Venue venueFound = user.searchForVenue(venue, main.getVenues());
			if (venueFound == null) {
				System.out.println("Venue not found");
				return;
			} else {
				System.out.println("\nHere's what we found:\n" + venueFound.getName());
				break;
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
				user.updateHomeVenue(venue, main.getVenues());
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
			if (nextLine.trim().equalsIgnoreCase("Back")) {
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

		String eventRefunded = "";
		do {
			System.out.println("Type in the name of the event you want a refund from");
			eventRefunded = scanner.nextLine();
		} while (eventRefunded.trim().equalsIgnoreCase(""));

		int numTicketsRefunded = 0;
		do {
			System.out.println("Type in the number of tickets you want to refund");
			numTicketsRefunded = Integer.parseInt(scanner.nextLine());
		} while (numTicketsRefunded <= 0);

		user.requestRefund(eventRefunded, numTicketsRefunded);
		System.out.println("Your refund request has been sent to be processed");
	}

	/**
	 * This occurs when a user wants to purchase concessions.
	 */
	private void purchaseConcessions() {
		System.out.println("\n********** Purchase Concessions **********");
		main.displayConcessions();

		String type = "";
		do {
			System.out.println("Type in the concession you want");
			type = scanner.nextLine();
		} while (type.trim().equalsIgnoreCase(""));

		System.out.println("Quantity of that concession you want");
		int quantity = 0;
		while (quantity <= 0) {
			System.out.println("Please enter a number");
			quantity = Integer.parseInt(scanner.nextLine());
		}

		user.receiveConcessions(type, quantity);
		System.out.println(
				"Your concessions have been added to your receipt. \nPrint your receipt and show this to an employee at the concession counter");
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
			if (nextLine.trim().toLowerCase().equals("back")) {
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
		Employee employeeUser = null;
		if (!((user instanceof Employee) || (user instanceof Admin))) {
			System.out.println("You do not have the permissions to use this function");
			return;
		}
		employeeUser = (Employee) user;

		System.out.println("\nType in the type of the show to be inputted (Movie, Play, or Concert)");
		String showType = scanner.nextLine();
		if (showType.trim().equalsIgnoreCase("")) {
			System.out.println("invalid type");
			return;
		}

		System.out.println("Type in the name of the show");
		String showName = scanner.nextLine();
		if (showName.trim().equalsIgnoreCase("")) {
			System.out.println("invalid name");
			return;
		}

		System.out.println("Type in the date this show is occurring (MM/DD/YYYY)");
		String date = scanner.nextLine();
		if (date.trim().equalsIgnoreCase("")) {
			System.out.println("invalid date");
			return;
		}

		System.out.println("Type in the time this show is occurring on this date (HH:MM[am/pm])");
		String time = scanner.nextLine();
		if (time.trim().equalsIgnoreCase("")) {
			System.out.println("invalid time");
			return;
		}

		System.out.println("Type in the official rating of this show");
		int rating = Integer.parseInt(scanner.nextLine());
		if (rating <= 0) {
			System.out.println("invalid rating");
			return;
		}

		System.out.println("Type in the age rating of this show");
		int ageRating = Integer.parseInt(scanner.nextLine());
		if (ageRating <= 0) {
			System.out.println("invalid age rating");
			return;
		}

		System.out.println("Type in the major producers of this show. Type 'Done' when finished");
		ArrayList<String> producers = new ArrayList<String>();
		while (true) {
			String producer = scanner.nextLine();
			if (producer.trim().equalsIgnoreCase("Done"))
				break;
			producers.add(producer);
		}

		Show show = null;

		if (showType.trim().equalsIgnoreCase("Movie")) {
			System.out.println("Type in the genre of this movie");
			String genre = scanner.nextLine();
			System.out.println("Type in the major actors in this movie. Type 'Done' when finished");
			ArrayList<String> actors = new ArrayList<String>();
			while (true) {
				String actor = scanner.nextLine();
				if (actor.trim().equalsIgnoreCase("Done"))
					break;
				actors.add(actor);
			}
			show = new Movie(showName, rating, ageRating, genre, actors, producers);
		} else if (showType.trim().equalsIgnoreCase("Play")) {
			System.out.println("Type in the major actors in this play. Type 'Done' when finished");
			ArrayList<String> actors = new ArrayList<String>();
			while (true) {
				String actor = scanner.nextLine();
				if (actor.trim().equalsIgnoreCase("Done"))
					break;
				actors.add(actor);
			}
			show = new Play(showName, rating, ageRating, actors, producers);
		} else if (showType.trim().equalsIgnoreCase("Concert")) {
			System.out.println("Type in the major performers in this concert. Type 'Done' when finished");
			ArrayList<String> performers = new ArrayList<String>();
			while (true) {
				String performer = scanner.nextLine();
				if (performer.trim().equalsIgnoreCase("Done"))
					break;
				performers.add(performer);
			}
			show = new Concert(showName, rating, ageRating, performers, producers);
		}

		ArrayList<Venue> allVenues = main.getVenues();
		System.out.println("And at which venue is this event taking place?");
		allVenues.forEach(System.out::println);

		String venueName = scanner.nextLine();
		if (venueName.trim().equalsIgnoreCase("")) {
			System.out.println("No event given");
			return;
		}

		Venue venueToAddEventTo = null;
		for (Venue venue : allVenues)
			if (venueName.equals(venue.getName())) {
				venueToAddEventTo = venue;
				break;
			}

		if (venueToAddEventTo == null) {
			System.out.println("No Venue matching that name...Event not added");
			return;
		}

		employeeUser.inputEvent(venueToAddEventTo, date, time, show);

		System.out.println("This event has been added");
	}

	/**
	 * Allows an employee to apply a discount to the prices of tickets.
	 */
	private void inputDiscount() {
		Employee employeeUser = null;
		if (!(user instanceof Employee)) {
			System.out.println("Only an employee level user can use this function");
			return;
		}
		employeeUser = (Employee) user;

		System.out.println("\nType in the percent discount");
		double discount = Double.parseDouble(scanner.nextLine());

		System.out.println("What is the name of the user you wish give this discount?");
		String name = scanner.nextLine();

		User receivingDiscount = null;
		for (User user : main.getUsers())
			if (user.getName().equalsIgnoreCase(name)) {
				receivingDiscount = user;
				break;
			}

		if (receivingDiscount == null) {
			System.out.println("No user with that name...");
			return;
		}

		employeeUser.inputDiscount(discount, receivingDiscount);

		System.out.println("Discount has been applied");
	}

	/**
	 * Allows an employee to issue a refund to users who requested a refund.
	 */
	private void refundTickets() {

		System.out.println("Type in the name of the customer to give a refund to");
		String customerName = scanner.nextLine();
		if (customerName.trim().equalsIgnoreCase("")) {
			System.out.println("Blank customer name given");
			return;
		}

		Employee employeeUser = (Employee) user;
		employeeUser.refundTickets(customerName);

		System.out.println("Customer's refund has been processed");
	}

	/**
	 * Allows admins to view and manage their venues.
	 */
	private void manageVenues() {
		Admin adminUser = null;
		if (!(user instanceof Admin)) {
			System.out.println("Only an admin level user can use this function");
			return;
		}
		adminUser = (Admin) user;

		while (true) {
			System.out.println("\n********** Venues **********");
			adminUser.displayVenues();

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
				System.out.println(
						"Type in the name of the venue you want to add to your list. Venue must be in the main system");
				String venueAdded = scanner.nextLine();
				adminUser.addVenue(venueAdded, main.getVenues());
			case (1):
				System.out.println("Type in the name of the venue you want to remove from your list");
				String venueRemoved = scanner.nextLine();
				adminUser.removeVenue(venueRemoved);
			}
		}
	}

	/**
	 * Displays sub menu options under a particular main menu option.
	 * 
	 * @param subMenuOptions - The menu options under a specific main menu option.
	 */
	private void displaySubMenuOptions(String[] subMenuOptions) {
		System.out.println("\nWhat would you like to do?");
		for (int i = 0; i < subMenuOptions.length; i++) {
			System.out.println((i + 1) + ". " + subMenuOptions[i]);
		}
		System.out.println();
	}

	/** Main method which starts the program */
	public static void main(String[] args) {
		TicketingAppUI appInterface = new TicketingAppUI();
		appInterface.run();

	}

}
