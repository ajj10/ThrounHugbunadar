package Controller_Layer1;

import org.junit.*;
import org.junit.jupiter.api.Test;

import Controller_Layer.TripSearch;
import Model_Layer.Daytrip;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class TripSearchTest {
	private TripSearch mySearch;
	@Before
	public void setUp() {
		mySearch = new TripSearch(null, "Reykjavík", null, 0, 100000, -1, -1);
		mySearch.updateTrips();
	}
	
	@After
	public void tearDown() {
		mySearch = null;
	}
	
	@Test
	public void testSomeBehavior() {
		assertEquals("Reykjavík","Reykjavík");
	}
}
