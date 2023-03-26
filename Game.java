import java.util.*;
import java.io.FileReader;
import java.io.FileWriter;

/**
* This class runs the game and it's controls.
* @author Parsa Mousavi
* @version 1.0.0
*/
public class Game
{
    /**
    * Default constructor creates a Game class object.
    */
    private Game()
    {}

    /**
    * This method takes input  for and validates player name.
    * @return returns player name input.
    */
    private String acceptPlayerName()
    {
        boolean nameSubmitted = false;
        Scanner scanner = new Scanner(System.in);
        String name = "unknown";
        System.out.println("What is your name?");

        while(!nameSubmitted)
        {
            name = scanner.nextLine();   

            if (name.length() < 3 || name.length() > 12)
            {
                System.out.println("Player name must be between 3-12 characters long!");
            }
            else
            {
                nameSubmitted = true;
            }
        }

        return name;
    } 

    /**
    * This method displays all the games visuals.
    * @param highway provides the game highway to be displayed.
    * @param playerVehicle provides the vehicle to be displayed.
    * @param currentIndex provides the current index at which the highway is displayed at.
    */
    private void displayGameOptions(Highway highway, Vehicle playerVehicle, int currentIndex)
    {
        System.out.println();
        playerVehicle.vehicleDisplay();
        System.out.println("(@) Player, (B) Roadblock, (S) Tyre Spikes, (O) Open manhole, (F) Fuel");
        highway.liveHighwayDisplay(currentIndex);
        System.out.println();
        System.out.println("Select 1 to move forward.");
        System.out.println("Select 2 to Swerve up.");
        System.out.println("Select 3 to Swerve down.");
        System.out.println("Select 4 to Boost Forward.");
        System.out.println();
    }

    /**
    * This method check whether the player has hit an obstacle on the highway.
    * @param highway provides the highway on which collision is checked.
    * @param playerVehicle provides the playerVehicle that is being checked for collision.
    * @param indexOfCollision provides the section of highway being checked for collision.
    */
    private void collisionCheck(Highway highway, Vehicle playerVehicle, int indexOfCollision)
    {
        String obstacle = highway.getPlayerLane()[indexOfCollision + 1];
        int damage = 0;

        switch (obstacle)
        {
            case "F":
                int Fuel = (playerVehicle.getAvailableFuel() + 10);
                if (Fuel > playerVehicle.getMaxFuel())
                {
                    Fuel = playerVehicle.getMaxFuel();
                }
                playerVehicle.setAvailableFuel(Fuel);
                break;
            case "B":
                damage = playerVehicle.getDamageSustainability();
                playerVehicle.setDamageSustainability(damage - 20);
                break;
            case "S":
                damage = playerVehicle.getDamageSustainability();
                playerVehicle.setDamageSustainability(damage - 45);
                break;
            case "O":
                damage = playerVehicle.getDamageSustainability();
                playerVehicle.setDamageSustainability(damage - 60);
                break;
        }
    }

