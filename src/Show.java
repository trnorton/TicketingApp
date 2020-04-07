import java.util.ArrayList;

/**
 * Show.java - An abstract base class to represent a general show
 * 
 * @author Taylor Norton, Lukacs Ablonczy
 */
public abstract class Show {

	private String name;
	private int offRating;
	private int ageRating;

	private ArrayList<String> reviews;

	private ArrayList<Integer> custRatings;
	private ArrayList<String> producers;

	/**
	 * Returns the name of the show.
	 * 
	 * @return String - The name of the show.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the show's name to a given String.
	 * 
	 * @param name - A variable of type String.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the offical rating of the show.
	 * 
	 * @return int - The official rating of the show.
	 */
	public int getOffRating() {
		return offRating;
	}

	/**
	 * Sets the show's official rating to a given int.
	 * 
	 * @param offRating - A variable of type int.
	 */
	public void setOffRating(int offRating) {
		this.offRating = offRating;
	}

	/**
	 * Returns the age rating of the show.
	 * 
	 * @return int - The age rating of the show.
	 */
	public int getAgeRating() {
		return ageRating;
	}

	/**
	 * Sets the show's age rating to a given int.
	 * 
	 * @param ageRating - A variable of type int.
	 */
	public void setAgeRating(int ageRating) {
		this.ageRating = ageRating;
	}

	/**
	 * Prints out the customer reviews of the show.
	 */
	public void displayReviews() {
		for (String review : reviews) {
			System.out.println(review);
		}
	}

	/**
	 * Adds a given String to the list of customer reviews.
	 * 
	 * @param r - A variable of type String.
	 */
	public void addReview(String review) {
		reviews.add(review);
	}

	/**
	 * Removes a given String from the list of customer reviews.
	 * 
	 * @param r - A variable of type String.
	 */
	public void removeReview(String review) {
		reviews.remove(review);
	}

	/**
	 * Prints out the customer ratings of the show.
	 */
	public void displayCustRatings() {
		for (int rating : custRatings) {
			System.out.println(rating);
		}
	}

	/**
	 * Adds a given Integer to the list of customer ratings.
	 * 
	 * @param r - A variable of type Integer.
	 */
	public void addCustRating(Integer rating) {
		custRatings.add(rating);
	}

	/**
	 * Removes a given Integer from the list of customer ratings.
	 * 
	 * @param r - A variable of type Integer.
	 */
	public void removeCustRating(Integer rating) {
		custRatings.remove(rating);
	}

	private int getAverageCustRating() {
		int sum = 0;
		for (int rating : custRatings) {
			sum += rating;
		}

		if (custRatings.size() == 0)
			return 0;

		return sum / custRatings.size();
	}

	/**
	 * Adds a given String to the list of producers.
	 * 
	 * @param producer - A variable of type String.
	 */
	public void addProducer(String producer) {
		if (producer == null) {
			System.out.println("Can't add null producer...");
			return;
		}

		System.out.println("Adding producer " + producer);
		producers.add(producer);
	}

	/**
	 * Removes a given String from the list of producers.
	 * 
	 * @param producer - A variable of type String.
	 */
	public void removeProducer(String producer) {
		if (producer == null) {
			System.out.println("Can't remove null producer...");
			return;
		}

		if (!producers.contains(producer)) {
			System.out.println("Producer " + producer + " not in list...not removed");
			return;
		}

		System.out.println("Removing producer " + producer);
		producers.remove(producer);
	}

	/**
	 * Sets the list of producers to the given list of producers.
	 * 
	 * @param producers - A variable of type ArrayList<String>.
	 */
	public void setProducers(ArrayList<String> producers) {
		this.producers = producers;
	}

	/**
	 * Returns the list of producers.
	 * 
	 * @return ArrayList<String> - The list of producers.
	 */
	public ArrayList<String> getProducers() {
		return producers;
	}

	/**
	 * Returns the list of customer reviews.
	 * 
	 * @return ArrayList<String> - The list of reviews.
	 */
	public ArrayList<String> getReviews() {
		return this.reviews;
	}

	/**
	 * Sets the list of reviews to the given list of reviews.
	 * 
	 * @param reviews - A variable of type ArrayList<String>.
	 */
	public void setReviews(ArrayList<String> reviews) {
		this.reviews = reviews;
	}

	/**
	 * Returns the list of customer ratings.
	 * 
	 * @return ArrayList<Integer> - The list of ratings.
	 */
	public ArrayList<Integer> getCustRatings() {
		return custRatings;
	}

	/**
	 * Sets the list of ratings to the given list of ratings.
	 * 
	 * @param custRatings - A variable of type ArrayList<Integer>.
	 */
	public void setCustRatings(ArrayList<Integer> custRatings) {
		this.custRatings = custRatings;
	}

	/**
	 * Returns the String representation of a Show.
	 * 
	 * @return String - The String representation of a Show.
	 */
	public String toString() {
		StringBuilder producersString = new StringBuilder();
		for (String producer : producers)
			producersString.append(producer).append(" ");

		return " Name: " + this.getName() + "\nIMDB Rating: " + offRating + "\nAge Rating: " + getAgeRating()
				+ "\nOverall Viewer Rating: " + getAverageCustRating() + "\nProducers: " + producersString;
	}
}
