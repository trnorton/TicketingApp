import java.util.ArrayList;

public class Concert extends Show{

	private ArrayList<String> performers;

	public Concert(){

	}

	public Concert(String name, int ageRating, ArrayList<String> performers, ArrayList<String> producers){
		this.setName(name);
		this.setAgeRating(ageRating);
		this.setProducers(producers);
		this.setPerformers(performers);
	}

	public void addPerformer(String performer){
		if(performer == null){
			System.out.println("Cannot add null actor...");
			return;
		}

		if(performers.contains(performer)){
			System.out.println("Performer " + performer + " is already in list...will not add again");
			return;
		}

		performers.add(performer);
	}

	public void removePerformer(String performer){
		if(performer == null){
			System.out.println("Can't remove null performer...");
			return;
		}

		if(!performer.contains(performer)){
			System.out.println("performer " + performer + " not in list...not removed");
			return;
		}

		System.out.println("Removing performer " + performer);
		performers.remove(performer);
	}


	public ArrayList<String> getPerformers() {
		return performers;
	}

	public void setPerformers(ArrayList<String> performers) {
		if(performers == null){
			System.out.println(this + " can't set performers bc it is null");
			return;
		}

		this.performers = performers;
	}


	@Override
	public String toString() {
		StringBuilder performersString = new StringBuilder();
		for(String actor : performers)
			performersString.append(actor).append(", ");

		return super.toString() + " Performers: " + performersString;
	}
}
