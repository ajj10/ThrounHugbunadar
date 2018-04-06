package Controller_Layer;

import Model_Layer.Daytrip;
import Storage_Layer.DatabaseManager;

import java.sql.SQLException;
import java.util.ArrayList;

public class TripSearch {
	
	private ArrayList<Daytrip> trips;
	//private DatabaseManager database;
	private String location;
	private String activity;
	private int[] priceRange;
	private int rating;
	private int duration;
	
	
	public TripSearch(ArrayList<Daytrip> Trips, String Location, String Activity, int lowPrice, int highPrice,
			int Rating, int Duration) 
	{
		trips = Trips;
		location = Location;
		activity = Activity;
		priceRange = new int[2];
		priceRange[0] = lowPrice;
		priceRange[1] = highPrice;
		rating = Rating;
		duration = Duration;
	}
	
	public void updateTrips() throws SQLException {
		//update
		trips = DatabaseManager.getTrips(location, activity, priceRange, rating, duration);
	}
	
	public void sortByRating() {
		Daytrip temp;
		for(int i = 0; i < trips.size()-1; i++) {
			for(int j = 1+i; j < trips.size(); j++) {
				if(trips.get(i).getAverageRating() < trips.get(j).getAverageRating()) {
					temp = trips.get(i);
					trips.set(i, trips.get(j));
					trips.set(j, temp);
				}
			}
		}
	}
	
	public void sortByPrice() {
		Daytrip temp;
		for(int i = 0; i < trips.size()-1; i++) {
			for(int j = 1+i; j < trips.size(); j++) {
				if(trips.get(i).getPrice() > trips.get(j).getPrice()) {
					temp = trips.get(i);
					trips.set(i, trips.get(j));
					trips.set(j, temp);
				}
			}
		}
	}
	
	public void sortByDuration() {
		Daytrip temp;
		for(int i = 0; i < trips.size()-1; i++) {
			for(int j = 1+i; j < trips.size(); j++) {
				if(trips.get(i).getDuration() > trips.get(j).getDuration()) {
					temp = trips.get(i);
					trips.set(i, trips.get(j));
					trips.set(j, temp);
				}
			}
		}
	}
	
	public ArrayList<Daytrip> search(String searchString) {
		ArrayList<Daytrip> searchStringTrips = new ArrayList<Daytrip>();
		
		for(int i = 0; i < trips.size(); i++) {
			if(trips.get(i).getName().toLowerCase().startsWith(searchString.toLowerCase())) {
				searchStringTrips.add(trips.get(i));
			}
			else if(trips.get(i).getLocation().toLowerCase().startsWith(searchString.toLowerCase())) {
				searchStringTrips.add(trips.get(i));
			}
		}
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
	
	public void setDuration(int Duration) {
		duration = Duration;
	}
	
	public ArrayList<Daytrip> getTrips() {
		return trips;
	}
	
	//getDatabase(): DatabaseManager

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
