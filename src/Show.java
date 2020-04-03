import java.util.ArrayList;

/**
 * @author Taylor Norton, Lukacs Ablonczy
 */
public abstract class Show {

	private String name;
	private int offRating;
	private int ageRating;

	private ArrayList<String> reviews;

	private ArrayList<Integer> custRatings;
	private ArrayList<String> producers;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOffRating() {
		return offRating;
	}

	public void setOffRating(int offRating) {
		this.offRating = offRating;
	}

	public int getAgeRating() {
		return ageRating;
	}

	public void setAgeRating(int ageRating) {
		this.ageRating = ageRating;
	}

	public void displayReviews() {
		for (String r : reviews) {
			System.out.println(r);
		}
	}

	public void addReview(String r) {
		reviews.add(r);
	}

	public void removeReview(String r) {
		reviews.remove(r);
	}

	public void displayCustRatings() {
		for (int r : custRatings) {
			System.out.println(r);
		}
	}

	public void addCustRating(Integer r) {
		custRatings.add(r);
	}

	public void removeCustRating(Integer r) {
		custRatings.remove(r);
	}

	private int getAverageCustRating() {
		int sum = 0;
		for (int r : custRatings) {
			sum += r;
		}
		return sum / custRatings.size();
	}

	public void addProducer(String producer){
		if(producer == null){
			System.out.println("Can't add null producer...");
			return;
		}

		System.out.println("Adding producer " + producer);
		producers.add(producer);
	}

	public void removeProducer(String producer){
		if(producer == null){
			System.out.println("Can't remove null producer...");
			return;
		}

		if(!producers.contains(producer)){
			System.out.println("Producer " + producer + " not in list...not removed");
			return;
		}

		System.out.println("Removing producer " + producer);
		producers.remove(producer);
	}

	public void setProducers(ArrayList<String> producers){
		this.producers = producers;
	}

	public ArrayList<String> getProducers() {
		return producers;
	}

	public ArrayList<String> getReviews(){
		return this.reviews;
	}

	public void setReviews(ArrayList<String> reviews) {
		this.reviews = reviews;
	}

	public ArrayList<Integer> getCustRatings() {
		return custRatings;
	}

	public void setCustRatings(ArrayList<Integer> custRatings) {
		this.custRatings = custRatings;
	}

	public String toString() {
		StringBuilder producersString = new StringBuilder();
		for(String producer : producers)
			producersString.append(producer).append(" ");


		return "Show Name: " + this.getName() + "\nIMDB Rating: " + offRating + "\nAge Rating: " + getAgeRating() + "\nOverall Viewer Rating: " + getAverageCustRating() + "\nProducers: " + producersString;
	}
}
