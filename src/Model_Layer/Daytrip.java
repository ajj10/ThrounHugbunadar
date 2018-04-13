package Model_Layer;

//import java.sql.Date;
import java.util.Date;

import Storage_Layer.DatabaseManager;

import java.util.ArrayList;

public class Daytrip {
	
	private int ID;
	private String name;
	private String location;
	private int duration;
	private int averageRating;
	private int price;
	private String activity;
	private int seatsAvailable;
	private String description;
	private ArrayList<Review> reviews;
	
	public Daytrip(int id, String tripName, String tripLocation, int tripDuration, int tripAverageRating, 
					int tripPrice, String tripActivity, int tripSeatsAvailable, String tripDescription, ArrayList<Review> tripReviews)
	{
		ID = id;
		name = tripName;
		location = tripLocation;
		duration = tripDuration;
		averageRating = tripAverageRating;
		price = tripPrice;
		activity = tripActivity;
		seatsAvailable = tripSeatsAvailable;
		description = tripDescription;
		reviews = tripReviews;
	}
	
	public String getName() {	
		return name;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public String getLocation() {
		return location;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getAverageRating() {
		return averageRating;
	}
	
	public String getActivity() {
		return activity;
	}
	
	public int getSeatsAvailable(String day) {
		return seatsAvailable - DatabaseManager.bookedSeats(ID, day);
	}
	
	public String getDescription() {
		return description;
	}
	
	public void decreaseSeatsAvailable(int seats) {
		seatsAvailable -= seats;
	}
	
	//getReviews(): list<Reviews>
	
	//addReview(review: Review): void
	
	public void increaseSeatsAvailable(int seats) {
		seatsAvailable += seats;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int getID() {
		return ID;
	}

}
