import java.util.ArrayList;

public class Theater {

	private char name;
	private Seat[][] seats;
	private ArrayList<Event> events;
	private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

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

	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}

	public void displaySeats() {
		System.out.print("   ");
		for(int i = 0; i<seats[0].length;i++)
			if(i<8) {
				System.out.print((i+1) + "  ");
			}
			else {
				System.out.print((i+1) + " ");
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

	public void addEvent(Event e) {
		events.add(e);
	}

	public void removeEvent(Event e) {
		events.remove(e);
	}

	public void displayEvents() {
		for (Event e : events) {
			System.out.println(e);
		}
	}
	
	public Event getEvent(Event event) {
		for(Event e : events) {
			if(e.toString().equals(event.toString()))
				return e;
		}
		return null;
	}
	
	public boolean hasAvailableSeat() {
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				if (seats[i][j].getSeatStatus().equals(" "))
					return true;
			}
		}
		return false;
	}
	
	public Seat getSeat(char row, int col) {
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				if(seats[i][j].getRow() == row && seats[i][j].getColumn() == col) {
					return seats[i][j];
				}
			}
		}
		return null;
	}
}
