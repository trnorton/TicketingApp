import java.util.ArrayList;
import java.util.LinkedList;

public class Tester {

	public static void main(String[] args){
		ArrayList<Movie> movies = JsonParser.loadDataFromFile(new Movie());

		movies.forEach(System.out::println);

	}

}
