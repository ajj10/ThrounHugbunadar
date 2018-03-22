package Controller_Layer;

import Model_Layer.Daytrip;

public class TripSearch {
	
	private Daytrip[] trips;
	//private DatabaseManager database;
	private String location;
	private String activity;
	private int[] priceRange;
	private int rating;
	private int duration;
	
	
	public TripSearch(Daytrip[] Trips, String Location, String Activity, int highPrice, int lowPrice,
			int Rating, int Duration) 
	{
		trips = Trips;
		location = Location;
		activity = Activity;
		priceRange[0] = lowPrice;
		priceRange[1] = highPrice;
		rating = Rating;
		duration = Duration;
	}
	
	public void updateTrips() {
		//update
	}
	
	public void sortByRating() {
		Daytrip temp;
		for(int i = 0; i < trips.length-1; i++) {
			for(int j = 1+i; j < trips.length; j++) {
				if(trips[i].getAverageRating() < trips[j].getAverageRating()) {
					temp = trips[i];
					trips[i] = trips[j];
					trips[j] = temp;
				}
			}
		}
	}
	
	public void sortByPrice() {
		Daytrip temp;
		for(int i = 0; i < trips.length-1; i++) {
			for(int j = 1+i; j < trips.length; j++) {
				if(trips[i].getPrice() > trips[j].getPrice()) {
					temp = trips[i];
					trips[i] = trips[j];
					trips[j] = temp;
				}
			}
		}
	}
	
	public Daytrip[] search(String searchString) {
		Daytrip[] searchStringTrips = null;
		return searchStringTrips;
	}
	
	public void setLocation(String Location) {
		location = Location;
	}
	
	public void setActivity(String Activity) {
		activity = Activity;
	}
	
	public void setPriceRange(int[] price) {
		priceRange[0] = price[0];
		priceRange[1] = price[1];
	}
	
	public void setRating(int Rating) {
		rating = Rating;
	}
	
	public Daytrip[] getTrips() {
		return trips;
	}
	
	//getDatabase(): DatabaseManager

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
