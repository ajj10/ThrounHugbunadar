package Model_Layer;

import java.util.ArrayList;

public class Basket {
	
	private ArrayList<Daytrip> trips;
	private Customer customerInfo;
	
	public Basket(ArrayList<Daytrip> Trips, Customer customer) 
	{
		trips = Trips;
		customerInfo = customer;
	}

	public ArrayList<Daytrip> getTrips() {
		return trips;
	}
	
	public String getCustomerName() {
		return customerInfo.getFullName();
	}
	
	public void addTrip(Daytrip trip) {
		trips.add(trip);
	}
	
	public void removeTrip(Daytrip trip) {
		trips.remove(trip);
	}
	
	public void emptyBasket() {
		customerInfo = null;
		trips = null;
	}
	
	public int getTotal() {
		int totalPrice = 0;
		
		for (int i = 0; i < trips.size(); i++) {
			totalPrice += trips.get(i).getPrice();
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
