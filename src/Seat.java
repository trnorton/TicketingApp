
public class Seat {

	private char row;
	private int column;
	private boolean isTaken;
	private String seatStatus;

	public Seat(char row, int column) {
		this.row = row;
		this.column = column;
		isTaken = false;
		seatStatus = " ";
	}

	public char getRow() {
		return row;
	}

	public void setRow(char row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public boolean checkIfTaken() {
		return isTaken;
	}

	public String getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(String seatStatus) {
		this.seatStatus = seatStatus;
	}

	public void changeSeatAvailability() {
		isTaken = !isTaken;
	}

	public String toString() {
		return "" + row + column;
	}
	
	public String displaySeat() {
		return "[" + seatStatus + "]";
	}
}