    /**
    * This method starts the live escape or the players drive through the highway.
    * @param highway, provides the game highway.
    * @param playerVehicle provides the player escape vehicle.
    */
    private void highwayRun(Highway highway, Vehicle playerVehicle)
    {
        Scanner scanner = new Scanner(System.in);
        int highwayLength = highway.getLane1().length;
        boolean gameActive = true;
        int Index = 0;

        while (gameActive)
        {
            int Fuel = playerVehicle.getAvailableFuel();
            int Damage = playerVehicle.getDamageSustainability();

            try
            {
                if (Index >= highwayLength - 1)
                {
                    gameActive = false;
                    highway.liveHighwayDisplay(Index);
                    System.out.println("Congratulation! You have escaped. 🥇");
                    recordDistance(Index, true);
                    break;
                }
                else if (Fuel <= 0 || Damage <= 0)
                {
                    if (Fuel <= 0)
                    {
                        System.out.println("You ran out of fuel!");
                        System.out.println("GAME OVER!");
                        recordDistance(Index, false);
                        gameActive = false;
                        break;
                    }
                    else
                    {
                        System.out.println("Your vehicle has sustained too much damage");
                        System.out.println("GAME OVER!");
                        recordDistance(Index, false);
                        gameActive = false;
                        break;
                    }
                }

                displayGameOptions(highway, playerVehicle, Index);
                int option = Integer.parseInt(scanner.nextLine());

                if (option != 1 && option != 2 && option != 3 && option != 4)
                {
                    System.out.println("Error. Please Enter a number between 1-4");
                    continue;
                }

                switch (option)
                {
                    case 1: //Move Forward
                        highway.getPlayerLane()[Index] = "-";
                        playerVehicle.setAvailableFuel(Fuel - 1);
                        collisionCheck(highway, playerVehicle, Index);
                        Index++;
                        highway.getPlayerLane()[Index] = "@";
                        break;
                    
                    case 2: //Swerve Up
                        if ((highway.getPlayerLane()).equals(highway.getLane1()))
                        {
                            System.out.println("You are already in the uppermost lane, you cannot swerve up.");
                        }
                        else if ((highway.getPlayerLane()).equals(highway.getLane3()))
                        {
                            highway.getPlayerLane()[Index] = "-"; 
                            highway.setPlayerLane(2);
                            playerVehicle.setAvailableFuel(Fuel - 2);
                            collisionCheck(highway, playerVehicle, Index);
                            Index++;
                            highway.getPlayerLane()[Index] = "@";
                        }
                        else if ((highway.getPlayerLane()).equals(highway.getLane2()))
                        {
                            highway.getPlayerLane()[Index] = "-"; 
                            highway.setPlayerLane(1);
                            playerVehicle.setAvailableFuel(Fuel - 2);
                            collisionCheck(highway, playerVehicle, Index);
                            Index++;
                            highway.getPlayerLane()[Index] = "@";
                        }
                        break;
                    
                    case 3: //Swerve Down
                        if ((highway.getPlayerLane()).equals(highway.getLane3()))
                        {
                            System.out.println("You are already in the bottom lane, you cannot swerve down.");
                        } 
                        else if ((highway.getPlayerLane()).equals(highway.getLane1()))
                        {
                            highway.getPlayerLane()[Index] = "-"; 
                            highway.setPlayerLane(2);
                            playerVehicle.setAvailableFuel(Fuel - 2);
                            collisionCheck(highway, playerVehicle, Index);
                            Index++;
                            highway.getPlayerLane()[Index] = "@";
                        }
                        else if ((highway.getPlayerLane()).equals(highway.getLane2()))
                        {
                            highway.getPlayerLane()[Index] = "-"; 
                            highway.setPlayerLane(3);
                            playerVehicle.setAvailableFuel(Fuel - 2);
                            collisionCheck(highway, playerVehicle, Index);
                            Index++;
                            highway.getPlayerLane()[Index] = "@";
                        }
                        break;
                    
                    case 4: //Boost 
                        int boost = playerVehicle.getBoostSpeed();
                        for (int i = boost; i > 0; i--)
                        {
                            Fuel = playerVehicle.getAvailableFuel();
                            highway.getPlayerLane()[Index] = "-"; 
                            if (Index >= highwayLength - 1)
                            {
                                highway.getPlayerLane()[highwayLength - 1] = "@";
                                break;
                            }
                            else
                            {
                                collisionCheck(highway, playerVehicle, Index);
                                Index++;
                                highway.getPlayerLane()[Index] = "@";
                            }
                        }
                        playerVehicle.setAvailableFuel(Fuel - (boost * 3));
                }
            }
            catch (Exception e)
            {
                System.out.println("Error! Please try again by typing a number between 1-4.");
            }
        }
    }

    /**
    * This is the main method that begins the execution of the program.
    * @param args indicate the codes that will be executed.
    */
    public static void main(String[] args)
    {
        Game game = new Game();
        String playerName = game.acceptPlayerName();
        game.welcomeDisplay(playerName);

        Vehicle[] vehicles = game.readVehicleOptions();
        String gameDifficulty = game.selectDifficulty();

        Vehicle playerVehicle = vehicles[game.selectVehicle(vehicles)];
        playerVehicle.adjustFuel(gameDifficulty, playerVehicle);
        Highway highway = new Highway(gameDifficulty);
        
        game.highwayRun(highway, playerVehicle);
    }

