
public class Customer extends User {

	public Customer(String name, int birthday, int phoneNumber, String address, String email) {
		super(name, birthday, phoneNumber, address, email);
	}
	
	public void removeEventReview(Event e) {
		//TODO
	}
	
	public String toString() {
		return "";
		//TODO return "\n\nName: " + name+"\nBirthday: " + birthday + "\nPhone Number: " + phoneNumber+"\nAddress: "+ address+ "\nEmail: "+this.email;
	}
}
