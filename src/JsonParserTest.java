import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class JsonParserTest {


	@Test
	public void saveDataTest(){
		final String NAME = "Harry Potter";
		final int AGERATING = 0;
		final int OFFRATING = 0;
		final String GENRE = "Action";
		final ArrayList<String> ACTORS = new ArrayList<>(Arrays.asList("Daniel Radcliffe"));
		final ArrayList<String> PRODUCERS = new ArrayList<>(Arrays.asList("Daniel Radcliffe"));

		final Movie EXPECTED = new Movie(NAME, OFFRATING, AGERATING, GENRE, PRODUCERS, ACTORS);

		ArrayList<Movie> movies = new ArrayList<>(Arrays.asList(EXPECTED));
		JsonParser.saveData(movies);

		Movie found = JsonParser.loadMovies().get(0);

		Assert.assertEquals(EXPECTED.toString(), found.toString());


	}

	@Test
	public void loadMoviesSuccessTest(){
		ArrayList<Movie> movies = JsonParser.loadMovies();
		Assert.assertNotNull(movies);
	}

	@Test
	public void loadPlaysSuccessTest(){
		ArrayList<Play> plays = JsonParser.loadPlays();
		Assert.assertNotNull(plays);
	}

	@Test
	public void loadConcertsSuccessTest(){
		ArrayList<Concert> concerts = JsonParser.loadConcerts();
		Assert.assertNotNull(concerts);
	}

	@Test
	public void loadMoviesEmptyTest(){
		ArrayList<Movie> movies = JsonParser.loadMovies();
		Assert.assertFalse(movies.isEmpty());
	}

	@Test
	public void loadPlaysEmptyTest(){
		ArrayList<Play> plays = JsonParser.loadPlays();
		Assert.assertFalse(plays.isEmpty());
	}

	@Test
	public void loadConcertsEmptyTest(){
		ArrayList<Concert> concerts = JsonParser.loadConcerts();
		Assert.assertFalse(concerts.isEmpty());
	}


}
