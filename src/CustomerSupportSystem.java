/**
 * CustomerSupportSystem.java - A class to represent a customer support page.
 * 
 * @author Taylor Norton
 *
 */
public class CustomerSupportSystem {

	private String description;
	private int phoneNumber;

	/**
	 * Default Constructor.
	 */
	public CustomerSupportSystem() {
		description = "This is the customer support page. If you are experiencing issues with the app, call the number below:";
		phoneNumber = 1234567890;
	}

	/**
	 * Returns a String representation of the CustomerSupportSystem.
	 * 
	 * @return String - The representation of the CustomerSupportSystem.
	 */
	public String toString() {
		return description + "\n" + phoneNumber;
	}
}
