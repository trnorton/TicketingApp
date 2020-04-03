import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;

public class JsonParser {

	private static final String[] ATTRIBUTES = {"name", "offRating", "ageRating", "reviews", "custRatings", "producers"};
	private static final String[] MOVIE_ATTRS = {"majorActors", "genre"};
	private static final String PLAY_ATTR = "majorActors";
	private static final String CONCERT_ATTR = "performers";
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

		String name = (String)json.get(ATTRIBUTES[0]);
		show.setName(name);

		int offRating = Integer.parseInt(json.get(ATTRIBUTES[1]).toString());
		show.setOffRating(offRating);

		int ageRating = Integer.parseInt(json.get(ATTRIBUTES[2]).toString());
		show.setAgeRating(ageRating);

		ArrayList<String> reviews = new ArrayList<>();
		JSONArray reviewArray = (JSONArray)json.get(ATTRIBUTES[3]);
		for(Object review : reviewArray)
			reviews.add((String)(review));
		show.setReviews(reviews);

		ArrayList<Integer> custRatings = new ArrayList<>();
		JSONArray custRatingsArray = (JSONArray)(json.get(ATTRIBUTES[4]));

		for(Object rating : custRatingsArray)
			custRatings.add(Math.toIntExact((Long)rating));
		show.setCustRatings(custRatings);

		ArrayList<String> producers= new ArrayList<>();
		JSONArray producerArray = (JSONArray)json.get(ATTRIBUTES[5]);
		for(Object producer : producerArray)
			producers.add((String)(producer));
		show.setProducers(producers);

