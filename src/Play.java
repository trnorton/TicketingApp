import java.util.ArrayList;

/**
 * @author Lukacs Ablonczy Defines the Play Show type
 */
public class Play extends Show {

	private ArrayList<String> majorActors = new ArrayList<String>();

	/**
	 * Blank constructor, used for creating empty Show types and later setting values
	 */
	public Play() {

	}

	/**
	 * Constructor for Play type
	 * 
	 * @param name        Name of show
	 * @param ageRating   AgeRating of show
	 * @param majorActors String Arraylist of actors of movie
	 * @param producers   producers of show
	 */
	public Play(String name, int offRating, int ageRating, ArrayList<String> majorActors, ArrayList<String> producers) {
		this.setName(name);
		this.setOffRating(offRating);
		this.setAgeRating(ageRating);
		this.setProducers(producers);
		this.setMajorActors(majorActors);
	}

	/**
	 * Constructor for Play type
	 * 
	 * @param name        Name of show
	 * @param offRating   IMDB Rating of show
	 * @param ageRating   AgeRating of show
	 * @param reviews     Reviews of show
	 * @param custRatings Customer Ratings of show
	 * @param producers   producers of show
	 * @param majorActors String Arraylist of actors of movie
	 */
	public Play(String name, int offRating, int ageRating, ArrayList<String> reviews, ArrayList<Integer> custRatings,
			ArrayList<String> producers, ArrayList<String> majorActors) {
		this.setName(name);
		this.setOffRating(offRating);
		this.setAgeRating(ageRating);
		this.setReviews(reviews);
		this.setCustRatings(custRatings);
		this.setProducers(producers);
		this.setMajorActors(majorActors);
	}

	/**
	 * Places actor's name into majorActors ArrayList
	 * 
	 * @param actor String name of actors
	 */
	public void addMajorActor(String actor) {
		if (actor == null) {
			System.out.println("Cannot add null actor...");
			return;
		}

		if (majorActors.contains(actor)) {
			System.out.println("Actor " + actor + " is already in list...will not add again");
			return;
		}

		majorActors.add(actor);
	}

	/**
	 * Removes name from majorActors arraylist
	 * 
	 * @param actor String name of actor to remove
	 */
	public void removeMajorActor(String actor) {
		if (actor == null) {
			System.out.println("Can't remove null actor...");
			return;
		}

		if (!actor.contains(actor)) {
			System.out.println("Actor " + actor + " not in list...not removed");
			return;
		}

		System.out.println("Removing actor " + actor);
		majorActors.remove(actor);
	}

	/**
	 * Returns arraylist of major actors
	 * 
	 * @return String arraylist majorActors
	 */
	public ArrayList<String> getMajorActors() {
		return majorActors;
	}

	/**
	 * Setter for this movie's majorActors
	 * 
	 * @param majorActors String arraylist of actor's names
	 */
	public void setMajorActors(ArrayList<String> majorActors) {
		if (majorActors == null) {
			System.out.println(this + " can't set majorActors bc it is null");
			return;
		}

		this.majorActors = majorActors;
	}

	/**
	 * Returns string describing movie
	 * 
	 * @return Name: Ratings: Producers: Famous Actors:
	 */
	@Override
	public String toString() {
		StringBuilder actorsString = new StringBuilder();
		for (String actor : majorActors)
			actorsString.append(actor).append(" ");

		return "Play" + super.toString() + "\nFamous Actors: " + actorsString + "\n";
	}
}
