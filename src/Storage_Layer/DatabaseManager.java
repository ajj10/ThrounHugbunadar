package Storage_Layer;

import java.sql.*;

public class DatabaseManager
{
  private static Connection connection;
  
  public static ResultSet getTrips(String location, String activity, int[] price, int rating, int duration) throws SQLException {
	  Statement statement = connection.createStatement();
	  String query = "SELECT * FROM AllTrips";
	  query += " WHERE rating >= " + rating; //þarf að vera valið rating
	  
	  if(location != null) query += " AND location = '" + location + "'";
	  if(activity != null) query += " AND activity = '" + activity + "'";
	  if(price != null) query += " AND price >= " + price[0] + " AND price <= " + price[1];
	  if(duration != -1) query += " AND duration <= " + duration;
	  
	  query += ";";
	  System.out.println(query);
	  //Breyta þannig það skili Daytrip
	  ResultSet rs = statement.executeQuery(query);
	  return rs;
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
      Statement statement = connection.createStatement();
      //ResultSet rs = statement.executeQuery("SELECT * FROM AllTrips");
      int[] price = {0, 50000};
      ResultSet rs = getTrips("Akureyri", "Mývatn", price, 3, 570);
      //ResultSet rs = getTrips("Reykjavík", null, price, 0, -1);
      printResults(rs);
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
