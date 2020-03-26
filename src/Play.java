import java.util.ArrayList;

public class Play extends Event{

	private ArrayList<String> majorActors;
	private ArrayList<String> producers;

	public Play(){
		this.setMajorActors(new ArrayList<>());
		this.setProducers(new ArrayList<>());
	}

	public Play(String name, int date, int time, int ageRating, ArrayList<String> majorActors, ArrayList<String> producers){
		
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

	public ArrayList<String> getProducers() {
		return producers;
	}
	public void setProducers(ArrayList<String> producers) {
		if(producers == null){
			System.out.println(this + "can't set producers bc it is null");
			return;
		}

		this.producers = producers;
	}



}
