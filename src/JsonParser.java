import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;

public class JsonParser {

//	private static final String[] ATTRIBUTES = {"name", "offRating", "ageRating", "reviews", "custRatings", "producers"};
//	private static final String[] MOVIE_ATTRS = {"majorActors", "genre"};

	private static final String NAME = "name";
	private static final String OFFRATING = "offRating";
	private static final String AGERATING = "ageRating";
	private static final String REVIEWS = "reviews";
	private static final String CUSTRATING = "custRatings";
	private static final String PRODUCERS = "producers";
	private static final String MAJORACTORS = "majorActors";
	private static final String GENRE = "genre";
	private static final String PERFORMERS = "performers";

	private static final String TYPE_MOVIE = "Movies";
	private static final String TYPE_PLAY = "Plays";
	private static final String TYPE_CONCERT = "Concerts";
	private static final String FILENAME_EXTENSION = ".json";
	private static boolean isMovie = false;
	private static boolean isConcert = false;
	private static boolean isPlay = false;


	private static <T extends Show> T loadBasicsFromFile(JSONObject json, Class<T> newShow){
		T show = null;
		try {
			show = newShow.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String name = (String)json.get(NAME);
		show.setName(name);

		int offRating = Integer.parseInt(json.get(OFFRATING).toString());
		show.setOffRating(offRating);

		int ageRating = Integer.parseInt(json.get(AGERATING).toString());
		show.setAgeRating(ageRating);

		ArrayList<String> reviews = new ArrayList<>();
		JSONArray reviewArray = (JSONArray)json.get(REVIEWS);
		for(Object review : reviewArray)
			reviews.add((String)(review));
		show.setReviews(reviews);

		ArrayList<Integer> custRatings = new ArrayList<>();
		JSONArray custRatingsArray = (JSONArray)(json.get(CUSTRATING));

		for(Object rating : custRatingsArray)
			custRatings.add(Math.toIntExact((Long)rating));
		show.setCustRatings(custRatings);

		ArrayList<String> producers= new ArrayList<>();
		JSONArray producerArray = (JSONArray)json.get(PRODUCERS);
		for(Object producer : producerArray)
			producers.add((String)(producer));
		show.setProducers(producers);

		return show;
	}

	private static Movie addMovieAttributes(JSONObject json, Movie movie){
		ArrayList<String> majorActors = new ArrayList<>();
		JSONArray majorActorsArray = (JSONArray) json.get(MAJORACTORS);
		for (Object majorActor : majorActorsArray)
			majorActors.add((String) (majorActor));

		String genre = json.get(GENRE).toString();
		movie.setMajorActors(majorActors);
		movie.setGenre(genre);

		return movie;
	}

	private static Play addPlayAttributes(JSONObject json, Play play){
		ArrayList<String> majorActors = new ArrayList<>();
		JSONArray majorActorsArray = (JSONArray)json.get(MAJORACTORS);
		for(Object majorActor : majorActorsArray)
			majorActors.add((String)(majorActor));

		play.setMajorActors(majorActors);

		return play;
	}

	private static Concert addConcertAttributes(JSONObject json, Concert concert){
		ArrayList<String> performers = new ArrayList<>();
		JSONArray performersArray = (JSONArray)json.get(PERFORMERS);
		for(Object performer : performersArray)
			performers.add((String)(performer));

		return concert;
	}

	public static <T extends Show> ArrayList<T> loadDataFromFile(T show){
		setType(show);

		ArrayList<T> shows = new ArrayList<>();
		try {
			JSONParser parser = new JSONParser();
			JSONArray showArray = (JSONArray)new JSONParser().parse(new FileReader(getFilePath(show) + FILENAME_EXTENSION));

			for (Object jsonObject : showArray) {
				JSONObject json = (JSONObject) jsonObject;
				T newShow = (T) loadBasicsFromFile(json, show.getClass());

				if(isMovie){
					Movie movie = addMovieAttributes(json, (Movie)newShow);
					shows.add((T)movie);
				} else if(isConcert){
					Concert concert = addConcertAttributes(json, (Concert)newShow);
					shows.add((T)concert);
				} else if(isPlay) {
					Play play = addPlayAttributes(json, (Play)newShow);
					shows.add((T)play);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return shows;
	}

/*	public static ArrayList<Movie> loadMoviesFromFile(){
		ArrayList<Movie> movies = new ArrayList<>();
		try {
			JSONParser parser = new JSONParser();
			JSONArray moviesArray = (JSONArray)new JSONParser().parse(new FileReader(TYPE_MOVIE + FILENAME_EXTENSION));

			for (Object jsonObject : moviesArray) {
				JSONObject json = (JSONObject) jsonObject;

				Movie movie = loadBasicsFromFile(json, new Movie());

				ArrayList<String> majorActors = new ArrayList<>();
				JSONArray majorActorsArray = (JSONArray)json.get(MAJORACTORS);
				for(Object majorActor : majorActorsArray)
					majorActors.add((String)(majorActor));

				String genre = json.get(GENRE).toString();
				movie.setMajorActors(majorActors);
				movie.setGenre(genre);

				movies.add(movie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return movies;
	}

	public static ArrayList<Concert> loadConcertsFromFile(){
		ArrayList<Concert> concerts = new ArrayList<Concert>();

		try {
			JSONParser parser = new JSONParser();
			JSONArray concertsArray = (JSONArray)new JSONParser().parse(new FileReader(TYPE_CONCERT + FILENAME_EXTENSION));

			for (Object jsonObject : concertsArray) {
				JSONObject json = (JSONObject) jsonObject;

				String name = (String)json.get(NAME);
				int offRating = Integer.parseInt(json.get(OFFRATING).toString());
				int ageRating = Integer.parseInt(json.get(AGERATING).toString());

				ArrayList<String> reviews = new ArrayList<>();
				JSONArray reviewArray = (JSONArray)json.get(REVIEWS);
				for(Object review : reviewArray)
					reviews.add((String)(review));

				ArrayList<Integer> custRatings = new ArrayList<>();
				JSONArray custRatingArray = (JSONArray)json.get(CUSTRATING);
				for(Object rating : custRatingArray)
					custRatings.add((int)(rating));

				ArrayList<String> producers= new ArrayList<>();
				JSONArray producerArray = (JSONArray)json.get(PRODUCERS);
				for(Object producer : producerArray)
					producers.add((String)(producer));

				ArrayList<String> performers = new ArrayList<>();
				JSONArray performersArray = (JSONArray)json.get(PERFORMERS);
				for(Object performer : performersArray)
					performers.add((String)(performer));

				concerts.add(new Concert(name, offRating, ageRating, reviews, custRatings, producers, performers));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return concerts;
	}

	public static ArrayList<Play> loadPlaysFromFile(){
		ArrayList<Play> plays = new ArrayList<>();

		try {
			JSONParser parser = new JSONParser();
			JSONArray playArray = (JSONArray)new JSONParser().parse(new FileReader(TYPE_PLAY + FILENAME_EXTENSION));

			for (Object jsonObject : playArray) {
				JSONObject json = (JSONObject) jsonObject;

				String name = (String)json.get(NAME);
				int offRating = Integer.parseInt(json.get(OFFRATING).toString());
				int ageRating = Integer.parseInt(json.get(AGERATING).toString());

				ArrayList<String> reviews = new ArrayList<>();
				JSONArray reviewArray = (JSONArray)json.get(REVIEWS);
				for(Object review : reviewArray)
					reviews.add((String)(review));

				ArrayList<Integer> custRatings = new ArrayList<>();
				JSONArray custRatingArray = (JSONArray)json.get(CUSTRATING);
				for(Object rating : custRatingArray)
					custRatings.add((int)(rating));

				ArrayList<String> producers= new ArrayList<>();
				JSONArray producerArray = (JSONArray)json.get(PRODUCERS);
				for(Object producer : producerArray)
					producers.add((String)(producer));

				ArrayList<String> majorActors = new ArrayList<>();
				JSONArray majorActorsArray = (JSONArray)json.get(MAJORACTORS);
				for(Object majorActor : majorActorsArray)
					majorActors.add((String)(majorActor));

				plays.add(new Play(name, offRating, ageRating, reviews, custRatings, producers, majorActors));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plays;
	}*/

	/*private static JSONObject getShowJSON(Movie movie){
		JSONObject movieInfo = getBasicJSON(movie);

		ArrayList<String> majorActors = movie.getMajorActors();
		String genre = movie.getGenre();

		JSONArray actorsArray = new JSONArray();
		actorsArray.addAll(majorActors);

		movieInfo.put(MAJORACTORS, actorsArray.toString());
		movieInfo.put(GENRE, genre);

		return movieInfo;
	}

	private static JSONObject getShowJSON(Play play){
		JSONObject movieInfo = getBasicJSON(play);

		ArrayList<String> majorActors = play.getMajorActors();
		JSONArray actorsArray = new JSONArray();
		actorsArray.addAll(majorActors);

		movieInfo.put(MAJORACTORS, actorsArray.toString());

		return movieInfo;
	}

	private static JSONObject getShowJSON(Concert concert){
		JSONObject movieInfo = getBasicJSON(concert);

		ArrayList<String> majorActors = concert.getPerformers();
		JSONArray actorsArray = new JSONArray();
		actorsArray.addAll(majorActors);

		movieInfo.put(MAJORACTORS, actorsArray.toString());

		return movieInfo;
	}

	*/

/*	public static void saveMovies(ArrayList<Movie> movies){
		try {
			FileWriter writer = new FileWriter(new File(TYPE_MOVIE + FILENAME_EXTENSION));
			JSONArray moviesAsJSON = new JSONArray();

			for(Movie movie : movies)
				moviesAsJSON.add(getShowJSON(movie));

			writer.write(moviesAsJSON.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void savePlays(ArrayList<Play> plays){
		try {
			FileWriter writer = new FileWriter(new File(TYPE_PLAY + FILENAME_EXTENSION));
			JSONArray playsAsJSON = new JSONArray();

			for(Play play : plays)
				playsAsJSON.add(getShowJSON(play));

			writer.write(playsAsJSON.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void saveConcerts(ArrayList<Concert> concerts){
		try {
			FileWriter writer = new FileWriter(new File(TYPE_CONCERT + FILENAME_EXTENSION));
			JSONArray concertsAsJSON = new JSONArray();

			for(Concert concert: concerts)
				concertsAsJSON.add(getShowJSON(concert));

			writer.write(concertsAsJSON.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}*/

	public static <T extends Show> void saveData(ArrayList<T> showList){
		T typeChecker = showList.get(0);
		setType(typeChecker);
		String filepath = getFilePath(typeChecker);

		try {
			FileWriter writer = new FileWriter(new File(filepath + FILENAME_EXTENSION));
			JSONArray showJson = new JSONArray();

			for(T show : showList)
				showJson.add(getShowJSON(show));

			writer.write(fixJson(showJson.toJSONString()));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String fixJson(String json){
		return json.replace("\"[", "[").replace("]\"", "]").replace("\\\"", "\"");
	}

	private static <T extends Show> JSONObject getShowJSON(T show){
		JSONObject showInfo = getBasicJSON(show);

		if(isMovie){
			Movie movie = (Movie)show;
			ArrayList<String> majorActors = movie.getMajorActors();
			String genre = movie.getGenre();

			JSONArray actorsArray = new JSONArray();
			actorsArray.addAll(majorActors);

			showInfo.put(MAJORACTORS, actorsArray.toJSONString());
			showInfo.put(GENRE, genre);
		}else if(isPlay){
			Play play = (Play)show;
			ArrayList<String> majorActors = play.getMajorActors();
			JSONArray actorsArray = new JSONArray();
			actorsArray.addAll(majorActors);

			showInfo.put(MAJORACTORS, actorsArray.toJSONString());
		}else if(isConcert){
			Concert concert = (Concert)show;

			ArrayList<String> majorActors = concert.getPerformers();
			JSONArray actorsArray = new JSONArray();
			actorsArray.addAll(majorActors);

			showInfo.put(MAJORACTORS, actorsArray.toJSONString());
		}

		return showInfo;
	}

	private static <T extends Show> JSONObject getBasicJSON(T show){
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
		showInfo.put(OFFRATING, offRating);
		showInfo.put(AGERATING, ageRating);
		showInfo.put(REVIEWS, reviewsArray.toJSONString());
		showInfo.put(CUSTRATING, custRatingsArray.toJSONString());
		showInfo.put(PRODUCERS, producersArray.toJSONString());

		return showInfo;
	}

	private static String getFilePath(Show show){
		Show typeChecker = show;
		if(isMovie)
			return  TYPE_MOVIE;
		else if(isPlay)
			return TYPE_PLAY;
		else if(isConcert)
			return TYPE_CONCERT;
		else{
			System.out.println("Not a show type");
			return null;
		}
	}

	private static <T extends Show> void setType(T show){
		if(show instanceof Movie) {
			isMovie = true;
			isConcert = false;
			isPlay = false;
		}
		else if(show instanceof Play) {
			isMovie = false;
			isConcert = false;
			isPlay = true;
		}
		else if(show instanceof  Concert) {
			isMovie = false;
			isConcert = true;
			isPlay = false;
		}
	}

}
