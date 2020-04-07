import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Lukacs Ablonczy
 * This class uses loads and saves JSON files based on the System's movies, concerts, and plays held in ArrayList<String> objects
 */
public class JsonParser {

	//Keys for Json Values
	private static final String NAME = "name";
	private static final String OFF_RATING = "offRating";
	private static final String AGE_RATING = "ageRating";
	private static final String REVIEWS = "reviews";
	private static final String CUST_RATINGS = "custRatings";
	private static final String PRODUCERS = "producers";
	private static final String MAJOR_ACTORS = "majorActors";
	private static final String GENRE = "genre";
	private static final String PERFORMERS = "performers";

	//File names and Show type markers
	private static final String TYPE_MOVIE = "Movies";
	private static final String TYPE_PLAY = "Plays";
	private static final String TYPE_CONCERT = "Concerts";
	private static final String FILENAME_EXTENSION = ".json";

	//Keeps track of show type, to avoid repeated instanceof checks
	private static boolean isMovie = false;
	private static boolean isConcert = false;
	private static boolean isPlay = false;

	/**
	 * Loads movies from Movies.json
	 * @return ArrayList<Movie> of all movies found
	 */
	public static ArrayList<Movie> loadMovies() {
		return loadDataFromFile(new Movie());
	}

	/**
	 * Loads concerts from Concerts.json
	 * @return ArrayList<Concert> of all concerts found
	 */
	public static ArrayList<Concert> loadConcerts() {
		return loadDataFromFile(new Concert());
	}

	/**
	 * Loads plays from Plays.json
	 * @return ArrayList<Play> of all plays found
	 */
	public static ArrayList<Play> loadPlays() {
		return loadDataFromFile(new Play());
	}

