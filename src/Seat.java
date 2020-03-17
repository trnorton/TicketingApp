
public class Seat {

	private char row;
	private int column;
	private String type;
	private boolean isTaken;

	public Seat(char row, int column, String type) {
		this.row = row;
		this.column = column;
		this.type = type;
		isTaken = false;
	}

	public boolean checkIfTaken() {
		return isTaken;
	}

	public void changeSeatAvailability() {
		isTaken = !isTaken;
	}

	public String toString() {
		return "" + row + column;
	}
}
