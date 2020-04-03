import java.util.ArrayList;
import java.util.Arrays;

public class Tester {

	private static final String[] words = {"bloope", "bleeg", "haha", "yoyo"};
	private static final int[] ratings = {2,4,3,2,4,3,4,2,5};

	public static void main(String[] args){
		ArrayList<Concert> movies = JsonParser.loadConcerts();

		ArrayList<String> strings = new ArrayList<>(Arrays.asList(words));
		ArrayList<Integer> other = new ArrayList<>();
		/*for(int i : ratings)
			other.add(i);

		movies.add(new Movie("Kung Fu panda", 5, 5, strings, other, strings, strings, "Action"));*/

		//movies.forEach(System.out::println);

		JsonParser.saveData(movies);

	}

}
