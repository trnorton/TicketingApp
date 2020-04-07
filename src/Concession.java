/**
 * Concession.java - A class to represent a food/drink at concessions.
 * 
 * @author Taylor Norton
 *
 */
public class Concession {

	private String name;
	private double price;
	private int quantity;

	/**
	 * Parameterized constructor.
	 * 
	 * @param name     - A value of type String.
	 * @param price    - A value of type int.
	 * @param quantity - A value of type int.
	 */
	public Concession(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	/**
	 * Returns the name of the Concession.
	 * 
	 * @return String - The name of the Concession.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the Concession to a given String value.
	 * 
	 * @param name - A variable of type String.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the price of the Concession.
	 * 
	 * @return int - The price of the Concession.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price of the Concession to a given int value.
	 * 
	 * @param price - A variable of type int.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Returns the quantity of the Concession.
	 * 
	 * @return int - The quantity of the Concession.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity of the Concession to a given int value.
	 * 
	 * @param quantity - A variable of type int.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Returns the String representation of the Concession.
	 * 
	 * @return String - the String representation of the Concession.
	 */
	public String toString() {
		return name + " - Quantity: " + quantity + " - Price: $" + price;
	}
}
