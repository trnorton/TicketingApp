import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayTest {

	@Test
	public void correctValuesInputTestName(){
		final String NAME = "Harry Potter";
		final int AGERATING = 0;
		final int OFFRATING = 0;
		final String GENRE = "Action";
		final ArrayList<String> ACTORS = new ArrayList<>(Arrays.asList("Daniel Radcliffe"));
		final ArrayList<String> PRODUCERS = new ArrayList<>(Arrays.asList("Daniel Radcliffe"));

		final Play EXPECTED = new Play(NAME, OFFRATING, AGERATING, null, null, PRODUCERS, ACTORS);

		Play play = new Play(NAME, OFFRATING, AGERATING, null, null, PRODUCERS, ACTORS);

		Assert.assertEquals(EXPECTED.getName(), play.getName());

	}

	@Test
	public void getMajorActorsTest(){
		Play play = new Play();
		final String MAJORACTOR = "Bob Dunes";
		final ArrayList<String> EXPECTED = new ArrayList<>(Arrays.asList(MAJORACTOR));
		play.setMajorActors(EXPECTED);

		Assert.assertEquals(EXPECTED, play.getMajorActors());
	}

	@Test
	public void setMajorActorsTest(){
		Play play = new Play();
		final String MAJORACTOR = "Bob Dunes";
		final ArrayList<String> EXPECTED = new ArrayList<>(Arrays.asList(MAJORACTOR));

		play.setMajorActors(EXPECTED);
		Assert.assertEquals(EXPECTED, play.getMajorActors());
	}

	@Test
	public void addMajorActorTestValid(){
		Play play = new Play();
		final String ACTORNAME = "Bob dunes";
		play.setMajorActors(new ArrayList<String>());
		play.addMajorActor(ACTORNAME);

		Assert.assertEquals(ACTORNAME, play.getMajorActors().get(0));
	}

	@Test
	public void addMajorActorTestInvalidRepeat(){
		Play play = new Play();
		final String ACTORNAME = "Bob dunes";
		play.setMajorActors(new ArrayList<String>(Arrays.asList(ACTORNAME)));
		final int LENGTH = 1;
		play.addMajorActor(ACTORNAME);

		Assert.assertEquals(LENGTH, play.getMajorActors().size());
	}

	@Test
	public void removeMajorActorTestValid(){
		Play play = new Play();
		final String ACTORNAME = "Bob dunes";
		play.setMajorActors(new ArrayList<String>(Arrays.asList(ACTORNAME)));
		final int LENGTH = 0;

		play.removeMajorActor(ACTORNAME);
		Assert.assertEquals(LENGTH, play.getMajorActors().size());
	}

	@Test
	public void toStringTest(){
		final String NAME = "Harry Potter";
		final int AGERATING = 0;
		final int OFFRATING = 0;
		final String GENRE = "Action";
		final ArrayList<String> ACTORS = new ArrayList<>(Arrays.asList("Daniel Radcliffe"));
		final ArrayList<String> PRODUCERS = new ArrayList<>(Arrays.asList("Daniel Radcliffe"));

		final Play play = new Play(NAME, OFFRATING, AGERATING, null, null, PRODUCERS, ACTORS);

		final String MOVIESTRING = "Play" + " Name: " + NAME + "\nIMDB Rating: " + OFFRATING + "\nAge Rating: " + AGERATING
				+ "\nOverall Viewer Rating: " + 0 + "\nProducers: " + PRODUCERS +  "\nFamous Actors: " + ACTORS + "\n";



	}
	
}
