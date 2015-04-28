import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Sample
{
  public static void main(String[] args) throws ClassNotFoundException
  {
    // load the sqlite-JDBC driver using the current class loader
    Class.forName("org.sqlite.JDBC");
	Scanner scan = new Scanner(System.in);
	System.out.println("Welcome to the Pokedex!\nWe have information about:\nPokemon\nPokemon vs Pokemon type effectiveness\nTrainers\nTrainer Teams\nGyms");
	

    Connection connection = null;
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:Pokemon1.1.sql");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.
	
		System.out.println("Would you like to see a list of all Pokemon? (Yes/No)");
		String yesNo = scan.next();
		if(yesNo.toLowerCase().equals("yes")) {
      ResultSet rs = statement.executeQuery("SELECT name FROM pokemon");
		int count = 0;
	  while(rs.next())
      {
        // read the result set
        System.out.println("Pokemon" + count + " is " + rs.getString("name"));
      }
	  }

    }
    catch(SQLException e)
    {
      // if the error message is "out of memory", 
      // it probably means no database file is found
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
