package Model_Layer;

//import java.sql.Date;
import java.util.Date;

public class Daytrip {
	
	private String name;
	private String location;
	private int duration;
	private int averageRating;
	private int price;
	private String activity;
	private int seatsAvailable;
	private String description;
	//private list<Review> review;
	
	public Daytrip(String tripName, String tripLocation, int tripDuration, int tripAverageRating, 
					int tripPrice, String tripActivity, int tripSeatsAvailable, String tripDescription)
	{
		name = tripName;
		location = tripLocation;
		duration = tripDuration;
		averageRating = tripAverageRating;
		price = tripPrice;
		activity = tripActivity;
		seatsAvailable = tripSeatsAvailable;
		description = tripDescription;
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
	
	public int getSeatsAvailable(Date day) {
		return seatsAvailable;
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

}
