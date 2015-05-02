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
	  String trainerID = "";
	  String ans = "";
	  do{
      System.out.println("Welcome to Pokemon Squad Analyzer! Are you new here?(y/n)");
      ans = scan.next();
      if(ans.equalsIgnoreCase("y")){
		  String gender = "";
		  do{
			  System.out.println("Are you a boy or a girl?");
			  gender = scan.next();
		  } while(!gender.equalsIgnoreCase("boy") && !gender.equalsIgnoreCase("girl"));
		  if(gender.equalsIgnoreCase("boy")){
			  gender = "male";
		  } else{
			  gender = "female";
		  }
		  String name = "";
		  System.out.println("What it your name? MAX 25 CHARS");
		  name = scan.nextLine();
		  name = scan.nextLine();
		  String pNum = "";
		  do{
			  System.out.println("Enter only the numbers that compose your " +
			  "phone number: 10 DIGITS");
			  pNum = scan.next();
		  } while(pNum.length() > 10);
	  }
	  } while(!ans.equalsIgnoreCase("y") && !ans.equalsIgnoreCase("n"));
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
