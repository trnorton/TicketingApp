import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.io.*;

import org.junit.jupiter.api.Test;

class ConcessionsSystemTest {

	
	@Test
	public void testConcessionsSystem() {
		ConcessionsSystem concessions = new ConcessionsSystem();
		assertNotNull(concessions);
	}

	@Test
	public void testAddValidConcessions() {
		ConcessionsSystem concessions = new ConcessionsSystem();
		int initialSize = concessions.getConcessions().size();
		concessions.addConcessions(new Concession("Pizza", 5.0, 50));
		int finalSize = concessions.getConcessions().size();
		assertSame(initialSize+1, finalSize);
	}
	
	@Test
	public void testAddInvalidConcessions() {
		ConcessionsSystem concessions = new ConcessionsSystem();
		concessions.addConcessions(null);
		int finalSize = concessions.getConcessions().size();
		assertNull(concessions.getConcessions().get(finalSize-1));
	}

	@Test
	public void testRemoveValidConcessions() {
		ConcessionsSystem concessions = new ConcessionsSystem();
		int initialSize = concessions.getConcessions().size();
		concessions.removeConcessions(concessions.getConcessions().get(0));
		int finalSize = concessions.getConcessions().size();
		assertSame(initialSize-1, finalSize);
	}
	
	@Test
	public void testRemoveInvalidConcessions() {
		ConcessionsSystem concessions = new ConcessionsSystem();
		int initialSize = concessions.getConcessions().size();
		concessions.removeConcessions(null);
		int finalSize = concessions.getConcessions().size();
		assertSame(initialSize, finalSize);
	}

	
	@Test
	public void testShowConcessions() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 
        System.setOut(new PrintStream(outContent));
        ConcessionsSystem concessions = new ConcessionsSystem();
        concessions.showConcessions();
        assertEquals("Popcorn - Quantity: 50 - Price: $4.0\n" + "Soda - Quantity: 50 - Price: $3.0\n" + "Twizzlers - Quantity: 50 - Price: $1.5\n", outContent.toString());
	}

	@Test
	public void testGetConcessions() {
		ConcessionsSystem concessions = new ConcessionsSystem();
		ArrayList<Concession> concessionsArrayList = new ArrayList<Concession>();
		concessionsArrayList.add(new Concession("Popcorn", 4.0, 50));
		concessionsArrayList.add(new Concession("Soda", 3.0, 50));
		concessionsArrayList.add(new Concession("Twizzlers", 1.5, 50));
		assertEquals(concessions.getConcessions().toString(), concessionsArrayList.toString());
	}

}
