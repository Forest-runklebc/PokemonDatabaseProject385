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
      connection = DriverManager.getConnection("jdbc:sqlite:PokeData.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.
	
<<<<<<< HEAD
		System.out.println("1. Count how many types are super effective against Fire");
		System.out.println("2. Update trainer team 1 with Ivysaur and Venusaur");
		System.out.println("3. Req 3...");
		System.out.println("4. Show all Pokemon.");
		String ans = scan.next();
		if(ans.equals("4")) {
      ResultSet rs = statement.executeQuery("SELECT name FROM pokemon");
=======
		System.out.println("Would you like to see a list of all Pokemon? (Yes/No)");
		String yesNo = scan.next();
		if(yesNo.toLowerCase().equals("yes")) {
      ResultSet rs = statement.executeQuery("select base_type,strength from effectiveness where strength like '%fire%'");
>>>>>>> b59c83c7351c639f0fc163af606e03337c7d5732
		int count = 0;
		
	  while(rs.next())
      {
        // read the result set
<<<<<<< HEAD
        System.out.println("Pokemon " + (count+1) + " is " + rs.getString("name"));
=======
        System.out.println("BaseType = " + rs.getString("base_type") + " - Stengths are " + rs.getString("strength"));
>>>>>>> b59c83c7351c639f0fc163af606e03337c7d5732
		count++;
      }
	  }
	  
<<<<<<< HEAD
	  else if(ans.equals("3")){
		  
	  }
	  
	  else if(ans.equals("2")){
		  ResultSet rs = statement.executeQuery("UPDATE trainer_team SET p1_id = 002," +
		  " p2_id = 002, p3_id = 002, p4_id = 002, p5_id = 002, p6_id = 003 WHERE " + 
		  "trainer_id = 1;");
	  }
	  
	  else if(ans.equals("1")){
		  ResultSet rs = statement.executeQuery("select count(*) from " +
		  "effectiveness where strength like \'%fire%\';");
		  System.out.println(rs.getString("count(*)"));
	  }
	  
	  else{
		  System.out.println("Not a valid option for prototype. Shutting down.");
	  }
=======
>>>>>>> b59c83c7351c639f0fc163af606e03337c7d5732

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
