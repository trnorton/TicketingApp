import java.util.ArrayList;
import java.util.Scanner;

public class TicketingAppUI {

	private static final String WELCOME_MESSAGE = "Welcome to our Ticketing App";
	private Scanner scanner;
	private String nextLine;
	//TODO private User user;
	private ArrayList<String> mainMenuOptions;
	
	TicketingAppUI() {
		scanner = new Scanner(System.in);
		mainMenuOptions = new ArrayList<String>();
		fillBasicMainMenuOptions();
	}
	
	public void run() {
		System.out.println(WELCOME_MESSAGE);
		
		//ask if user is guest or not, login/proceed as needed
		logIn();
			//display Main Menu, which has options on how to proceed
		
			//value for user commands
			//invalid value goes to a continue
			//break if logging out
			//switch case for different cases
			
		
	}
	
	private void logIn() {
		while(true) {
			System.out.println("If you are a returning user, type in your account ID. Otherwise, type 0");
			nextLine = scanner.nextLine();
			try {
				if(nextLine.equals("0")) {
					//TODO user = new User(); ------> basic guest user
					fillRemainingMenuOptions();
					break;
				}
				else {
					int acctId = Integer.parseInt(nextLine);
					//TODO make user into a new user from list of users in database based on given acctId
					fillRemainingMenuOptions();
					break;
				}
			} catch(Exception e) {
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
		//TODO if user is customer
		mainMenuOptions.add("Logout");
		//TODO if user is employee
		mainMenuOptions.add("Input Event");
		mainMenuOptions.add("Input Theater Info");
		mainMenuOptions.add("Input Discount");
		mainMenuOptions.add("Input Ticket Prices");
		mainMenuOptions.add("Refund Ticket");
		mainMenuOptions.add("Logout");
		//TODO if user is admin
		mainMenuOptions.add("Manage Venues");
		mainMenuOptions.add("Input Event");
		mainMenuOptions.add("Input Theater Info");
		mainMenuOptions.add("Logout");
		//TODO if user is guest
		mainMenuOptions.add("Exit");
	}
	
	public static void main(String[] args) {
		TicketingAppUI appInterface = new TicketingAppUI();
		appInterface.run();

	}

}
