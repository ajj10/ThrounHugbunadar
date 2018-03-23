package Controller_Layer;

//import java.sql.Date;
import java.util.Date;

import Model_Layer.Basket;
import Model_Layer.Daytrip;
import Model_Layer.Review;

public class Root {
	
	private Basket myBasket;
	private TripSearch myTripSearch;
	
	public Root(Basket basket, TripSearch tripSearch) {
		myBasket = basket;
		myTripSearch = tripSearch;
	}
	
	public void writeReview(String name, Review review) {
		//review
	}
	
	public void bookSeat(String name, Date day, int numSeats) {
		//book
	}
	
	public Daytrip[] search(String searchString) {
		Daytrip[] trips = null;
		return trips;
	}
	
	public Daytrip[] findTrips(String location, String activity, int[] price, int rating, int duartion) {
		Daytrip[] trips = null;
		return trips;
	}

}
