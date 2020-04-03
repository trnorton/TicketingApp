import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class JsonParser {

	private static final String[] ATTRIBUTES = {"name", "offRating", "ageRating", "reviews", "customerRatings", "producers"};
	private static final String[] MOVIE_ATTRS = {"majorActors", "genre"};
	private static final String PLAY_ATTR = "majorActors";
	private static final String CONCERT_ATTR = "performers";
	private static final String TYPE_MOVIE = "Movies";
	private static final String TYPE_PLAY = "Plays";
	private static final String TYPE_CONCERT = "Concerts";
	private static final String FILENAME_EXTENSION = ".json";

	private static JSONObject getShowJSON(Movie movie){
		JSONObject movieInfo = getBasicJSON(movie);

		ArrayList<String> majorActors = movie.getMajorActors();
		String genre = movie.getGenre();

		JSONArray actorsArray = new JSONArray(majorActors);

		movieInfo.put(MOVIE_ATTRS[0], actorsArray.toString());
		movieInfo.put(MOVIE_ATTRS[1], genre);

		return movieInfo;
	}

	private static JSONObject getShowJSON(Play play){
		JSONObject movieInfo = getBasicJSON(play);

		ArrayList<String> majorActors = play.getMajorActors();
		JSONArray actorsArray = new JSONArray(majorActors);

		movieInfo.put(PLAY_ATTR, actorsArray.toString());

		return movieInfo;
	}

	private static JSONObject getShowJSON(Concert concert){
		JSONObject movieInfo = getBasicJSON(concert);

		ArrayList<String> majorActors = concert.getPerformers();
		JSONArray actorsArray = new JSONArray(majorActors);

		movieInfo.put(PLAY_ATTR, actorsArray.toString());

		return movieInfo;
	}

	private static JSONObject getBasicJSON(Show show){
		JSONObject showInfo = new JSONObject();

		String name = show.getName();
		int offRating = show.getOffRating();
		int ageRating = show.getAgeRating();
		ArrayList<String> reviews = show.getReviews();
		ArrayList<Integer> custRatings = show.getCustRatings();
		ArrayList<String> producers = show.getProducers();

		JSONArray reviewsArray = new JSONArray(reviews);
		JSONArray custRatingsArray = new JSONArray(custRatings);
		JSONArray producersArray = new JSONArray(producers);


		showInfo.put(ATTRIBUTES[0], name);
		showInfo.put(ATTRIBUTES[1], offRating);
		showInfo.put(ATTRIBUTES[2], ageRating);
		showInfo.put(ATTRIBUTES[3], reviewsArray.toString());
		showInfo.put(ATTRIBUTES[4], custRatingsArray.toString());
		showInfo.put(ATTRIBUTES[5], producersArray.toString());

		return showInfo;
	}

	public static void saveMovies(ArrayList<Movie> movies){
		try {
			FileWriter writer = new FileWriter(new File(TYPE_MOVIE + FILENAME_EXTENSION));
			JSONArray moviesAsJSON = new JSONArray();

			for(Movie movie : movies)
				moviesAsJSON.put(getShowJSON(movie));

			writer.write(moviesAsJSON.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void savePlays(ArrayList<Play> plays){
		try {
			FileWriter writer = new FileWriter(new File(TYPE_MOVIE + FILENAME_EXTENSION));
			JSONArray playsAsJSON = new JSONArray();

			for(Play play : plays)
				playsAsJSON.put(getShowJSON(play));

			writer.write(playsAsJSON.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void saveConcerts(ArrayList<Concert> concerts){
		try {
			FileWriter writer = new FileWriter(new File(TYPE_MOVIE + FILENAME_EXTENSION));
			JSONArray concertsAsJSON = new JSONArray();

			for(Concert concert: concerts)
				concertsAsJSON.put(getShowJSON(concert));

			writer.write(concertsAsJSON.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
