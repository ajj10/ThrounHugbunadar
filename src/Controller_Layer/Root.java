package Controller_Layer;

import java.sql.SQLException;
import java.util.ArrayList;
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
	
	public ArrayList<Daytrip> search(String searchString) {
		return myTripSearch.search(searchString);
	}
	
	public ArrayList<Daytrip> findTrips(String location, String activity, int[] price, int rating, int duration) throws SQLException {
		myTripSearch.setLocation(location);
		myTripSearch.setActivity(activity);
		myTripSearch.setPriceRange(price);
		myTripSearch.setRating(rating);
		myTripSearch.setDuration(duration);
		myTripSearch.updateTrips();
		return myTripSearch.getTrips();
	}

}
