
public class CustomerSupportSystem {

	private String description;
	private int phoneNumber;

	public CustomerSupportSystem() {
		description = "This is the customer support page. If you are experiencing issues with the app, call the number below:";
		phoneNumber = 1234567890;
	}

	private void displaySupportInfo() {
		System.out.println(description + phoneNumber);
	}
}
