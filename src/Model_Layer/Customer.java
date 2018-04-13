package Model_Layer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		Pattern validRegex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = validRegex.matcher(email);
		if(validateCreditCardNumber(creditCard) && fullName.length() > 3 && matcher.find()) return true;
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private boolean validateCreditCardNumber(String str) {

		int[] ints = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			ints[i] = Integer.parseInt(str.substring(i, i + 1));
		}
		for (int i = ints.length - 2; i >= 0; i = i - 2) {
			int j = ints[i];
			j = j * 2;
			if (j > 9) {
				j = j % 10 + 1;
			}
			ints[i] = j;
		}
		int sum = 0;
		for (int i = 0; i < ints.length; i++) {
			sum += ints[i];
		}
		if (sum % 10 == 0) {
			return true;
		} else {
			return false;
		}
	}

}
