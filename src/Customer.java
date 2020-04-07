/**
 * Customer.java - A class to represent a user with an existing account in the
 * main system.
 * 
 * @author Taylor Norton
 *
 */
public class Customer extends User {

	/**
	 * Parameterized concstructor.
	 * 
	 * @param name        - A variable of type String.
	 * @param birthday    - A variable of type String.
	 * @param phoneNumber - A variable of type String.
	 * @param address     - A variable of type String.
	 * @param email       - A variable of type String.
	 */
	public Customer(String name, String birthday, String phoneNumber, String address, String email) {
		super(name, birthday, phoneNumber, address, email);
	}

	/**
	 * Returns a String representation of a Customer.
	 * 
	 * @return String - The String representation of a Customer.
	 */
	public String toString() {
		return "\n\nName: " + this.getName() + "\nBirthday: " + this.getBirthday() + "\nPhone Number: "
				+ this.getPhoneNumber() + "\nAddress: " + this.getAddress() + "\nEmail: " + this.getEmail();
	}
}
