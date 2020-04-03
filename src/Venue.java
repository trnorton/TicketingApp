import java.util.ArrayList;

public class Venue {

	private String name;
	private String address;
	private Theater[] theaters;
	private ArrayList<String> reviews;
	private ArrayList<Integer> custRatings;
	private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private static final int standardNumOfRows = 25;
	private static final int standardNumOfCols = 30;

	public Venue(String name, String address, int numOfTheaters) {
		this.name = name;
		this.address = address;
		theaters = new Theater[numOfTheaters];
		for (int i = 0; i < theaters.length; i++) {
			theaters[i] = new Theater(alphabet[i], standardNumOfRows, standardNumOfCols);
		}
		reviews = new ArrayList<String>();
		custRatings = new ArrayList<Integer>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void displayReviews() {
		for (String r : reviews) {
			System.out.println(r);
		}
	}

	public void addReview(String r) {
		reviews.add(r);
	}

	public void removeReview(String r) {
		reviews.remove(r);
	}

	public void displayCustRatings() {
		for (int r : custRatings) {
			System.out.println(r);
		}
	}

	public void addCustRating(int r) {
		custRatings.add(r);
	}

	public void removeCustRating(int r) {
		custRatings.remove(r);
	}

	private int getAverageCustRating() {
		int sum = 0;
		for (int r : custRatings) {
			sum += r;
		}
		return sum / custRatings.size();
	}

	public String toString() {
		return "Name: " + name + "\nAddress: " + address + "\nOverall Customer Rating: " + getAverageCustRating();
	}
}
