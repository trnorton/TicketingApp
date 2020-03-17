
public class Theater {

	private char name;
	private Seat[][] seats;
	private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

	public Theater(char name, int numRows, int numColumns) {
		this.name = name;
		seats = new Seat[numRows][numColumns];
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				seats[i][j] = new Seat(alphabet[i], j + 1, "Regular");
			}
		}
	}

	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}

	public void displaySeats() {
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				System.out.print(seats[i][j] + " ");
			}
			System.out.println();
		}
	}
}
