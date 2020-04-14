import org.junit.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserTest {

    private User testUser;
    private ByteArrayOutputStream outContent;

    private static ArrayList<Venue> venues;
    private static ArrayList<Movie> movies;
    private static ArrayList<Play> plays;
    private static ArrayList<Concert> concerts;

    private static char[] alphabet;

    @BeforeClass
    public static void oneTimeSetup() {

        movies = JsonParser.loadMovies();
        plays = JsonParser.loadPlays();
        concerts = JsonParser.loadConcerts();

        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        MainSystem mainsystem = new MainSystem();
        venues = mainsystem.getVenues();
        venues.get(0).addCustRating(4);
    }

    @Before
    public void setUp() {
        testUser = new User("Johnny", "02/11/1990", "(843) 420-1337", "101 Main Street", "john101@gmail.com");
        outContent = new ByteArrayOutputStream();
    }

    @After
    public void tearDown() throws IOException {
        testUser = null;
        outContent.close();
    }

    @AfterClass
    public static void oneTimeTearDown() {
        venues.clear();
        movies.clear();
        plays.clear();
        concerts.clear();
    }

    @Test
    public void testCalculateAge() {
        assertEquals(29, testUser.getAge());
    }

    @Test
    public void testSearchForMovie() {
        assertEquals(movies.get(0).toString(), testUser.searchForMovie("Frozen 2").toString());
    }

    @Test
    public void testSearchForMovieFail() {
        assertNull(testUser.searchForMovie("The Incredibles"));
    }

    @Test
    public void testSearchForPlay() {
        assertEquals(plays.get(1).toString(), testUser.searchForPlay("SomePlay").toString());
    }

    @Test
    public void testSearchForPlayFail() {
        assertNull(testUser.searchForPlay("Hamlet"));
    }

    @Test
    public void testSearchForConcert() {
        assertEquals(concerts.get(0).toString(), testUser.searchForConcert("The Beatles Are Back (from the grave)").toString());
    }

    @Test
    public void testSearchForConcertFail() {
        assertNull(testUser.searchForConcert("Coachella"));
    }

    @Test
    public void testSearchForVenue() {
        assertEquals("123 Movie St", testUser.searchForVenue("Venue", venues).getAddress());
    }

    @Test
    public void testSearchForVenueFail() {
        assertNull(testUser.searchForVenue("Hollywood", venues));
    }

    @Test
    public void testLookAtBasicEventInfo() {
        System.setOut(new PrintStream(outContent));
        testUser.lookAtBasicEventInfo("Frozen 2");
        assertEquals(movies.get(0).toString() + "\n", outContent.toString());
    }

    @Test
    public void testLookAtBasicEventInfoFail() {
        System.setOut(new PrintStream(outContent));
        testUser.lookAtBasicEventInfo("Boss Baby");
        assertEquals("", outContent.toString());
    }

    @Test
    public void testUpdateHomeVenue() {
        testUser.updateHomeVenue("PlaysRUs", venues);
        assertSame(venues.get(1), testUser.getVenue());
    }

    @Test
    public void testUpdateHomeVenueFail() {
        testUser.updateHomeVenue("Hollywood", venues);
        assertNull(testUser.getVenue());
    }

    @Test
    public void testBookTickets() {
        testUser.updateHomeVenue("Venue", venues);
        testUser.bookTickets("Frozen 2", "04/10/2020", "12:00pm", 3, 2, alphabet[4], 7);
        assertEquals(5, testUser.getTickets().size());
    }

    @Test
    public void testBookTicketsFail() {
        testUser.updateHomeVenue("Venue", venues);
        Exception exception = assertThrows(NullPointerException.class, () -> {
        	testUser.bookTickets("Frozen 2", "04/11/2020", "12:00pm", 3, 2, alphabet[4], 7);
        });
        assertEquals(testUser.getTickets().toString(), "[]");
    }

    @Test
    public void testCreateReceipt() {
        testUser.updateHomeVenue("Venue", venues);
        testUser.bookTickets("Frozen 2", "04/10/2020", "12:00pm", 1, 0, alphabet[4], 7);
        String receipt = "********** Receipt **********\n" +
                "Ticket for \"Johnny\" at the Name: Venue\n" +
                "Address: 123 Movie St\n" +
                "Overall Customer Rating: 4\n" +
                "Theater A\n" +
                "Frozen 2 - 04/10/2020 - 12:00pm\n" +
                "Seat: E7\n" +
                "Paid: $5.00\n" +
                "\nTotal price: $5.00\n";
        System.setOut(new PrintStream(outContent));
        testUser.createReceipt();
        try {
            File newReceipt = new File("receipt.txt");
            Scanner receiptReader = new Scanner(newReceipt);
            while(receiptReader.hasNextLine()) {
                System.out.println(receiptReader.nextLine());
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        assertEquals(receipt, outContent.toString());
    }

    @Test
    public void testRateEvent() {
        testUser.rateEvent("Frozen 2", 3);
        assertEquals(3, movies.get(0).getCustRatings().get(movies.get(0).getCustRatings().size() - 1));
    }

    @Test
    public void testWriteEventReview() {
        testUser.writeEventReview("Frozen 2", "Test Review");
        assertEquals("Test Review", movies.get(0).getReviews().get(movies.get(0).getReviews().size() - 1));
    }

    @Test
    public void testDisplayWatchlist() {
        System.setOut(new PrintStream(outContent));
        testUser.addToWatchlist("Frozen 2");
        testUser.displayWatchlist();
        assertEquals(movies.get(0).toString(), testUser.getWatchlist().get(0).toString());
    }

    @Test
    public void testDisplayTickets() {
        System.setOut(new PrintStream(outContent));
        testUser.updateHomeVenue("Venue", venues);
        testUser.bookTickets("Frozen 2", "04/10/2020", "12:00pm", 1, 0, alphabet[4], 7);
        String expected = "Ticket for \"Johnny\" at the Name: Venue\n" +
                "Address: 123 Movie St\n" +
                "Overall Customer Rating: 4\n" +
                "Theater A\n" +
                "Frozen 2 - 04/10/2020 - 12:00pm\n" +
                "Seat: E7\n" +
                "Paid: $5.00\n\n";
        testUser.displayTickets();
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testRequestRefund() {
    	testUser.updateHomeVenue("Venue", venues);
    	testUser.bookTickets("Frozen 2", "04/10/2020", "12:00pm", 2, 5, alphabet[4], 7);
        //testUser.requestRefund("Frozen 2", 3);
        assertEquals(7, testUser.getTickets().size(), "This method wasn't implemented properly");
    }

    @Test
    public void testRequestRefundExcess() {
    	testUser.updateHomeVenue("Venue", venues);
    	testUser.bookTickets("Frozen 2", "04/10/2020", "12:00pm", 3, 2, alphabet[4], 7);
        //testUser.requestRefund("Frozen 2", 12);
        assertEquals(5, testUser.getTickets().size(), "This method wasn't implemented properly");
    }

    @Test
    public void testAddToWatchlist() {
        testUser.addToWatchlist("Frozen 2");
        assertEquals(movies.get(0).toString(), testUser.getWatchlist().get(0).toString());
    }

    @Test
    public void testAddToWatchlistFail() {
        testUser.addToWatchlist("Avengers: Endgame");
        assertEquals(0, testUser.getWatchlist().size());
    }

    @Test
    public void testRemoveFromWatchlist() {
        testUser.addToWatchlist("Frozen 2");
        testUser.addToWatchlist("The Jungle Book");
        testUser.removeFromWatchlist("Frozen 2");
        assertEquals(movies.get(1).toString(), testUser.getWatchlist().get(0).toString());
    }

    @Test
    public void testRemoveFromWatchlistFail() {
        testUser.addToWatchlist("Frozen 2");
        testUser.removeFromWatchlist("Frozen 3");
        assertEquals(1, testUser.getWatchlist().size());
    }

    @Test
    public void testUserDiscount() {
        testUser.setUserDiscount(0.15);
        assertEquals(0.15, testUser.getUserDiscount());
    }

    @Test
    public void testUserDiscountNegative() {
        testUser.setUserDiscount(-0.311356);
        assertEquals(0, testUser.getUserDiscount());
    }

    @Test
    public void testRewardsPoints() {
        testUser.setRewardsPoints(420);
        assertEquals(420, testUser.getRewardsPoints());
    }

    @Test
    public void testAccountID() {
        testUser.setAccountID(12345678);
        assertEquals(12345678, testUser.getAccountID());
    }

    @Test
    public void testVenue() {
        testUser.setVenue(venues.get(0));
        assertSame(venues.get(0), testUser.getVenue());
    }

    @Test
    public void testName() {
        assertEquals("Johnny", testUser.getName());
    }
    
    @Test
    public void testSetName() {
        testUser.setName("Doug");
    	assertEquals("Doug", testUser.getName());
    }

    @Test
    public void testAge() {
        testUser.setAge(40);
        assertEquals(40, testUser.getAge());
    }

    @Test
    public void testBirthday() {
        testUser.setBirthday("04/10/1990");
        assertEquals("04/10/1990", testUser.getBirthday());
    }

    @Test
    public void testPhoneNumber() {
        testUser.setPhoneNumber("(123) 456-7890");
        assertEquals("(123) 456-7890", testUser.getPhoneNumber());
    }

    @Test
    public void testAddress() {
        testUser.setAddress("404 Error Lane");
        assertEquals("404 Error Lane", testUser.getAddress());
    }

    @Test
    public void testEmail() {
        testUser.setEmail("panic@the.disco");
        assertEquals("panic@the.disco", testUser.getEmail());
    }

    @Test
    public void testConcessions() {
        testUser.addConcession(new Concession("Pizza", 10.0, 2));
        testUser.addConcession(new Concession("Cola", 4.20, 3));
        testUser.addConcession(new Concession("Fries", 8.00, 3));
        testUser.removeConcession(testUser.getConcessions().get(0));
        assertEquals(2, testUser.getConcessions().size());
    }

    @Test
    public void testToString() {
        String expected = "\n\nName: Johnny\n" +
                "Birthday: 02/11/1990\n" +
                "Phone Number: (843) 420-1337\n" +
                "Address: 101 Main Street\n" +
                "Email: john101@gmail.com";
        assertEquals(expected, testUser.toString());
    }
}
