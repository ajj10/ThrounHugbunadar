package Controller_Layer1;

import org.junit.*;

import Controller_Layer.TripSearch;
import junit.framework.TestCase;
import java.sql.SQLException;

public class TripSearchTest extends TestCase{
	private TripSearch mySearch;
	private TripSearch mySearch1;
	
	@Before
	public void setUp() throws SQLException {
		mySearch = new TripSearch(null, "Reykjavík", null, 0, 100000, -1, -1);
		mySearch.updateTrips();
		mySearch1 = new TripSearch(null, null , null, 0, 100000, -1, -1);
		mySearch1.updateTrips();
	}
	
	@After
	public void tearDown() {
		mySearch = null;
		mySearch1 = null;
	}
	
	@Test
	public void testname() {
		assertEquals(mySearch.getTrips().get(3).getName(), "Glacier Adventure");
	}
	
	@Test
	public void testactivity() {
		assertEquals(mySearch.getTrips().get(2).getActivity(), "Caving");
	}
	
	@Test
	public void testsortbyprice() {
		mySearch1.sortByPrice();
		assertEquals(mySearch1.getTrips().get(0).getPrice(), 11400);
		assertEquals(mySearch1.getTrips().get(1).getPrice(), 14680);
		assertEquals(mySearch1.getTrips().get(2).getPrice(), 27578);
		assertEquals(mySearch1.getTrips().get(3).getPrice(), 29010);
		assertEquals(mySearch1.getTrips().get(4).getPrice(), 35900);
	}
}
