package Storage_Layer;

import java.sql.*;
import Model_Layer.Daytrip;
import java.util.ArrayList;

public class DatabaseManager
{
  private static Connection connection;
  
  public static ArrayList<Daytrip> getTrips(String location, String activity, int[] price, int rating, int duration) throws SQLException {
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
	  int tripSeatsAvailable = 10; //vantar í gagnagrunn
	  //if(rs.getString(?) != null) tripSeatsAvailable = Integer.parseInt(rs.getString(?));
	  
	  Daytrip trips = new Daytrip(tripName, tripLocation, tripDuration, tripAverageRating, tripPrice, tripActivity, tripSeatsAvailable, tripDescription);
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
}
