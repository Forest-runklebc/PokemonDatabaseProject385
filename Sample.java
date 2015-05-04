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
	  
	  System.out.println("Your pokemon are the following... ");
	  for(int i = 0; i < 6; i++){
		  String tmp = trainerPoke.get(i);
		  if(tmp != null){
			  String getPokemon = "SELECT name FROM pokemon WHERE number = " + tmp;
			  ResultSet rs1 = statement.executeQuery(getPokemon);
			  System.out.println((i + 1) + ". " + rs.getString("name"));
		  }
	  }
	  //Done loading and listing pokemon, begin prompting user about group analysis.
	  String ans0 = "";
	  do{
		  System.out.println("What would you like to do? (1-5)");
		  System.out.println("1. Add a Pokemon");
		  System.out.println("2. Remove a Pokemon");
		  System.out.println("3. Analyze group strengths/weaknesses");
		  System.out.println("4. Look at Gym Pokemon");
		  System.out.println("5. Exit");
		  ans0 = scan.next();
		  ResultSet rst = null;
		  String reply = "";
		  boolean works = true;
		  int marker = 0;
		  String command = "";
		  String sstmp = "";
		  String sstmp0 = "";
		  //May have difficult time deleting pokemon, and moving all of them 
		  //down a slot if not from the right most non null attribute. 
		  
		  if(ans0.equals("1")){
			  if(trainerPoke.get(5) != null){
				  System.out.println("You already have the max amount of Pokemon!");
			  } else {
			  	command = "SELECT name, number FROM pokemon;";
			  	rst = statement.executeQuery(command);
			  	while(rst.next()){
					sstmp = rs.getString("number");
			  		sstmp0 = rs.getString("name");
			  		System.out.println("ID: " + sstmp + "\nNAME: " + sstmp0);
			  		System.out.println("----------------------------------");
			  	}
			  	System.out.println("Place ID of Pokemon you want to add: ");
			  	reply = scan.next();
			  	System.out.println(reply);
			  	marker = 0;
			  	while(true){
			  		if(trainerPoke.get(marker) == null){
			  			break;
			  		}
			  		marker++;
			  	}
			  	System.out.println(marker);
			  	command = "UPDATE trainer_team SET p" + (marker+1) + "_id = " + reply
			  	+ " WHERE trainer_id = " + trainerID;
			  	System.out.println(command);
			  	try{
			  		statement.executeUpdate(command);
			  		trainerPoke.set(marker, reply);
			  	} catch(Exception e){
			  		System.out.println("Invalid ID");
			  	}
			  }
			  
		  } else if(ans0.equals("2")){
			   for(int i = 0; i < 6; i++){
					String tmp = trainerPoke.get(i);
					if(tmp != null){
						String getPokemon = "SELECT name FROM pokemon WHERE number = " + tmp;
						rst = statement.executeQuery(getPokemon);
						System.out.println((i + 1) + ". " + rs.getString("name"));
						rst.close();
					}
				}
				do{
					System.out.println("Which do you want to remove?");
					reply = scan.next();
					works = false;
					if(reply.equals("1") || reply.equals("2") || reply.equals("3") ||
						reply.equals("4") || reply.equals("5") || reply.equals("6")){
							works = true;
							marker = Integer.parseInt(reply);
						}
				} while(!works);
				if(trainerPoke.get(marker-1) == null){
					System.out.println("There is no pokemon at this location.");
				} else {
					System.out.println("Removing Pokemon...");
					command = "UPDATE trainer_team SET p" + marker + "_id = NULL WHERE " +
					"trainer_id = " + trainerID;
					statement.executeUpdate(command);
					trainerPoke.set(marker-1, null);
					int stmp = marker-1;
					//This part checks if we need to move the values down in AL and SQL
					if(stmp < 5){
						while(true){
							if(stmp == 5){
								break;
							} else if(trainerPoke.get(stmp+1) == null){
								break;
							} else{
								trainerPoke.set(stmp, trainerPoke.get(stmp+1));
								trainerPoke.set((stmp + 1), null);
							}
							stmp++;
						}
						//First if case tests to see if it needs to delete or not.
						//Second updates SQL with array list.
						for(int i = 0; i < 6; i++){
							if(trainerPoke.get(i) == null){
								command = "SELECT p" + (i+1) + "_id FROM trainer_team " + 
								"WHERE trainer_id = " + trainerID;
								rst = statement.executeQuery(command);
								sstmp0 = "p" + (i+1) + "_id";
								sstmp = rst.getString(sstmp0);
								rst.close();
								if(sstmp != null){
									command = "UPDATE trainer_team SET p" + (i+1) +
									"_id = NULL WHERE trainer_id = " + trainerID;
									statement.executeUpdate(command);
								}
							} else{
								command = "UPDATE trainer_team SET p" + (i+1) + "_id = " +
								trainerPoke.get(i) + " WHERE trainer_id = " + trainerID;
								statement.executeUpdate(command);
							}
						}
					}
				
				}
		  } else if(ans0.equals("3")){
			  //ResultSet rst = null;
			  String names = "";
			  String str = "";
			  String weakn = "";
			  for(int i = 0; i < trainerPoke.size(); i++){
				if(trainerPoke.get(i) != null){
					String q1 = "SELECT name, strength, weakness FROM pokemon as P " + 
						"JOIN effectiveness as E on P.type = E.base_type WHERE number = " + 
						trainerPoke.get(i) + "";
					rst = statement.executeQuery(q1);
					names = rst.getString("name");
					str = rst.getString("strength");
					weakn = rst.getString("weakness");
					System.out.println((i + 1) + ". " + names + " is strong against " + 
					str + " and weak against " + weakn + ".");
				rst.close();
				}
			  }
		  } else if(ans0.equals("4")){
			  
		  }
	  } while(!ans0.equals("5"));
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
