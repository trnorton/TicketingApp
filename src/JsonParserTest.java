import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class JsonParserTest {


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

	@Test
	public void saveMoviesTest(){
		

	}

	@Test
	public void savePlaysTest(){}

	@Test
	public void saveConcertsTest(){}

}
