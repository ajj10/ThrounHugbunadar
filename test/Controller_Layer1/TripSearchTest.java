package Controller_Layer1;

import org.junit.*;

import Controller_Layer.TripSearch;
import Model_Layer.Daytrip;
import junit.framework.TestCase;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

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
	public void testloction() {
		System.out.println(mySearch.getTrips().get(3).getName());
		assertEquals(mySearch.getTrips().get(3).getName(), "Glacier Adventure");
	}
	@Test
	public void testactivity() {
		System.out.println(mySearch.getTrips().get(2).getActivity());
		assertEquals(mySearch.getTrips().get(2).getActivity(), "Caving");
	}
	
	
	
}
