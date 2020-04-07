import java.util.ArrayList;

/**
 * Theater.java - A class to represent a theater inside of a venue.
 * 
 * @author Taylor Norton
 *
 */
public class Theater {

	private char name;
	private Seat[][] seats;
	private ArrayList<Event> events;
	private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

	/**
	 * Parametrized constructor.
	 * 
	 * @param name       - A variable of type char.
	 * @param numRows    - A variable of type int.
	 * @param numColumns - A variable of type int.
	 */
	public Theater(char name, int numRows, int numColumns) {
		this.name = name;
		seats = new Seat[numRows][numColumns];
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				seats[i][j] = new Seat(alphabet[i], j + 1);
			}
		}
		events = new ArrayList<Event>();
	}

	/**
	 * Returns the name of the Theater.
	 * 
	 * @return char - The name of the Theater.
	 */
	public char getName() {
		return name;
	}

	/**
	 * Sets the name of the Theater to a given char.
	 * 
	 * @param name - A variable of type char.
	 */
	public void setName(char name) {
		this.name = name;
	}

	/**
	 * Prints out a visual representation of the Theater.
	 */
	public void displaySeats() {
		System.out.print("   ");
		for (int i = 0; i < seats[0].length; i++)
			if (i < 8) {
				System.out.print((i + 1) + "  ");
			} else {
				System.out.print((i + 1) + " ");
			}
		System.out.println();
		for (int i = 0; i < seats.length; i++) {
			System.out.print(alphabet[i] + " ");

			for (int j = 0; j < seats[i].length; j++) {
				if (seats[i][j].checkIfTaken() == true)
					seats[i][j].setSeatStatus("X");
				System.out.print(seats[i][j].displaySeat() + "");
			}
			System.out.println();
		}
	}

	/**
	 * Adds a given Event to the list of Events.
	 * 
	 * @param e - A variable of tpye Event.
	 */
	public void addEvent(Event e) {
		events.add(e);
	}

	/**
	 * Removes a given Event from the list of Events.
	 * 
	 * @param e - A variable of tpye Event.
	 */
	public void removeEvent(Event e) {
		events.remove(e);
	}

	/**
	 * Prints out all the events within a Theater.
	 */
	public void displayEvents() {
		for (Event e : events) {
			System.out.println(e);
		}
	}

	/**
	 * Returns a given Event if it is showing in the Theater.
	 * 
	 * @param event - A variable of type Event.
	 * @return Event - The Event found in the theater.
	 */
	public Event getEvent(Event event) {
		for (Event e : events) {
			if (e.toString().trim().equalsIgnoreCase(event.toString()))
				return e;
		}
		return null;
	}

	/**
	 * Returns whether or not the Theater has an open seat.
	 * 
	 * @return boolean - If there's an open seat or not.
	 */
	public boolean hasAvailableSeat() {
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				if (seats[i][j].getSeatStatus().equals(" "))
					return true;
			}
		}
		return false;
	}

	/**
	 * Returns an available Seat within the Theater.
	 * 
	 * @return Seat - The available Seat within the Theater.
	 */
	public Seat getAvailableSeat() {
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				if (seats[i][j].checkIfTaken() == false)
					return seats[i][j];
			}
		}
		return null;
	}

	/**
	 * Returns the Seat with at the given row and column in the Theater.
	 * 
	 * @param row - A variable of type char.
	 * @param col - A variable of type int.
	 * @return Seat - The desired Seat.
	 */
	public Seat getSeat(char row, int col) {
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				if (seats[i][j].getRow() == row && seats[i][j].getColumn() == col) {
					return seats[i][j];
				}
			}
		}
		return null;
	}

	/**
	 * Returns a String representation of the Theater.
	 * 
	 * @return String - The String representation of the Theater.
	 */
	public String toString() {
		return "" + name;
	}
}
