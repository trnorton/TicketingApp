import java.util.ArrayList;

public class ConcessionsSystem {

	private ArrayList<Concession> concessions;

	public ConcessionsSystem() {
		concessions = new ArrayList<Concession>();
		
		loadSampleData();
	}

	public void addConcessions(Concession c) {
		concessions.add(c);
	}

	public void removeConcessions(Concession c) {
		concessions.remove(c);
	}

	public void showConcessions() {
		for (Concession c : concessions) {
			System.out.println(c);
		}
	}
	
	public ArrayList<Concession> getConcessions(){
		return concessions;
	}
	
	private void loadSampleData() {
		concessions.add(new Concession("Popcorn", 4.0, 50));
		concessions.add(new Concession("Soda", 3.0, 50));
		concessions.add(new Concession("Twizzlers", 1.5, 50));
	}
}
