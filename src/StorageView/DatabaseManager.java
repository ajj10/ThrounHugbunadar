package StorageView;

import java.sql.*;

public class DatabaseManager
{
  public static void main(String[] args) throws ClassNotFoundException
  {
    // load the sqlite-JDBC driver using the current class loader
    Class.forName("org.sqlite.JDBC");
    Connection connection = null;
    
    try
    {
      // create a database connectionS
      connection = DriverManager.getConnection("jdbc:sqlite:database1.db");
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery("SELECT * FROM AllTrips");
      while(rs.next())
      {
        // read the result set
        System.out.println("Trip id: " + rs.getString(1));
        System.out.println("Trip Name:" + rs.getString(2));
        System.out.println("Departure Location:" + rs.getString(3)); 
        System.out.println("Activity:" + rs.getString(4));
        System.out.println("Price:" + rs.getString(5));
        System.out.println("Rating::" + rs.getString(6));
        System.out.println("Duration:" + rs.getString(7) + " minutes");
        System.out.println("");
        System.out.println("Mynd:" + rs.getString(9));
      }
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
