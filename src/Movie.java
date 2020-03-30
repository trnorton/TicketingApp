import java.util.ArrayList;

public class Movie extends Show{

	private ArrayList<String> majorActors;

	private String genre;

	public Movie(){

	}

	public Movie(String name, int ageRating, String genre, ArrayList<String> majorActors, ArrayList<String> producers){
		this.setName(name);
		this.setAgeRating(ageRating);
		this.setGenre(genre);
		this.setProducers(producers);
		this.setMajorActors(majorActors);
	}


	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		if(genre == null){
			System.out.println("Cannot set null genre...");
			return;
		}

		this.genre = genre;
	}


	public void addMajorActor(String actor){
		if(actor == null){
			System.out.println("Cannot add null actor...");
			return;
		}

		if(majorActors.contains(actor)){
			System.out.println("Actor " + actor + " is already in list...will not add again");
			return;
		}

		majorActors.add(actor);
	}

	public void removeMajorActor(String actor){
		if(actor == null){
			System.out.println("Can't remove null actor...");
			return;
		}

		if(!actor.contains(actor)){
			System.out.println("Actor " + actor + " not in list...not removed");
			return;
		}

		System.out.println("Removing actor " + actor);
		majorActors.remove(actor);
	}


	public ArrayList<String> getMajorActors() {
		return majorActors;
	}

	public void setMajorActors(ArrayList<String> majorActors) {
		if(majorActors == null){
			System.out.println(this + " can't set majorActors bc it is null");
			return;
		}

		this.majorActors = majorActors;
	}


	@Override
	public String toString() {
		StringBuilder actorsString = new StringBuilder();
		for(String actor : majorActors)
			actorsString.append(actor).append(", ");

		return super.toString() + " Famous Actors: " + actorsString;
	}
}
