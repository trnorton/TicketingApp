import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerTest {

	@Test
	public void testCustomer() {
		Customer customer = new Customer("Customer", "01/01/1980", "(803)123-4567", "123 Simple St", "customer@gmail.com");
		assertNotNull(customer);
	}
	
	@Test
	public void testToString() {
		Customer customer = new Customer("Customer", "01/01/1980", "(803)123-4567", "123 Simple St", "customer@gmail.com");
		String stringRepresentation = "\n\nName: Customer\nBirthday: 01/01/1980\nPhone Number: (803)123-4567\nAddress: 123 Simple St\nEmail: customer@gmail.com";
		assertEquals(customer.toString(), stringRepresentation);
	}

}
