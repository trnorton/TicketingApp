import java.util.ArrayList;
import java.util.Scanner;

public class TicketingAppUI {

	private static final String WELCOME_MESSAGE = "Welcome to our Ticketing App";
	private Scanner scanner;
	private String nextLine;
	private int comm;
	// TODO private User user;
	// TODO MainSystem main;
	private ArrayList<String> mainMenuOptions;
	private String[] watchlistMenuOptions = { "Add to watchlist", "Remove from watchlist", "Back" };
	private String[] venueMenuOptions = { "Add to venue list", "Remove from venue list", "Back" };
	private String[] showSearchMenuOptions = { "Display info", "Book tickets", "Rate", "Write review",
			"Add to watchlist", "Return to main menu" };
	private String[] venueSearchMenuOptions = { "Make this venue your home venue", "Return to main menu" };

	TicketingAppUI() {
		scanner = new Scanner(System.in);
		// TODO main = new MainSystem();
		mainMenuOptions = new ArrayList<String>();
		fillBasicMainMenuOptions();
	}

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
			if (comm == mainMenuOptions.size() - 1)
				break;

			// switch case for different cases
			// TODO if user is customer
			takeActionCustomer();
			// TODO if user is employee
			takeActionEmployee();
			// TODO if user is admin
			takeActionAdmin();
			// TODO if user is guest
			takeActionGuest();
		}

	}

	private void logIn() {
		while (true) {
			System.out.println("If you are a returning user, type in your account ID. Otherwise, type 0");
			nextLine = scanner.nextLine();
			try {
				if (nextLine.equals("0")) {
					// TODO user = new User(); ------> basic guest user
					fillRemainingMenuOptions();
					break;
				} else {
					int acctId = Integer.parseInt(nextLine);
					// TODO make user into a new user from list of users in database based on given
					// acctId
					fillRemainingMenuOptions();
					break;
				}
			} catch (Exception e) {
				System.out.println("Not a valid input");
				continue;
			}
		}
	}

	private void fillBasicMainMenuOptions() {
		mainMenuOptions.add("Search for Movie");
		mainMenuOptions.add("Search for Play");
		mainMenuOptions.add("Search for Concert");
		mainMenuOptions.add("View Watchlist");
		mainMenuOptions.add("Search for Venue");
		mainMenuOptions.add("View Ticket Purchases");
		mainMenuOptions.add("Request Ticket Refund");
		mainMenuOptions.add("View Customer Support Information");
	}

	private void fillRemainingMenuOptions() {
		// TODO if user is customer
		mainMenuOptions.add("Logout");
		// TODO if user is employee
		mainMenuOptions.add("Input Event");
		mainMenuOptions.add("Input Theater Info");
		mainMenuOptions.add("Input Discount");
		mainMenuOptions.add("Input Ticket Prices");
		mainMenuOptions.add("Refund Ticket");
		mainMenuOptions.add("Logout");
		// TODO if user is admin
		mainMenuOptions.add("Input Event");
		mainMenuOptions.add("Input Theater Info");
		mainMenuOptions.add("Manage Venues");
		mainMenuOptions.add("Logout");
		// TODO if user is guest
		mainMenuOptions.add(0, "Create Account");
		mainMenuOptions.add("Exit");
	}

	private void displayMenuOptions() {
		System.out.println("\n********** Main Menu **********");
		for (int i = 0; i < mainMenuOptions.size(); i++) {
			System.out.println((i + 1) + ". " + mainMenuOptions.get(i));
		}
		System.out.println();
	}

	private int getUserCommand(int numCommands) {
		System.out.println("Type the number corresponding to the action you want to take");
		nextLine = scanner.nextLine();
		int command = Integer.parseInt(nextLine) - 1;
		if (command >= 0 && command <= numCommands - 1)
			return command;
		return -1;

	}

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
			requestRefund();
			break;
		case (6):
			viewCustomerSupport();
			break;
		}
	}

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
			requestRefund();
			break;
		case (6):
			viewCustomerSupport();
			break;
		case (7):
			inputEvent();
			break;
		case (8):
			inputTheaterInfo();
			break;
		case (9):
			inputDiscount();
			break;
		case (10):
			inputTicketPrices();
			break;
		case (11):
			refundTickets();
			break;
		}
	}

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
			requestRefund();
			break;
		case (6):
			viewCustomerSupport();
			break;
		case (7):
			inputEvent();
			break;
		case (8):
			inputTheaterInfo();
			break;
		case (9):
			manageVenues();
			break;
		}
	}

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
			requestRefund();
			break;
		case (7):
			viewCustomerSupport();
			break;
		}
	}

	private void createAccount() {
		System.out.println("\n********** Create New Account **********");
		System.out.println("Type in your name");
		String name = scanner.nextLine();

		System.out.println("Type in your birthday (MM/DD/YYYY");
		String birthday = scanner.nextLine();

		System.out.println("Type in your phone number");
		int phoneNum = Integer.parseInt(scanner.nextLine());

		System.out.println("Type in your home address");
		String address = scanner.nextLine();

		System.out.println("Type in your email");
		String email = scanner.nextLine();

		// TODO user.createAccount(); ----- (turns a guest into a Customer)

		System.out.println("Your account has been created");
	}

	private void findMovie() {
		System.out.println("\n********** Search for Movie **********");
		System.out.println("Type in the movie you want to search for");
		
		String movie = "";
		while(true) {
			movie = scanner.nextLine();
			try {
				// TODO user.searchForEvent(movie);
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
				displayMovieInfo();
				break;
			case (1):
				bookMovieTickets();
				break;
			case (2):
				rateMovie();
				break;
			case (3):
				writeMovieReview();
				break;
			case (4):
				addToWatchlist(movie);
				break;
			}
		}
	}

	private void displayMovieInfo() {
		System.out.println("Movie Info:");
		// TODO user.lookAtBasicEventInfo(movie);
	}

	private void bookMovieTickets() {
		System.out.println("Type in the date you want to see this movie (MM/DD/YYYY");
		String date = scanner.nextLine();

		System.out.println("Type in the time you want to see this movie");
		String time = scanner.nextLine();

		System.out.println("Type in the number of adult tickets you want to purchase");
		int numAdultTickets = Integer.parseInt(scanner.nextLine());

		System.out.println("Type in the number of child tickets you want to purchase");
		int numChildTickets = Integer.parseInt(scanner.nextLine());

		// TODO user.bookTickets(movie, date, time, numAdultTickets, numChildTickets);

		System.out.println("Tickets have been booked!");

		System.out.println("Would you like a receipt? (Y/N)");
		while (true) {
			nextLine = scanner.nextLine();
			if (nextLine.equals("Y")) {
				// TODO user.createReceipt()
				System.out.println("Your receipt has been created");
			} else if (nextLine.equals("N")) {
				break;
			} else {
				System.out.println("Invalid input, only Y/N");
			}
		}
	}

	private void rateMovie() {
		System.out.println("Type in your rating for this movie on a scale of 1 to 5");
		
		while(true) {
			try {
				int rating = Integer.parseInt(scanner.nextLine());
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
		
		// TODO user.rateEvent(movie, rating);
		System.out.println("Rating has been recorded");
	}

	private void writeMovieReview() {
		System.out.println("Type in your review for this movie");
		String review = scanner.nextLine();
		// TODO user.writeEventReview(movie, review);
		System.out.println("Review has been recorded");
	}

	private void findPlay() {
		System.out.println("\n********** Search for Play **********");
		System.out.println("Type in the play you want to search for");
		
		String play = "";
		while(true) {
			try {
				play = scanner.nextLine();
				// TODO user.searchForEvent(play);
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
				displayPlayInfo();
				break;
			case (1):
				bookPlayTickets();
				break;
			case (2):
				ratePlay();
				break;
			case (3):
				writePlayReview();
				break;
			case (4):
				addToWatchlist(play);
				break;
			}
		}
	}

	private void displayPlayInfo() {
		System.out.println("Play Info:");
		// TODO user.lookAtBasicEventInfo(play);
	}

	private void bookPlayTickets() {
		System.out.println("Type in the date you want to see this play (MM/DD/YYYY");
		String date = scanner.nextLine();

		System.out.println("Type in the time you want to see this play");
		String time = scanner.nextLine();

		System.out.println("Type in the number of adult tickets you want to purchase");
		int numAdultTickets = Integer.parseInt(scanner.nextLine());

		System.out.println("Type in the number of child tickets you want to purchase");
		int numChildTickets = Integer.parseInt(scanner.nextLine());

		// TODO user.bookTickets(play, date, time, numAdultTickets, numChildTickets);

		System.out.println("Tickets have been booked!");

		System.out.println("Would you like a receipt? (Y/N)");
		while (true) {
			nextLine = scanner.nextLine();
			if (nextLine.equals("Y")) {
				// TODO user.createReceipt()
				System.out.println("Your receipt has been created");
			} else if (nextLine.equals("N")) {
				continue;
			} else {
				System.out.println("Invalid input, only Y/N");
			}
		}
	}

	private void ratePlay() {
		System.out.println("Type in your rating for this play on a scale of 1 to 5");
		
		while(true) {
			try {
				int rating = Integer.parseInt(scanner.nextLine());
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
		
		// TODO user.rateEvent(play, rating);
		System.out.println("Rating has been recorded");
	}

	private void writePlayReview() {
		System.out.println("Type in your review for this play");
		String review = scanner.nextLine();
		// TODO user.writeEventReview(play, review);
		System.out.println("Review has been recorded");
	}

	private void findConcert() {
		System.out.println("\n********** Search for Concert **********");
		System.out.println("Type in the concert you want to search for");
		
		String concert = "";
		while(true) {
			try {
				concert = scanner.nextLine();
				// TODO user.searchForEvent(play);
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
				displayConcertInfo();
				break;
			case (1):
				bookConcertTickets();
				break;
			case (2):
				rateConcert();
				break;
			case (3):
				writeConcertReview();
				break;
			case (4):
				addToWatchlist(concert);
				break;
			}
		}
	}

	private void displayConcertInfo() {
		System.out.println("Concert Info:");
		// TODO user.lookAtBasicEventInfo(concert);
	}

	private void bookConcertTickets() {
		System.out.println("Type in the date you want to see this concert (MM/DD/YYYY");
		String date = scanner.nextLine();

		System.out.println("Type in the time you want to see this concert");
		String time = scanner.nextLine();

		System.out.println("Type in the number of adult tickets you want to purchase");
		int numAdultTickets = Integer.parseInt(scanner.nextLine());

		System.out.println("Type in the number of child tickets you want to purchase");
		int numChildTickets = Integer.parseInt(scanner.nextLine());

		// TODO user.bookTickets(concert, date, time, numAdultTickets, numChildTickets);

		System.out.println("Tickets have been booked!");

		System.out.println("Would you like a receipt? (Y/N)");
		while (true) {
			nextLine = scanner.nextLine();
			if (nextLine.equals("Y")) {
				// TODO user.createReceipt()
				System.out.println("Your receipt has been created");
			} else if (nextLine.equals("N")) {
				continue;
			} else {
				System.out.println("Invalid input, only Y/N");
			}
		}
	}

	private void rateConcert() {
		System.out.println("Type in your rating for this concert on a scale of 1 to 5");
		
		while(true) {
			try {
				int rating = Integer.parseInt(scanner.nextLine());
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
		
		// TODO user.rateEvent(concert, rating);
		System.out.println("Rating has been recorded");
	}

	private void writeConcertReview() {
		System.out.println("Type in your review for this concert");
		String review = scanner.nextLine();
		// TODO user.writeEventReview(concert, review);
		System.out.println("Review has been recorded");
	}

	private void addToWatchlist(String event) {
		// TODO user.addToWatchlist(event);
		System.out.println("Added to watchlist");
	}

	private void viewWatchlist() {
		while (true) {
			System.out.println("\n********** Watchlist **********");
			// TODO user.displayWatchlist();

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
				// TODO user.addToWatchlist(showAdded);
			case (1):
				System.out.println("Type in the name of the show you want to remove from the watchlist");
				String showRemoved = scanner.nextLine();
				// TODO user.removeFromWatchlist(showRemoved);
			}
		}
	}

	private void findVenue() {// TODO expand on this?
		System.out.println("\n********** Search for Concert **********");
		System.out.println("Type in the concert you want to search for");
		
		String venue = "";
		while(true) {
			try {
				venue = scanner.nextLine();
				// TODO user.searchForVenue(venue);
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
				// TODO user.updateHomeVenue(venue);
				System.out.println("Your home venue has been updated");
			}
		}
	}

	private void requestRefund() {
		System.out.println("\n********** Request Refund **********");
		System.out.println("Type in the name of the event you want a refund from");
		String eventRefunded = scanner.nextLine();
		System.out.println("Type in the number of tickets you want to refund");
		int numTicketsRefunded = Integer.parseInt(scanner.nextLine());
		// TODO user.requestRefund(eventRefunded, numTicketsRefunded);
		System.out.println("Your refund request has been sent to be processed");
	}

	private void viewCustomerSupport() {
		System.out.println("\n********** Customer Support **********");
		// TODO main.displaySupportInfo();

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

	private void inputEvent() {
		System.out.println("\nType in the type of the show to be inputted");
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

		// TODO user.inputEvent(showType, showName, date, time, rating, ageRating);

		System.out.println("This event has been added");
	}

	private void inputTheaterInfo() {
		System.out.println("\nType in the name of the theater");
		char theaterName = scanner.nextLine().charAt(0);

		System.out.println("Type in the type of the show to be inputted");
		String showType = scanner.nextLine();

		System.out.println("Type in the name of the show at this theater");
		String showName = scanner.nextLine();

		System.out.println("Type in the date this show is occurring");
		String date = scanner.nextLine();

		System.out.println("Type in the time this show is occurring on this date");
		String time = scanner.nextLine();

		// TODO user.inputTheaterInfo(theaterName, showType, showName, date, time);

		System.out.println("This theater's info. has been updated");
	}

	private void inputDiscount() {
		System.out.println("\nType in the percent discount");
		int discount = Integer.parseInt(scanner.nextLine());

		// TODO user.inputDiscount(discount);

		System.out.println("Discount has been applied");
	}

	private void inputTicketPrices() {
		System.out.println("\nType in the price of an adult ticket");
		double adultTicketPrice = Double.parseDouble(scanner.nextLine());

		System.out.println("Type in the price of a child ticket");
		double childTicketPrice = Double.parseDouble(scanner.nextLine());

		// TODO user.inputTicketPrices(adultTicketPrice, childTicketPrice);

		System.out.println("Ticket prices have been set");
	}

	private void refundTickets() {
		// TODO user.displayTicketsRefunded();
		System.out.println("Type in the name of the customer to give a refund to");
		String customerName = scanner.nextLine();

		// TODO user.refundTickets(customer);

		System.out.println("Customer's refund has been processed");
	}

	private void manageVenues() {
		while (true) {
			System.out.println("\n********** Venues **********");
			// TODO user.displayVenues();

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
				// TODO user.addVenue(venueAdded);
			case (1):
				System.out.println("Type in the name of the venue you want to remove from the list");
				String venueRemoved = scanner.nextLine();
				// TODO user.removeVenue(venueRemoved);
			}
		}
	}

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
