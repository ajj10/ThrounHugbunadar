package StorageView;

import java.sql.*;
import org.sqlite.*;

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
        System.out.println("Ferd nr: " + rs.getString(1));
        System.out.println("Nafn:" + rs.getString(2));
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
