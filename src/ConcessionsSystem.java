import java.util.ArrayList;

public class ConcessionsSystem {

	private ArrayList<Concession> concessions;

	public ConcessionsSystem() {
		concessions = new ArrayList<Concession>();
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
}
