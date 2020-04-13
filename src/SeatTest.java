import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SeatTest {

	@Test
	public void testSeat() {
		Seat seat = new Seat('A', 1);
		assertNotNull(seat);
	}

	@Test
	public void testGetRow() {
		Seat seat = new Seat('A', 1);
		assertEquals(seat.getRow(), 'A');
	}

	@Test
	public void testSetValidRow() {
		Seat seat = new Seat('A', 1);
		seat.setRow('B');
		assertEquals(seat.getRow(), 'B');
	}
	
	@Test
	public void testSetInvalidRow() {
		Seat seat = new Seat('A', 1);
		seat.setRow(Character.MIN_VALUE);
		assertEquals(seat.getRow(), Character.MIN_VALUE);
	}

	@Test
	public void testGetColumn() {
		Seat seat = new Seat('A', 1);
		assertEquals(seat.getColumn(), 1);
	}

	@Test
	public void testSetValidColumn() {
		Seat seat = new Seat('A', 1);
		seat.setColumn(2);
		assertEquals(seat.getColumn(), 2);
	}
	
	@Test
	public void testSetInvalidColumn() {
		Seat seat = new Seat('A', 1);
		seat.setColumn(-1);
		assertEquals(seat.getColumn(), -1, "Invalid value for columns");
	}

	@Test
	public void testCheckIfTaken() {
		Seat seat = new Seat('A', 1);
		assertFalse(seat.checkIfTaken());
	}

	@Test
	public void testGetSeatStatus() {
		Seat seat = new Seat('A', 1);
		assertEquals(seat.getSeatStatus(), " ");
	}

	@Test
	public void testSetValidSeatStatus() {
		Seat seat = new Seat('A', 1);
		seat.setSeatStatus("X");
		assertEquals(seat.getSeatStatus(), "X");
	}
	
	@Test
	public void testSetInvalidSeatStatus() {
		Seat seat = new Seat('A', 1);
		seat.setSeatStatus("");
		assertEquals(seat.getSeatStatus(), "", "Seat status can't be anything but ' ' or 'X'");
	}

	@Test
	public void testChangeSeatAvailability() {
		Seat seat = new Seat('A', 1);
		seat.changeSeatAvailability();
		assertTrue(seat.checkIfTaken());
		assertEquals(seat.getSeatStatus(), "X");
	}

	@Test
	public void testToString() {
		Seat seat = new Seat('A', 1);
		assertEquals(seat.toString(), "A1");
	}

	@Test
	public void testDisplaySeat() {
		Seat seat = new Seat('A', 1);
		assertEquals(seat.displaySeat(), "[ ]");
	}

}
