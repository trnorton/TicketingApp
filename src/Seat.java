/**
 * Representation of a seat at a theatre
 * Has a row letter, column number, and additional information
 * @author
 */
public class Seat {

	private char row;
	private int column;
	private boolean isTaken;
	private String seatStatus;

	/**
	 * Parametrized constructor for a seat
	 * @param row row letter for the seat
	 * @param column column letter for the seat
	 */
	public Seat(char row, int column) {
		this.row = row;
		this.column = column;
		isTaken = false;
		seatStatus = " ";
	}

	/**
	 * Method which displays the seat's current row assignment
	 * @return the seat's current row letter
	 */
	public char getRow() {
		return row;
	}

	/**
	 * Method which sets a new row assignment for the seat
	 * @param row the seat's new row letter
	 */
	public void setRow(char row) {
		this.row = row;
	}

	/**
	 * Method which displays the seat's current column assignment
	 * @return the seat's current column number
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Method which sets a new column assignment for the seat
	 * @param column the seat's new column number
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * Method which checks if a seat is taken
	 * @return true if seat is taken, false if not
	 */
	public boolean checkIfTaken() {
		return isTaken;
	}

	/**
	 * Method which checks the seat's status
	 * @return the current seat status
	 */
	public String getSeatStatus() {
		return seatStatus;
	}

	/**
	 * Method which sets a new status for the seat
	 * @param seatStatus the new status for the current seat
	 */
	public void setSeatStatus(String seatStatus) {
		this.seatStatus = seatStatus;
	}

	/**
	 * Method which changes the availability of the current seat
	 */
	public void changeSeatAvailability() {
		isTaken = !isTaken;
	}

	/**
	 * @return the combined row and column assignment of the seat
	 */
	public String toString() {
		return "" + row + column;
	}

	/**
	 * @return the seat's current status
	 */
	public String displaySeat() {
		return "[" + seatStatus + "]";
	}
}
