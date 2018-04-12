package Model_Layer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Basket {
	
	private ArrayList<Booking> bookings;
	private Customer customerInfo;
	
	public Basket() 
	{
		bookings = new ArrayList<Booking>();
		customerInfo = new Customer();
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}
	
	public String getCustomerName() {
		return customerInfo.getFullName();
	}
	
	public Customer getCustomerInfo() {
		return customerInfo;
	}
	
	public void addBooking(Daytrip trip, int seats, String day) {
		System.out.println("add to basket: " + trip.getName() + ", seats: " + seats + ", date: " + day);
		bookings.add(new Booking(trip, seats, day));
	}
	
	public void removeTrip(Booking booking) {
		bookings.remove(booking);
	}
	
	public void emptyBasket() {
		bookings.clear();
	}
	
	public int getTotal() {
		int totalPrice = 0;
		
		for (int i = 0; i < bookings.size(); i++) {
			totalPrice += bookings.get(i).getTrip().getPrice() * bookings.get(i).getSeats();
		}
		
		return totalPrice;
	}
	
	public void bookTrips() {
		//b�ka
	}
	
	public void cancelBooking() {
		//cancella
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
