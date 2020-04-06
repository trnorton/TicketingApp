import java.util.ArrayList;

/**
 * @author Lukacs Ablonczy
 * Defines the Movie Show type
 */
public class Movie extends Show {

	private ArrayList<String> majorActors;
	private String genre;

	/**
	 * Blank constructor, used for creating empty Show types
	 */
	public Movie(){

	}

	/**
	 * Constructor for Concert type
	 * @param name Name of show
	 * @param ageRating AgeRating of show
	 * @param genre Genre of movie
	 * @param majorActors String Arraylist of actors of movie
	 * @param producers producers of show
	 */
	public Movie(String name, int ageRating, String genre, ArrayList<String> majorActors, ArrayList<String> producers) {
		this.setName(name);
		this.setAgeRating(ageRating);
		this.setGenre(genre);
		this.setProducers(producers);
		this.setMajorActors(majorActors);
	}

	/**
	 * Constructor for Concert type
	 * @param name Name of show
	 * @param offRating IMDB Rating of show
	 * @param ageRating AgeRating of show
	 * @param reviews Reviews of show
	 * @param custRatings Customer Ratings of show
	 * @param producers producers of show
	 * @param majorActors String Arraylist of actors of movie
	 * @param genre Genre of movie
	 */
	public Movie(String name, int offRating, int ageRating, ArrayList<String> reviews, ArrayList<Integer> custRatings, ArrayList<String> producers, ArrayList<String> majorActors, String genre) {
		this.setName(name);
		this.setOffRating(offRating);
		this.setAgeRating(ageRating);
		this.setReviews(reviews);
		this.setCustRatings(custRatings);
		this.setProducers(producers);
		this.setMajorActors(majorActors);
		this.setGenre(genre);
	}

	/**
	 * Getter for genre attribute
	 * @return String genre of this movie
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Setter for genre attribute
	 * @param genre String genre for this movie
	 */
	public void setGenre(String genre) {
		if (genre == null) {
			System.out.println("Cannot set null genre...");
			return;
		}

		this.genre = genre;
	}

	/**
	 * Places actor's name into majorActors ArrayList
	 * @param actor String name of actors
	 */
	public void addMajorActor(String actor) {
		if (majorActors.contains(actor)) {
			System.out.println("Actor " + actor + " is already in list...will not add again");
			return;
		}

		majorActors.add(actor);
	}

	/**
	 * Removes name from majorActors arraylist
	 * @param actor String name of actor to remove
	 */
	public void removeMajorActor(String actor) {
		if (!actor.contains(actor)) {
			System.out.println("Actor " + actor + " not in list...not removed");
			return;
		}

		System.out.println("Removing actor " + actor);
		majorActors.remove(actor);
	}

	/**
	 * Returns arraylist of major actors
	 * @return String arraylist majorActors
	 */
	public ArrayList<String> getMajorActors() {
		return majorActors;
	}

	/**
	 * Setter for this movie's majorActors
	 * @param majorActors String arraylist of actor's names
	 */
	public void setMajorActors(ArrayList<String> majorActors) {
		this.majorActors = majorActors;
	}

	/**
	 * Returns string describing movie
	 * @return Name: Ratings: Producers: Genre: Famous Actors:
	 */
	@Override
	public String toString() {
		StringBuilder actorsString = new StringBuilder();
		for (String actor : majorActors)
			actorsString.append(actor).append(" ");

		return "Movie" + super.toString() + "\nGenre: " + this.genre + "\nFamous Actors: " + actorsString + "\n";
	}
}