    /**
    * This method reads and stores Vehicle options from the text file containing vehicles list.
    * @return Returns all the Vehicle options as a Vehicle object array.
    */
    private Vehicle[] readVehicleOptions()
    {
        int counter = 0;
        Vehicle[] vehicles = new Vehicle[3];
        boolean fileRead = false;
        while (!fileRead)
            try
            {
                Scanner console = new Scanner(System.in);
                FileReader reader = new FileReader("Vehicles.txt");
                try
                {
                    Scanner fileInput = new Scanner(reader);
                    while (fileInput.hasNextLine())
                    {
                        String[] lineContents = fileInput.nextLine().split(",");
                        String make = lineContents[0];
                        int boost = Integer.parseInt(lineContents[1]);
                        int fuel = Integer.parseInt(lineContents[2]);
                        int damage = Integer.parseInt(lineContents[3]); 
                        Vehicle newVehicle = new Vehicle(make, boost, fuel, damage);
                        vehicles[counter] = newVehicle;
                        counter ++;
                    }
                }
                finally
                {
                    try
                    {
                        fileRead = true;
                        reader.close();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error! Could not read the file.");
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println("Error! Could not read the file.");
            }

        return vehicles;
    }

    /**
    * This method records the distance covered at the end of the highway run.
    * @param index provides the final position of player on highway.
    * @param escaped clarifies whether it was a failed or successful escape.
    */
    private void recordDistance(int index, boolean escaped)
    {
        Scanner scanner = new Scanner(System.in);
        try
        {
            FileWriter writer = new FileWriter("output.txt");
            try
            {
                if (escaped)
                {
                    String outcome = ("Successful Escape, Distance: " + Integer.toString(index) + " sections. \n");
                    writer.append(outcome);
                }
                else
                {
                    String outcome = ("Failed Escape, Distance: " + Integer.toString(index) + " sections. \n");
                    writer.append(outcome);
                }
            }
            finally
            {
                try
                {
                    writer.close();
                }
                catch (Exception e)
                {
                    System.out.println("Error closing file.");
                }
            }
        } 
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    /**
    * This method takes input for game difficulty.
    * @return returns the selected difficulty in string format.
    */
    private String selectDifficulty()
    {
        Scanner scanner = new Scanner(System.in);
        boolean difficultySet = false;
        String Difficulty = "unknown";

        System.out.println("Select your game difficulty");
        System.out.println("Select 1 for Easy. This is a short highway with maximum fuel.");
        System.out.println("Select 2 for Moderate. This course is a bit longer, with more obstacles and 80% fuel.");
        System.out.println("Select 3 for Hard. Longest Course, most obstacles and 50% fuel.");

        do
        {
            try
            {
                int input = Integer.parseInt(scanner.nextLine());
                if (input == 1 || input == 2 || input == 3)
                {
                    if (input == 1)
                    {
                        System.out.println("Diffuculty: Easy. You are taking it Easy!");
                        Difficulty = "Easy";
                    }
                    else if (input == 2)
                    {
                        System.out.println("Difficulty: Moderate. You like a bit of a challenge.");
                        Difficulty = "Moderate";
                    } 
                    else if (input == 3)
                    {
                        System.out.println("Difficulty: Hard. You are a thrill seeker!!");
                        Difficulty = "Hard";
                    }
                    difficultySet = true;
                }
                else
                {
                    System.out.println("Please Enter a number between 1 and 3");
                }                
            }
            catch (Exception e)
            {
                System.out.println("Error, Please select again.");
                System.out.println("Enter a number between 1 to 3");
            }
        } while (!difficultySet);

        System.out.println("");
        return Difficulty;    
    }

    /**
    * This method takes input to select a vehicle from the list of vehicle.
    * @param vehicles provides the list of vehicles to be selected from.
    * @return returns the selected vehicles number from the list of vehicles.
    */
    private int selectVehicle(Vehicle[] vehicles)
    {
        Scanner scanner = new Scanner(System.in);
        int vehicleChoice = 0;
        boolean vehicleSelected = false;

        for (int i = 0; i < vehicles.length; i++)
        {
            System.out.println("Select your escape vehicle: ");
            System.out.print("Select " + (i + 1) + " for "); 
            vehicles[i].vehicleDisplay();
        }

        do
        {
            try
            {
                int input = Integer.parseInt(scanner.nextLine());
                if (input > 0 && input < vehicles.length + 1)
                {
                    System.out.println("You have chosen the " + vehicles[input - 1].getMake());
                    vehicleSelected = true;
                    vehicleChoice = input - 1;
                }
                else
                {
                    System.out.println("Please Enter a number between 1 and 3");
                }                
            }
            catch (Exception e)
            {
                System.out.println("Error, Please select again.");
                System.out.println("Enter a number between 1 to 3");
            }
        } while (!vehicleSelected);

        return vehicleChoice;    
    }

    /**
    * This method displays the games welcome message and instructions.
    * @param playerName provides the player's name entered in the game.
    */
    private void welcomeDisplay(String playerName)
    {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~💎~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Hello " + playerName + ". Welcome to Need for Java 1!");
        System.out.println("-- The objective is to get to End of the highway without getting caught.");
        System.out.println("-- The objective is to get to End of the highway without getting caught.");
        System.out.println("-- Avoid obstacles and pick up fuel on the highway.");
        System.out.println("-- Obstacles: Roadblock = 20 Damage, Tyre Spikes = 45 Damage, Open manhole = 60 Damage, Fuel = +10 Fuel");
        System.out.println("-- If your vehicle Sustains too much damage or runs out of fuel you lose.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
    }
}
