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
		  scan.nextLine();
		  name = scan.nextLine();
		  String pNum = "";
		  do{
			  System.out.println("Enter only the numbers that compose your " +
			  "phone number: 10 DIGITS");
			  pNum = scan.next();
		  } while(pNum.length() > 10);
		  String home = "";
		  do{
			  System.out.println("What is your Hometown? 25 CHARS");
			  scan.nextLine();
			  home = scan.nextLine();
		  } while(home.length() > 25);
		  System.out.println("Assigning ID...");
		  boolean isAvailable = true;
		  int id = 0;
		  do{
			  id++;
			  isAvailable = true;
			  String check = "SELECT id FROM trainer WHERE id = " + id;
			  ResultSet rs = statement.executeQuery(check);
			  while(rs.next()){
				  isAvailable = false;
			  }
		  } while(!isAvailable);
		  String entry = new String ("INSERT INTO trainer VALUES(" + id + ",\'" + home +
		  "\',\'" + gender + "\',\'" + name + "\'," + pNum + ")");
		  statement.executeUpdate(entry);
		  trainerID = "" + id;
		  ArrayList<String> pIds = new ArrayList<>();
		  String getPokes = "SELECT name, number FROM pokemon;";
		  ResultSet rs = statement.executeQuery(getPokes);
		  while(rs.next()){
			  String pId = rs.getString("number");
			  String pName = rs.getString("name");
			  pIds.add(pId);
			  System.out.println("ID: " + pId + "\nNAME: " + pName);
			  System.out.println("----------------------------------");
		  }
		  boolean isValid = false;
		  String fPId = "";
		  do{
			  System.out.println("And finally, you may choose your first pokemon!");
			  fPId = scan.next();
			  for(int i = 0; i < pIds.size(); i++){
				  if(fPId.equals(pIds.get(i))){
					  isValid = true;
				  }
			  }
		  } while(!isValid);
		  String empty = null;
		  String giveFirstPman = new String("INSERT INTO trainer_team VALUES( \'" + 
		  trainerID + "\',\'" + fPId + "\'," + empty + "," + 
		  empty + "," + empty + "," + empty + "," + empty + ")");
		  statement.executeUpdate(giveFirstPman);
		  System.out.println("Congrats! You are in the system!");
	  } else if(ans.equalsIgnoreCase("n")){
		  ArrayList<String> ids = new ArrayList<>();
		  String getTrainers = "SELECT name, id FROM trainer;";
		  ResultSet rs = statement.executeQuery(getTrainers);
		  while(rs.next()){
			  String TId = rs.getString("id");
			  String TName = rs.getString("name");
			  ids.add(TId);
			  System.out.println("ID: " + TId + "\nNAME: " + TName);
			  System.out.println("----------------------------------");
		  }
		  boolean isValid = false;
		  do{
			System.out.println("Please choose from the list your Id.");
			trainerID = scan.next();
			for(int i = 0; i < ids.size(); i++){
				String tmp = ids.get(i);
				if(tmp.equals(trainerID)){
					isValid = true;
				}
			}
		  } while(!isValid);
	  } else{
		  
	  }
	  } while(!ans.equalsIgnoreCase("y") && !ans.equalsIgnoreCase("n"));
	  //Here we will go through and load their pokemon Ids into a list.
	  ArrayList<String> trainerPoke = new ArrayList<>();
	  String getUserP = "SELECT p1_id, p2_id, p3_id, p4_id, p5_id, " + 
	  "p6_id FROM trainer_team WHERE trainer_id = " + trainerID;
	  ResultSet rs = statement.executeQuery(getUserP);
	  trainerPoke.add(rs.getString("p1_id"));
	  trainerPoke.add(rs.getString("p2_id"));
	  trainerPoke.add(rs.getString("p3_id"));
	  trainerPoke.add(rs.getString("p4_id"));
	  trainerPoke.add(rs.getString("p5_id"));
	  trainerPoke.add(rs.getString("p6_id"));
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
