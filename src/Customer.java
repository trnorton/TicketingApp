
public class Customer extends User {

	public Customer(String name, String birthday, String phoneNumber, String address, String email) {
		super(name, birthday, phoneNumber, address, email);
	}
	
	public void removeEventReview(Event e) {
		//TODO
	}
	
	public String toString() {
		return "\n\nName: " + this.getName() +"\nBirthday: " + this.getBirthday() + "\nPhone Number: " + this.getPhoneNumber() +"\nAddress: "+ this.getAddress() + "\nEmail: "+this.getEmail();
	}
}
