import java.util.ArrayList;

public abstract class Show {

	private String name;
	private int offRating;
	private int ageRating;
	private ArrayList<String> reviews;
	private ArrayList<Integer> custRatings;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOffRating() {
		return offRating;
	}

	public void setOffRating(int offRating) {
		this.offRating = offRating;
	}

	public int getAgeRating() {
		return ageRating;
	}

	public void setAgeRating(int ageRating) {
		this.ageRating = ageRating;
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

	public void addCustRating(Integer r) {
		custRatings.add(r);
	}

	public void removeCustRating(Integer r) {
		custRatings.remove(r);
	}

	private int getAverageCustRating() {
		int sum = 0;
		for (int r : custRatings) {
			sum += r;
		}
		return sum / custRatings.size();
	}

	public abstract String toString();
}
