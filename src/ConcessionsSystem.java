import java.util.ArrayList;

/**
 * ConcessionsSystem.java - A class to represent a system to manage Concessions.
 * 
 * @author Taylor Norton
 *
 */
public class ConcessionsSystem {

	private ArrayList<Concession> concessions;

	/**
	 * Default constructor.
	 */
	public ConcessionsSystem() {
		concessions = new ArrayList<Concession>();

		loadSampleData();
	}

	/**
	 * Adds a Concession to the list of Concessions.
	 * 
	 * @param c - A variable of type Concession.
	 */
	public void addConcessions(Concession concession) {
		concessions.add(concession);
	}

	/**
	 * Removes a Concession from the list of Concessions.
	 * 
	 * @param c - A variable of type Concession.
	 */
	public void removeConcessions(Concession concession) {
		concessions.remove(concession);
	}

	/**
	 * Displays all Concessions in the list.
	 */
	public void showConcessions() {
		for (Concession concession : concessions) {
			System.out.println(concession);
		}
	}

	/**
	 * Returns the list of Concessions.
	 * 
	 * @return ArrayList<Concession> - The list of Concessions.
	 */
	public ArrayList<Concession> getConcessions() {
		return concessions;
	}

	private void loadSampleData() {
		concessions.add(new Concession("Popcorn", 4.0, 50));
		concessions.add(new Concession("Soda", 3.0, 50));
		concessions.add(new Concession("Twizzlers", 1.5, 50));
	}
}
