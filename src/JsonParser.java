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


	public static ArrayList<Movie> loadMovies() {
		return loadDataFromFile(new Movie());
	}

	public static ArrayList<Concert> loadConcerts() {
		return loadDataFromFile(new Concert());
	}

	public static ArrayList<Play> loadPlays() {
		return loadDataFromFile(new Play());
	}

	public static <T extends Show> void saveData(ArrayList<T> showList) {
		T typeChecker = showList.get(0);
		setType(typeChecker);
		String filepath = getFilePath(typeChecker);

		try {
			FileWriter writer = new FileWriter(new File(filepath + FILENAME_EXTENSION));
			JSONArray showJson = new JSONArray();

			for (T show : showList)
				showJson.add(getShowJSON(show));

			writer.write(fixJson(showJson.toJSONString()));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static <T extends Show> T loadBasicsFromFile(JSONObject json, Class<T> newShow) {
		T show = null;
		try {
			show = newShow.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

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

		return show;
	}

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

	private static Play addPlayAttributes(JSONObject json, Play play) {
		ArrayList<String> majorActors = new ArrayList<>();
		JSONArray majorActorsArray = (JSONArray) json.get(MAJOR_ACTORS);
		for (Object majorActor : majorActorsArray)
			majorActors.add((String) (majorActor));

		play.setMajorActors(majorActors);

		return play;
	}

	private static Concert addConcertAttributes(JSONObject json, Concert concert) {
		ArrayList<String> performers = new ArrayList<>();
		JSONArray performersArray = (JSONArray) json.get(PERFORMERS);
		for (Object performer : performersArray)
			performers.add((String)(performer));

		concert.setPerformers(performers);

		return concert;
	}

	private static <T extends Show> ArrayList<T> loadDataFromFile(T show) {
		setType(show);

		ArrayList<T> shows = new ArrayList<>();
		try {
			JSONParser parser = new JSONParser();
			JSONArray showArray = (JSONArray) new JSONParser().parse(new FileReader(getFilePath(show) + FILENAME_EXTENSION));

			for (Object jsonObject : showArray) {
				JSONObject json = (JSONObject) jsonObject;
				T newShow = (T) loadBasicsFromFile(json, show.getClass());

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

	private static String fixJson(String json) {
		return json.replace("\"[", "[").replace("]\"", "]").replace("\\\"", "\"");
	}

	private static <T extends Show> JSONObject getShowJSON(T show) {
		JSONObject showInfo = getBasicJSON(show);

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

		return showInfo;
	}

	private static <T extends Show> JSONObject getBasicJSON(T show) {
		JSONObject showInfo = new JSONObject();

		String name = show.getName();
		int offRating = show.getOffRating();
		int ageRating = show.getAgeRating();
		ArrayList<String> reviews = show.getReviews();
		ArrayList<Integer> custRatings = show.getCustRatings();
		ArrayList<String> producers = show.getProducers();

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

		return showInfo;
	}

	private static String getFilePath(Show show) {
		Show typeChecker = show;
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
