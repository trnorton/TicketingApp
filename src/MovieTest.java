import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.MediaSize;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MovieTest {

	@Test
	public void correctValuesInputTestName(){
		final String NAME = "Harry Potter";
		final int AGERATING = 0;
		final int OFFRATING = 0;
		final String GENRE = "Action";
		final ArrayList<String> ACTORS = new ArrayList<>(Arrays.asList("Daniel Radcliffe"));
		final ArrayList<String> PRODUCERS = new ArrayList<>(Arrays.asList("Daniel Radcliffe"));

		final Movie EXPECTED = new Movie(NAME, OFFRATING, AGERATING, null, PRODUCERS, ACTORS);

		Movie movie = new Movie(NAME, OFFRATING, AGERATING, null, PRODUCERS, ACTORS);

		Assert.assertEquals(EXPECTED.getName(), movie.getName());

	}

	@Test
	public void getMajorActorsTest(){
		Movie movie = new Movie();
		final String MAJORACTOR = "Bob Dunes";
		final ArrayList<String> EXPECTED = new ArrayList<>(Arrays.asList(MAJORACTOR));
		movie.setMajorActors(EXPECTED);

		Assert.assertEquals(EXPECTED, movie.getMajorActors());
	}
	
	@Test
	public void setGenreTestValid(){
		Movie movie = new Movie();
		final String EXPECTED = "Action";

		movie.setGenre(EXPECTED);
		Assert.assertEquals(EXPECTED,movie.getGenre());
	}

	@Test
	public void setGenreTestInvalid(){
		Movie movie = new Movie();
		final String EXPECTED = "Cannot set null genre";

		movie.setGenre(EXPECTED);
		Assert.assertEquals(EXPECTED, movie.getGenre());
	}

	@Test
	public void setMajorActorsTest(){
		Movie movie = new Movie();
		final String MAJORACTOR = "Bob Dunes";
		final ArrayList<String> EXPECTED = new ArrayList<>(Arrays.asList(MAJORACTOR));

		movie.setMajorActors(EXPECTED);
		Assert.assertEquals(EXPECTED, movie.getMajorActors());
	}

	@Test
	public void addMajorActorTestValid(){
		Movie movie = new Movie();
		final String ACTORNAME = "Bob dunes";
		movie.setMajorActors(new ArrayList<String>());
		movie.addMajorActor(ACTORNAME);

		Assert.assertEquals(ACTORNAME, movie.getMajorActors().get(0));
	}

	@Test
	public void addMajorActorTestInvalidRepeat(){
		Movie movie = new Movie();
		final String ACTORNAME = "Bob dunes";
		movie.setMajorActors(new ArrayList<String>(Arrays.asList(ACTORNAME)));
		final int LENGTH = 1;
		movie.addMajorActor(ACTORNAME);

		Assert.assertEquals(LENGTH, movie.getMajorActors().size());
	}

	@Test
	public void removeMajorActorTestValid(){
		Movie movie = new Movie();
		final String ACTORNAME = "Bob dunes";
		movie.setMajorActors(new ArrayList<String>(Arrays.asList(ACTORNAME)));
		final int LENGTH = 0;

		movie.removeMajorActor(ACTORNAME);
		Assert.assertEquals(LENGTH, movie.getMajorActors().size());
	}

	@Test
	public void toStringTest(){
		final String NAME = "Harry Potter";
		final int AGERATING = 0;
		final int OFFRATING = 0;
		final String GENRE = "Action";
		final ArrayList<String> ACTORS = new ArrayList<>(Arrays.asList("Daniel Radcliffe"));
		final ArrayList<String> PRODUCERS = new ArrayList<>(Arrays.asList("Daniel Radcliffe"));

		final Movie movie = new Movie(NAME, OFFRATING, AGERATING, null, ACTORS, PRODUCERS);

		final String MOVIESTRING = "Movie" + " Name: " + NAME + "\nIMDB Rating: " + OFFRATING + "\nAge Rating: " + AGERATING
				+ "\nOverall Viewer Rating: " + 0 + "\nProducers: " + PRODUCERS + "\nGenre: " + GENRE + "\nFamous Actors: " + ACTORS + "\n";



	}

}
