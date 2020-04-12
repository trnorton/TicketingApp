import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerSupportSystemTest {

	@Test
	public void testCustomerSupportSystem() {
		CustomerSupportSystem customerSupport = new CustomerSupportSystem();
		assertNotNull(customerSupport);
	}

	@Test
	public void testToString() {
		CustomerSupportSystem customerSupport = new CustomerSupportSystem();
		assertEquals(customerSupport.toString(), "This is the customer support page. If you are experiencing issues with the app, call the number below:\n(123)456-7890");
	}

}
