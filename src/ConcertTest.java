import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ConcertTest {

	@Test
	public void correctValuesInputTestName(){
		final String NAME = "Harry Potter";
		final int AGERATING = 0;
		final int OFFRATING = 0;
		final String GENRE = "Action";
		final ArrayList<String> ACTORS = new ArrayList<>(Arrays.asList("Daniel Radcliffe"));
		final ArrayList<String> PRODUCERS = new ArrayList<>(Arrays.asList("Daniel Radcliffe"));

		final Concert EXPECTED = new Concert(NAME, OFFRATING, AGERATING, null, null, PRODUCERS, ACTORS);

		Concert concert = new Concert(NAME, OFFRATING, AGERATING, null, null, PRODUCERS, ACTORS);

		Assert.assertEquals(EXPECTED.getName(), concert.getName());

	}

	@Test
	public void getPerformersTest(){
		Concert concert = new Concert();
		final String MAJORACTOR = "Bob Dunes";
		final ArrayList<String> EXPECTED = new ArrayList<>(Arrays.asList(MAJORACTOR));
		concert.setPerformers(EXPECTED);

		Assert.assertEquals(EXPECTED, concert.getPerformers());
	}

	@Test
	public void setPerformersTest(){
		Concert concert = new Concert();
		final String MAJORACTOR = "Bob Dunes";
		final ArrayList<String> EXPECTED = new ArrayList<>(Arrays.asList(MAJORACTOR));

		concert.setPerformers(EXPECTED);
		Assert.assertEquals(EXPECTED, concert.getPerformers());
	}

	@Test
	public void addPerformerTestValid(){
		Concert concert = new Concert();
		final String ACTORNAME = "Bob dunes";
		concert.setPerformers(new ArrayList<String>());
		concert.addPerformer(ACTORNAME);

		Assert.assertEquals(ACTORNAME, concert.getPerformers().get(0));
	}

	@Test
	public void addPerformerTestInvalidRepeat(){
		Concert concert = new Concert();
		final String ACTORNAME = "Bob dunes";
		concert.setPerformers(new ArrayList<String>(Arrays.asList(ACTORNAME)));
		final int LENGTH = 1;
		concert.addPerformer(ACTORNAME);

		Assert.assertEquals(LENGTH, concert.getPerformers().size());
	}

	@Test
	public void removePerformerTestValid(){
		Concert concert = new Concert();
		final String ACTORNAME = "Bob dunes";
		concert.setPerformers(new ArrayList<String>(Arrays.asList(ACTORNAME)));
		final int LENGTH = 0;

		concert.removePerformer(ACTORNAME);
		Assert.assertEquals(LENGTH, concert.getPerformers().size());
	}

	@Test
	public void toStringTest(){
		final String NAME = "Harry Potter";
		final int AGERATING = 0;
		final int OFFRATING = 0;
		final String GENRE = "Action";
		final ArrayList<String> ACTORS = new ArrayList<>(Arrays.asList("Daniel Radcliffe"));
		final ArrayList<String> PRODUCERS = new ArrayList<>(Arrays.asList("Daniel Radcliffe"));

		final Concert concert = new Concert(NAME, OFFRATING, AGERATING, null, null, PRODUCERS, ACTORS);

		final String MOVIESTRING = "Concert" + " Name: " + NAME + "\nIMDB Rating: " + OFFRATING + "\nAge Rating: " + AGERATING
				+ "\nOverall Viewer Rating: " + 0 + "\nProducers: " + PRODUCERS +  "\nFamous Actors: " + ACTORS + "\n";



	}

}
