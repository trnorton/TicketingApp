
public class Seat {

	private char row;
	private int column;
	private String type;
	private boolean isTaken;
	private String seatStatus;

	public Seat(char row, int column, String type) {
		this.row = row;
		this.column = column;
		this.type = type;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
		return "[" + seatStatus + "]";
	}
}
