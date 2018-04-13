package Storage_Layer;

import java.sql.*;

import Model_Layer.Basket;
import Model_Layer.Daytrip;
import Model_Layer.Review;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseManager
{
  private static Connection connection;
  
  public static ArrayList<Daytrip> getTrips(String location, String activity, int[] price, int rating, int duration) throws SQLException {
	  connection = DriverManager.getConnection("jdbc:sqlite:database1.db");
	  Statement statement = connection.createStatement();
	  String query = "SELECT * FROM AllTrips";
	  query += " WHERE rating >= " + rating; //þarf að vera valið rating
	  
	  if(location != null) query += " AND location = '" + location + "'";
	  if(activity != null) query += " AND activity = '" + activity + "'";
	  if(price != null) query += " AND price >= " + price[0] + " AND price <= " + price[1];
	  if(duration > 0) query += " AND duration <= " + duration;
	  
	  query += ";";
	  System.out.println(query);
	  
	  ResultSet rs = statement.executeQuery(query);
	  ArrayList<Daytrip> trips = new ArrayList<Daytrip>();
	  while(rs.next()) {
		  trips.add(createDaytrip(rs));
	  }
	  return trips;
  }
  
  private static Daytrip createDaytrip(ResultSet rs) throws SQLException {
	  
	  int tripNumber = Integer.parseInt(rs.getString(1));
	  String tripName = rs.getString(2);
	  String tripLocation = rs.getString(3);
	  String tripActivity = rs.getString(4);
	  int tripPrice = -1;
	  if(rs.getString(5) != null) tripPrice = Integer.parseInt(rs.getString(5));
	  int tripAverageRating = -1;
	  if(rs.getString(6) != null) tripAverageRating = Integer.parseInt(rs.getString(6));
	  int tripDuration = -1;
	  if(rs.getString(7) != null) tripDuration = Integer.parseInt(rs.getString(7));
	  String tripDescription = rs.getString(8);
	  int tripSeatsAvailable = 7; //fast
	  
	  /*Statement statement1 = connection.createStatement();
	  String query1 = "SELECT * FROM Bookings WHERE tripNumber = " + tripNumber + ";";
	  ResultSet seats = statement1.executeQuery(query1);
	  
	  while(seats.next()) {
		  if(seats.getString(3) != null) tripSeatsAvailable = Integer.parseInt(seats.getString(3));
	  }*/
	  
	  //Reviews
	  Statement statement2 = connection.createStatement();
	  String query2 = "SELECT * FROM Review WHERE tripNumber = " + tripNumber + ";";
	  ResultSet reviews = statement2.executeQuery(query2);
	  ArrayList<Review> reviewsArray = new ArrayList<Review>();
	  while(reviews.next()) {
		  //vantar reviewTitle og Date í database
		  reviewsArray.add(new Review(reviews.getString(2), "reviewTitle", new Date(), reviews.getString(3), Integer.parseInt(reviews.getString(3))));
	  }
	  
	  Daytrip trips = new Daytrip(tripNumber, tripName, tripLocation, tripDuration, tripAverageRating, tripPrice, tripActivity, tripSeatsAvailable, tripDescription, reviewsArray);
      return trips;
  }
  
  private static void printResults(ResultSet rs) throws SQLException {
	  while(rs.next())
      {
        // read the result set
        System.out.println("Trip id: " + rs.getString(1));
        System.out.println("Trip Name: " + rs.getString(2));
        System.out.println("Departure Location: " + rs.getString(3)); 
        System.out.println("Activity: " + rs.getString(4));
        System.out.println("Price: " + rs.getString(5));
        System.out.println("Rating: " + rs.getString(6));
        System.out.println("Duration: " + rs.getString(7) + " minutes");
        System.out.println("");
        System.out.println("Mynd: " + rs.getString(9));
        System.out.println("");
      }
  }
  
  public static void main(String[] args) throws ClassNotFoundException
  {
    // load the sqlite-JDBC driver using the current class loader
    Class.forName("org.sqlite.JDBC");
    connection = null;
    
    try
    {
      // create a database connectionS
      connection = DriverManager.getConnection("jdbc:sqlite:database1.db");
      //Statement statement = connection.createStatement();
      //ResultSet rs = statement.executeQuery("SELECT * FROM AllTrips");
      //printResults(rs);
    }
    catch(SQLException e)
    {
      System.err.println(e.getMessage());
    }
    finally
    {
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println(e);
      }
    }
  }

public static void book(Basket myBasket) {
	try {
		connection = DriverManager.getConnection("jdbc:sqlite:database1.db");
		Statement statement = connection.createStatement();
		for(int i = 0; i < myBasket.getBookings().size(); i++) {
			int tripNumber = myBasket.getBookings().get(i).getTrip().getID();
			int bookingID = myBasket.getCustomerInfo().getBookingID();
			int seatsTaken = myBasket.getBookings().get(i).getSeats();
			String date = myBasket.getBookings().get(i).getDay();
			//INSERT INTO...
			String query = "INSERT INTO Bookings (tripNumber, BookingID, seatTaken, Date) ";
			query += "VALUES (" + tripNumber + ", " + bookingID + ", " + seatsTaken + ", " + "'" + date + "');";
			statement.executeUpdate(query);
			System.out.println(query);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static int bookedSeats(int id, String day) {
	int i = 0;
	try {
		connection = DriverManager.getConnection("jdbc:sqlite:database1.db");
		Statement statement1 = connection.createStatement();
		String query1 = "SELECT * FROM Bookings WHERE tripNumber = " + id + " AND Date = " + day + ";";
		ResultSet seats = statement1.executeQuery(query1);
		while(seats.next()) i += Integer.parseInt(seats.getString(3));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return i;
}
}