	/**
	 * Writes to JSON any ArrayList of any show type
	 * @param showList ArrayList of a Show child type
	 * @param <T> The type of Show child contained in the arraylist
	 */
	public static <T extends Show> void saveData(ArrayList<T> showList) {
		T typeChecker = showList.get(0);
		setType(typeChecker);
		String filepath = getFilePath();

		try {
			File jsonFile = new File("src/" + filepath + FILENAME_EXTENSION);

			FileWriter writer = new FileWriter(jsonFile);
			JSONArray showJson = new JSONArray();

			for (T show : showList) {
				if(nameBlankOrNull(show.getName())){
					System.out.println("Will not write show: Name Blank or Null");
					continue;
				}

				showJson.add(getShowJSON(show));
			}

			writer.write(fixJson(showJson.toJSONString()));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks whether a Show child types name is null or blank, in order to skip it when reading or writing
	 * @param name String name of Show child type
	 * @return Whether name is null or blank
	 */
	private static boolean nameBlankOrNull(String name){
		return name == null || name.equals("");
	}

	/**
	 * Loads attributes from JSON that are shared among all Show types for a single object stored in JSON
	 * @param json JSONObject containing one of the elements of the main JSONArray parsed from the JSON file
	 * @param newShow Allows the creation of new instances of a generic datatype. Pass to this a show.getClass(), where show is the name of any variable of Show type created outside this method
	 * @param <T> The type of Show child being currently loaded
	 * @return Returns A Show child type, with some attributes set, and others null.
	 */
	private static <T extends Show> T loadBasicsFromFile(JSONObject json, Class<T> newShow) {
				T show = null;
		try {
			show = newShow.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			String name = (String) json.get(NAME);
			show.setName(name);

			int offRating = Integer.parseInt(json.get(OFF_RATING).toString());
			show.setOffRating(offRating);

			int ageRating = Integer.parseInt(json.get(AGE_RATING).toString());
			show.setAgeRating(ageRating);

			ArrayList<String> reviews = new ArrayList<>();
			JSONArray reviewArray = (JSONArray) json.get(REVIEWS);
			for (Object review : reviewArray)
				reviews.add((String) (review));
			show.setReviews(reviews);

			ArrayList<Integer> custRatings = new ArrayList<>();
			JSONArray custRatingsArray = (JSONArray) (json.get(CUST_RATINGS));
			for (Object rating : custRatingsArray)
				custRatings.add(Math.toIntExact((Long) rating));
			show.setCustRatings(custRatings);

			ArrayList<String> producers = new ArrayList<>();
			JSONArray producerArray = (JSONArray) json.get(PRODUCERS);
			for (Object producer : producerArray)
				producers.add((String) (producer));
			show.setProducers(producers);

		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Cannot load show data");
			return null;
		}

		return show;
	}

	/**
	 * Called by loadDataFromFile to add data to the given show
	 * @param json JSONObject containing one of the elements of the main JSONArray parsed from the JSON file
	 * @param movie Movie that will be receiving Genre and MajorActors elements from JSON
	 * @return Movie instance fully constructed
	 */
	private static Movie addMovieAttributes(JSONObject json, Movie movie) {
		ArrayList<String> majorActors = new ArrayList<>();
		JSONArray majorActorsArray = (JSONArray) json.get(MAJOR_ACTORS);
		for (Object majorActor : majorActorsArray)
			majorActors.add((String) (majorActor));

		String genre = json.get(GENRE).toString();
		movie.setMajorActors(majorActors);
		movie.setGenre(genre);

		return movie;
	}

	/**
	 * Called by loadDataFromFile to add data to the given show
	 * @param json JSONObject containing one of the elements of the main JSONArray parsed from the JSON file
	 * @param play Play that will be receiving a MajorActors element from JSON
	 * @return Play instance fully constructed
	 */
	private static Play addPlayAttributes(JSONObject json, Play play) {
		ArrayList<String> majorActors = new ArrayList<>();
		JSONArray majorActorsArray = (JSONArray) json.get(MAJOR_ACTORS);
		for (Object majorActor : majorActorsArray)
			majorActors.add((String) (majorActor));

		play.setMajorActors(majorActors);

		return play;
	}

	/**
	 * Called by loadDataFromFile to add data to the given show
	 * @param json JSONObject containing one of the elements of the main JSONArray parsed from the JSON file
	 * @param concert Play that will be receiving a Performers element from JSON
	 * @return Concert instance fully constructed
	 */
	private static Concert addConcertAttributes(JSONObject json, Concert concert) {
		ArrayList<String> performers = new ArrayList<>();
		JSONArray performersArray = (JSONArray) json.get(PERFORMERS);
		for (Object performer : performersArray)
			performers.add((String)(performer));

		concert.setPerformers(performers);

		return concert;
	}

	/**
	 * Loads JSON Data from a .json file. Object type and the file to draw from are determined automatically by the method
	 * @param show Show child type instance, always use either "new Movie()", "new Play()", or "new Concert()"
	 * @param <T> The name of any type that is a child of the Show type
	 * @return ArrayList of the Show child type given in the parameter loaded from .json
	 */
	private static <T extends Show> ArrayList<T> loadDataFromFile(T show) {
		setType(show);

		ArrayList<T> shows = new ArrayList<>();
		try {

			String filepath = getFilePath();
			File jsonFile = new File("src/" + filepath + FILENAME_EXTENSION);
			if(!jsonFile.exists() || jsonFile.length() == 0){
				System.out.println("No " + filepath + " to load...");
				return null;
			}


			JSONArray showArray = (JSONArray) new JSONParser().parse(new FileReader("src/" + filepath + FILENAME_EXTENSION));

			for (Object jsonObject : showArray) {
				JSONObject json = (JSONObject) jsonObject;
				if(json == null){
					continue;
				}

				T newShow = (T) loadBasicsFromFile(json, show.getClass());

				if(nameBlankOrNull(newShow.getName()))
					continue;

				if (isMovie) {
					Movie movie = addMovieAttributes(json, (Movie) newShow);

					shows.add((T) movie);
				} else if (isConcert) {
					Concert concert = addConcertAttributes(json, (Concert) newShow);
					shows.add((T) concert);
				} else if (isPlay) {
					Play play = addPlayAttributes(json, (Play) newShow);
					shows.add((T) play);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return shows;
	}

	/**
	 * Json Simple sucks, so this method cleans up the broken JSON by removing unnecessary quotation marks and escape characters
	 * @param json The string version of json. Most likely a JSONArray or JSONObject .toJSONString()
	 * @return Useable JSON to print to file
	 */
	private static String fixJson(String json) {
		return json.replace("\"[", "[").replace("]\"", "]").replace("\\\"", "\"");
	}

	/**
	 * Used to take a generic Show child type, adds the type's unique attributes to the JSONObject being build by saveData
	 * @param show Show child type instance
	 * @param <T> The name of any type that is a child of the Show type
	 * @return JSONObject that contains all of the attributes of the given instace of a Show child type
	 */
	private static <T extends Show> JSONObject getShowJSON(T show) {
		JSONObject showInfo = getBasicJSON(show);

		try {
			if (isMovie) {
				Movie movie = (Movie) show;
				ArrayList<String> majorActors = movie.getMajorActors();
				String genre = movie.getGenre();

				JSONArray actorsArray = new JSONArray();
				actorsArray.addAll(majorActors);

				showInfo.put(MAJOR_ACTORS, actorsArray.toJSONString());
				showInfo.put(GENRE, genre);
			} else if (isPlay) {
				Play play = (Play) show;
				ArrayList<String> majorActors = play.getMajorActors();
				JSONArray actorsArray = new JSONArray();
				actorsArray.addAll(majorActors);

				showInfo.put(MAJOR_ACTORS, actorsArray.toJSONString());
			} else if (isConcert) {
				Concert concert = (Concert) show;

				ArrayList<String> performers = concert.getPerformers();
				JSONArray performersArray = new JSONArray();
				performersArray.addAll(performers);

				showInfo.put(PERFORMERS, performersArray.toJSONString());
			}
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("Cannot save show json...");
		}

		return showInfo;
	}

	/**
	 * Retrieves JSON from .json that is common to all Show types to start off a JSONObject
	 * @param show Show child type instance
	 * @param <T> The name of any type that is a child of the Show type
	 * @return JSONObject that contains all of the attributes of the given instace of a Show child type
	 */
	private static <T extends Show> JSONObject getBasicJSON(T show) {
		JSONObject showInfo = new JSONObject();

		String name = show.getName();
		int offRating = show.getOffRating();
		int ageRating = show.getAgeRating();
		ArrayList<String> reviews = show.getReviews();
		ArrayList<Integer> custRatings = show.getCustRatings();
		ArrayList<String> producers = show.getProducers();

		if(reviews == null){
			reviews = new ArrayList<>();
		}

		if(custRatings == null){
			custRatings = new ArrayList<>();
		}

		if(producers == null){
			producers = new ArrayList<>();
		}

		try {
			JSONArray reviewsArray = new JSONArray();
			reviewsArray.addAll(reviews);

			JSONArray custRatingsArray = new JSONArray();
			custRatingsArray.addAll(custRatings);

			JSONArray producersArray = new JSONArray();
			producersArray.addAll(producers);


			showInfo.put(NAME, name);
			showInfo.put(OFF_RATING, offRating);
			showInfo.put(AGE_RATING, ageRating);
			showInfo.put(REVIEWS, reviewsArray.toJSONString());
			showInfo.put(CUST_RATINGS, custRatingsArray.toJSONString());
			showInfo.put(PRODUCERS, producersArray.toJSONString());
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("Cannot save Show json...");
			return null;
		}
		return showInfo;
	}

	/**
	 * Assits in determining the filepath by returning the string to use when building the filepath
	 * @return String constant to use in filepath
	 */
	private static String getFilePath() {
		if (isMovie)
			return TYPE_MOVIE;
		else if (isPlay)
			return TYPE_PLAY;
		else if (isConcert)
			return TYPE_CONCERT;
		else {
			System.out.println("Not a show type");
			return null;
		}
	}

	/**
	 * Sets markers for the Show child type given by parameter
	 * @param show Any instance of a show child type
	 * @param <T> The name of any type that is a child of show
	 */
	private static <T extends Show> void setType(T show) {
		if (show instanceof Movie) {
			isMovie = true;
			isConcert = false;
			isPlay = false;
		} else if (show instanceof Play) {
			isMovie = false;
			isConcert = false;
			isPlay = true;
		} else if (show instanceof Concert) {
			isMovie = false;
			isConcert = true;
			isPlay = false;
		}
	}

}