		return show;
	}

	private static Movie addMovieAttributes(JSONObject json, Movie movie){
		ArrayList<String> majorActors = new ArrayList<>();
		JSONArray majorActorsArray = (JSONArray) json.get(MOVIE_ATTRS[0]);
		for (Object majorActor : majorActorsArray)
			majorActors.add((String) (majorActor));

		String genre = json.get(MOVIE_ATTRS[1]).toString();
		movie.setMajorActors(majorActors);
		movie.setGenre(genre);

		return movie;
	}

	private static Play addPlayAttributes(JSONObject json, Play play){
		ArrayList<String> majorActors = new ArrayList<>();
		JSONArray majorActorsArray = (JSONArray)json.get(PLAY_ATTR);
		for(Object majorActor : majorActorsArray)
			majorActors.add((String)(majorActor));

		play.setMajorActors(majorActors);

		return play;
	}

	private static Concert addConcertAttributes(JSONObject json, Concert concert){
		ArrayList<String> performers = new ArrayList<>();
		JSONArray performersArray = (JSONArray)json.get(CONCERT_ATTR);
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
				JSONArray majorActorsArray = (JSONArray)json.get(MOVIE_ATTRS[0]);
				for(Object majorActor : majorActorsArray)
					majorActors.add((String)(majorActor));

				String genre = json.get(MOVIE_ATTRS[1]).toString();
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

				String name = (String)json.get(ATTRIBUTES[0]);
				int offRating = Integer.parseInt(json.get(ATTRIBUTES[1]).toString());
				int ageRating = Integer.parseInt(json.get(ATTRIBUTES[2]).toString());

				ArrayList<String> reviews = new ArrayList<>();
				JSONArray reviewArray = (JSONArray)json.get(ATTRIBUTES[3]);
				for(Object review : reviewArray)
					reviews.add((String)(review));

				ArrayList<Integer> custRatings = new ArrayList<>();
				JSONArray custRatingArray = (JSONArray)json.get(ATTRIBUTES[4]);
				for(Object rating : custRatingArray)
					custRatings.add((int)(rating));

				ArrayList<String> producers= new ArrayList<>();
				JSONArray producerArray = (JSONArray)json.get(ATTRIBUTES[5]);
				for(Object producer : producerArray)
					producers.add((String)(producer));

				ArrayList<String> performers = new ArrayList<>();
				JSONArray performersArray = (JSONArray)json.get(CONCERT_ATTR);
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

				String name = (String)json.get(ATTRIBUTES[0]);
				int offRating = Integer.parseInt(json.get(ATTRIBUTES[1]).toString());
				int ageRating = Integer.parseInt(json.get(ATTRIBUTES[2]).toString());

				ArrayList<String> reviews = new ArrayList<>();
				JSONArray reviewArray = (JSONArray)json.get(ATTRIBUTES[3]);
				for(Object review : reviewArray)
					reviews.add((String)(review));

				ArrayList<Integer> custRatings = new ArrayList<>();
				JSONArray custRatingArray = (JSONArray)json.get(ATTRIBUTES[4]);
				for(Object rating : custRatingArray)
					custRatings.add((int)(rating));

				ArrayList<String> producers= new ArrayList<>();
				JSONArray producerArray = (JSONArray)json.get(ATTRIBUTES[5]);
				for(Object producer : producerArray)
					producers.add((String)(producer));

				ArrayList<String> majorActors = new ArrayList<>();
				JSONArray majorActorsArray = (JSONArray)json.get(PLAY_ATTR);
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

		movieInfo.put(MOVIE_ATTRS[0], actorsArray.toString());
		movieInfo.put(MOVIE_ATTRS[1], genre);

		return movieInfo;
	}

	private static JSONObject getShowJSON(Play play){
		JSONObject movieInfo = getBasicJSON(play);

		ArrayList<String> majorActors = play.getMajorActors();
		JSONArray actorsArray = new JSONArray();
		actorsArray.addAll(majorActors);

		movieInfo.put(PLAY_ATTR, actorsArray.toString());

		return movieInfo;
	}

	private static JSONObject getShowJSON(Concert concert){
		JSONObject movieInfo = getBasicJSON(concert);

		ArrayList<String> majorActors = concert.getPerformers();
		JSONArray actorsArray = new JSONArray();
		actorsArray.addAll(majorActors);

		movieInfo.put(PLAY_ATTR, actorsArray.toString());

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

	public static void saveData(ArrayList<Show> showList){
		Show tester = showList.get(0);
		setType(tester);

		String filepath = getFilePath(tester);

		try {
			FileWriter writer = new FileWriter(new File(filepath + FILENAME_EXTENSION));
			JSONArray showJson = new JSONArray();

			for(Show show : showList)
				showJson.add(getShowJSON(show));

			writer.write(showJson.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static JSONObject getShowJSON(Show show){
		JSONObject showInfo = getBasicJSON(show);

		if(isMovie){
			Movie movie = (Movie)show;
			ArrayList<String> majorActors = movie.getMajorActors();
			String genre = movie.getGenre();

			JSONArray actorsArray = new JSONArray();
			actorsArray.addAll(majorActors);

			showInfo.put(MOVIE_ATTRS[0], actorsArray.toString());
			showInfo.put(MOVIE_ATTRS[1], genre);
		}else if(isPlay){
			Play play = (Play)show;
			ArrayList<String> majorActors = play.getMajorActors();
			JSONArray actorsArray = new JSONArray();
			actorsArray.addAll(majorActors);

			showInfo.put(PLAY_ATTR, actorsArray.toString());
		}else if(isConcert){
			Concert concert = (Concert)show;

			ArrayList<String> majorActors = concert.getPerformers();
			JSONArray actorsArray = new JSONArray();
			actorsArray.addAll(majorActors);

			showInfo.put(PLAY_ATTR, actorsArray.toString());
		}

		return showInfo;
	}

	private static JSONObject getBasicJSON(Show show){
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


		showInfo.put(ATTRIBUTES[0], name);
		showInfo.put(ATTRIBUTES[1], offRating);
		showInfo.put(ATTRIBUTES[2], ageRating);
		showInfo.put(ATTRIBUTES[3], reviewsArray.toString());
		showInfo.put(ATTRIBUTES[4], custRatingsArray.toString());
		showInfo.put(ATTRIBUTES[5], producersArray.toString());

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

	private static void setType(Show show){
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
