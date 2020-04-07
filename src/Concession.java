/**
 * Representation of a purchaseable concession
 * Has a name, prince, and quantity
 * @author
 */
public class Concession {

	private String name;
	private double price;
	private int quantity;

	/**
	 * Parametrized constructor for Concession
	 * @param name name of the concession
	 * @param price total price of the concession
	 * @param quantity number of instances of that concession being purchased
	 */
	public Concession(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	/**
	 * @return the current name of the concession
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the new name of the concession
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the current price of the concession
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the new price of the concession
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the current number of the concession being purchased
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the new number of concessions being purchased
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return all information about the current Concession
	 */
	public String toString() {
		return name + " - Quantity: " + quantity + " - Price: $" + price;
	}
}
