package Model_Layer;

public class Booking {
	
	private Daytrip trip;
	private int seats;
	private String day;

	public Booking(Daytrip myTrip, int numSeats, String date) {
		trip = myTrip;
		seats = numSeats;
		day = date;
	}
	
	public Daytrip getTrip() {
		return trip;
	}
	
	public int getSeats() {
		return seats;
	}
	
	public String getDay() {
		return day;
	}

}
