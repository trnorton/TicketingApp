import java.util.ArrayList;

public class Play extends Show{

	private ArrayList<String> majorActors;

	public Play(){

	}

	public Play(String name, int ageRating, ArrayList<String> majorActors, ArrayList<String> producers){
		super.setName(name);
		super.setAgeRating(ageRating);
		super.setProducers(producers);
		this.setMajorActors(majorActors);
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
