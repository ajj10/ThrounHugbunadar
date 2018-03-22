package Model_Layer;

public class Basket {
	
	private Model_Layer.Daytrip[] trips;
	private Model_Layer.Customer customerInfo;
	
	public Basket(Daytrip[] Trips, Customer customer) 
	{
		trips = Trips;
		customerInfo = customer;
	}

	public Daytrip[] getTrips() {
		return trips;
	}
	
	public String getCustomerName() {
		return customerInfo.getFullName();
	}
	
	public void addTrip(Daytrip trip) {
		int len = trips.length;
		trips[len] = trip;
	}
	
	public void removeTrip(Daytrip trip) {
		//remove
	}
	
	public void emptyBasket() {
		customerInfo = null;
		trips = null;
	}
	
	public int getTotal() {
		int totalPrice = 0;
		
		for (int i = 0; i < trips.length; i++) {
			totalPrice += trips[i].getPrice();
		}
		
		return totalPrice;
	}
	
	public void bookTrips() {
		//bóka
	}
	
	public void cancelBooking() {
		//cancella
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
