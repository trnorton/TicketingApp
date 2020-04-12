import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConcessionTest {

	@Test
	public void testConcession() {
		Concession concession = new Concession("Pizza", 5.0, 50);
		assertNotNull(concession);
	}

	@Test
	public void testGetName() {
		Concession concession = new Concession("Pizza", 5.0, 50);
		assertEquals(concession.getName(), "Pizza");
	}

	@Test
	public void testSetValidName() {
		Concession concession = new Concession("Pizza", 5.0, 50);
		concession.setName("Hotdog");
		assertEquals(concession.getName(), "Hotdog");
	}
	
	@Test
	public void testSetInvalidName() {
		Concession concession = new Concession("Pizza", 5.0, 50);
		concession.setName(null);
		assertNull(concession.getName());
	}

	@Test
	public void testGetPrice() {
		Concession concession = new Concession("Pizza", 5.0, 50);
		assertEquals(concession.getPrice(), 5.0);
	}

	@Test
	public void testSetValidPrice() {
		Concession concession = new Concession("Pizza", 5.0, 50);
		concession.setPrice(6.0);
		assertEquals(concession.getPrice(), 6.0);
	}

	@Test
	public void testGetQuantity() {
		Concession concession = new Concession("Pizza", 5.0, 50);
		assertSame(concession.getQuantity(), 50);
	}

	@Test
	public void testSetValidQuantity() {
		Concession concession = new Concession("Pizza", 5.0, 50);
		concession.setQuantity(40);
		assertSame(concession.getQuantity(), 40);
	}

	@Test
	public void testToString() {
		Concession concession = new Concession("Pizza", 5.0, 50);
		assertEquals(concession.toString(), "Pizza - Quantity: 50 - Price: $5.0");
	}

}
