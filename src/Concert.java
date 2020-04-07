import java.util.ArrayList;


/**
 * @author Lukacs Ablonczy
 * Defines the Concert Show type
 */
public class Concert extends Show {

	private ArrayList<String> performers;

	/**
	 * Blank constructor, used for creating empty Show types
	 */
	public Concert(){

	}
	/**
	 * Constructor for Concert type
	 * @param name Name of show
	 * @param ageRating AgeRating of show
	 * @param performers String Arraylist of performers of concert
	 * @param producers producers of show
	 */
	public Concert(String name, int offRating, int ageRating, ArrayList<String> performers, ArrayList<String> producers) {
		this.setName(name);
		this.setAgeRating(ageRating);
		this.setOffRating(offRating);
		this.setProducers(producers);
		this.setPerformers(performers);
	}

	/**
	 * Constructor for Concert type
	 * @param name Name of show
	 * @param offRating IMDB Rating of show
	 * @param ageRating AgeRating of show
	 * @param reviews Reviews of show
	 * @param custRatings Customer Ratings of show
	 * @param producers producers of show
	 * @param performers Performers of Concert
	 */
	public Concert(String name, int offRating, int ageRating, ArrayList<String> reviews, ArrayList<Integer> custRatings, ArrayList<String> producers, ArrayList<String> performers) {
		this.setName(name);
		this.setOffRating(offRating);
		this.setAgeRating(ageRating);
		this.setReviews(reviews);
		this.setCustRatings(custRatings);
		this.setProducers(producers);
		this.setPerformers(performers);
	}

	/**
	 * Place a performer into this concert's list of performers
	 * @param performer String name of performer
	 */
	public void addPerformer(String performer) {
		if (performer == null) {
			System.out.println("Cannot add null actor...");
			return;
		}

		if (performers.contains(performer)) {
			System.out.println("Performer " + performer + " is already in list...will not add again");
			return;
		}

		performers.add(performer);
	}

	/**
	 * Remove a performer from this concert's list of performers
	 * @param performer String name of performer
	 */
	public void removePerformer(String performer) {
		if (performer == null) {
			System.out.println("Can't remove null performer...");
			return;
		}

		if (!performer.contains(performer)) {
			System.out.println("performer " + performer + " not in list...not removed");
			return;
		}

		System.out.println("Removing performer " + performer);
		performers.remove(performer);
	}

	/**
	 * Getter for arraylist of performers
	 * @return String arraylist containing performers' names
	 */
	public ArrayList<String> getPerformers() {
		return performers;
	}

	/**
	 * Setter for this concert's list of performers
	 * @param performers String arraylist containing names of performers
	 */
	public void setPerformers(ArrayList<String> performers) {
		if (performers == null) {
			System.out.println(this + " can't set performers bc it is null");
			return;
		}

		this.performers = performers;
	}

	/**
	 * Returns string describing this concert
	 * @return Name: Ratings: Producers: Performers:
	 */
	@Override
	public String toString() {
		StringBuilder performersString = new StringBuilder();
		for (String actor : performers)
			performersString.append(actor).append(" ");

		return "Concert" + super.toString() + "\nPerformers: " + performersString + "\n";
	}
}
