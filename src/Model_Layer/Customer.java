package Model_Layer;

public class Customer {
	
	private int bookingID;
	private String fullName;
	private String email;
	private String creditCard;
	
	public Customer() 
	{
		bookingID = 123456;
		fullName = "";
		email = "";
		creditCard = "";
	}
	
	public void setFullName(String name) {
		fullName = name;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setEmail(String emailAddress) {
		email = emailAddress;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setBookingID(int ID) {
		bookingID = ID;
	}
	
	public int getBookingID() {
		return bookingID;
	}
	
	public void setCreditCard(String cardNumber) {
		creditCard = cardNumber;
	}
	
	public String getCreditCard() {
		return creditCard;
	}
	
	//pay(): void
	
	public void emailConfirmation() {
		//senda email
	}
	
	public boolean isValid() {
		boolean b = true;
		//ef allt rett �� true annars false
		return b;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
