package Controller_Layer;

import java.sql.SQLException;
import java.util.ArrayList;


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
	
	public void bookSeat(Daytrip trip, String day, int numSeats) {
		myBasket.addBooking(trip, numSeats, day);
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
	
	public ArrayList<Daytrip> sortByRating() {
		myTripSearch.sortByRating();
		return myTripSearch.getTrips();
	}
	
	public ArrayList<Daytrip> sortByPrice() {
		myTripSearch.sortByPrice();
		return myTripSearch.getTrips();
	}
	
	public ArrayList<Daytrip> sortByDuration() {
		myTripSearch.sortByDuration();
		return myTripSearch.getTrips();
	}
	
	public Basket getBasket() {
		return myBasket;
	}

}
