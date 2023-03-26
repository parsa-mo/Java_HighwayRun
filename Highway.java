import java.util.Arrays;

/**
* This class creates a Highway using Lane class objects.
* @author Parsa Mousavi
* @version 1.0.0
*/
public class Highway
{
    private Lane lane1;
    private Lane lane2;
    private Lane lane3;
    private String[] playerLane;

    /**
    * This default constructor initialises the fields with default values.
    */
    public Highway()
    {
        lane1 = new Lane();
        lane2 = new Lane();
        lane3 = new Lane();
    }

    /**
    * This non-default constructor initialises the fields based on inputs.
    * @param Difficulty determines length of highway.
    */
    public Highway(String Difficulty)
    {
        int maxObstacles = 0;
        if (Difficulty.equals("Easy"))
        {
            int Length = (int)((Math.random() * 6) + 10);
            maxObstacles = 12;
            lane1 = new Lane(Length);
            lane2 = new Lane(Length);
            lane3 = new Lane(Length);
        }
        else if (Difficulty.equals("Moderate"))
        {
            int Length = (int)((Math.random() * 16) + 15);
            maxObstacles = 24;
            lane1 = new Lane(Length);
            lane2 = new Lane(Length);
            lane3 = new Lane(Length);
        }  
        else if (Difficulty.equals("Hard"))
        {
            int Length = (int)((Math.random() * 21) + 30);
            maxObstacles = 45;
            lane1 = new Lane(Length);
            lane2 = new Lane(Length);
            lane3 = new Lane(Length);
        }    
        obstacleGenerator(maxObstacles);

        int randomLane = (int)((Math.random() * 3) + 1);
        setPlayerLane(randomLane);
        getPlayerLane()[0] = "@";
    } 

    /**
    * This method prints the highway lane objects in string format
    */
    public void displayHighway()
    {
        System.out.println(Arrays.toString(lane1.getLane()));
        System.out.println(Arrays.toString(lane2.getLane()));
        System.out.println(Arrays.toString(lane3.getLane()));
    }

    /**
    * This method prints the highway lane objects in 10 sections at a time in string format.
    * @param startingIndex indicates the starting position of the 10 sections being displayed.
    */
    public void liveHighwayDisplay(int startingIndex)
    {
        int remainingSections = (lane1.getLane().length - 1) - startingIndex;

        if (remainingSections < 9)
        {
            for (int j = (lane1.getLane().length - 10); j < lane1.getLane().length; j++)
            {
                System.out.print(lane1.getLane()[j] + "  ");
            }
            System.out.println();

            for (int j = (lane1.getLane().length - 10); j < lane1.getLane().length; j++)
            {
                System.out.print(lane2.getLane()[j] + "  ");
            }
            System.out.println();

            for (int j = (lane1.getLane().length - 10); j < lane1.getLane().length; j++)
            {
                System.out.print(lane3.getLane()[j] + "  ");
            }
            System.out.println();
        }
        else
        {
            for (int i = startingIndex; i <= (startingIndex + 9); i++)
            {
                System.out.print(lane1.getLane()[i] + "  ");
            }
            System.out.println();

            for (int i = startingIndex; i <= (startingIndex + 9); i++)
            {
                System.out.print(lane2.getLane()[i] + "  ");
            }
            System.out.println();

            for (int i = startingIndex; i <= (startingIndex + 9); i++)
            {
                System.out.print(lane3.getLane()[i] + "  ");
            }
            System.out.println();
        }
    }
    
    /**
    * This method adds obstacles to the highway lanes.
    * @param maxNumOfObstacles indicates the maximum number of obstacles that will be added to the highway.
    */
    public void obstacleGenerator(int maxNumOfObstacles)
    {
        int obstacleCount = 0;
        int Index = 3;
        int maxObstacles = maxNumOfObstacles;
        int length = lane1.getLane().length;
        boolean generating = true;

        while (generating)
        {
            if (obstacleCount == maxObstacles || Index == length)
            {
                generating = false;
            }
            else if (((int)(Math.random() * 3) + 1) == 1) 
            {
                lane1.getLane()[Index] = obstacleSelecter();
                obstacleCount ++;
                Index ++;
            }
            else if (((int)(Math.random() * 3) + 1) == 1)
            {
                lane2.getLane()[Index] = obstacleSelecter();
                obstacleCount++;
                Index ++;
            }
            else if (((int)(Math.random() * 3) + 1) == 1)
            {
                lane3.getLane()[Index] = obstacleSelecter();
                obstacleCount++;
                Index ++;
            }
        }
    } 

    /**
    * This method generates a specific type of obstacle.
    * @return returns an obstacle.
    */
    public String obstacleSelecter()
    {
        String[] obstacles = {"F", "F", "F", "B", "B", "B", "B", "S", "S", "O"};

        int randomNumber = (int)(Math.random() * 10);

        return obstacles[randomNumber];
    }

    /**
    * This is the Accessor method for lane1 field.
    * @return returns lane1 as String array.
    */
    public String[] getLane1()
    {
        return lane1.getLane();
    }

    /**
    * This is the Accessor method for lane2 field.
    * @return returns lane2 as String array.
    */
    public String[] getLane2()
    {
        return lane2.getLane();
    }
    
    /**
    * This is the Accessor method for lane3 field.
    * @return returns lane3 as String array.
    */
    public String[] getLane3()
    {
        return lane3.getLane();
    }

    /**
    * This is the Accessor method for playerLane field.
    * @return returns player lane as String Array.
    */
    public String[] getPlayerLane()
    {
        return playerLane;
    }

    /**
    * This method changes length of lane1.
    * @param Length sets the new length of the lane.
    */
    public void setLane1(int Length)
    {
        lane1 = new Lane(Length);
    }

    /**
    * This method changes length of lane2.
    * @param Length sets the new length of the lane.
    */
    public void setLane2(int Length)
    {
        lane2 = new Lane(Length);
    }

    /**
    * This method changes length of lane3.
    * @param Length sets the new length of the lane.
    */
    public void setLane3(int Length)
    {
        lane3 = new Lane(Length);
    }

    /**
    * This method sets the lane of the player icon "@".
    * @param laneNumber indicates which lane the icon will be in.
    */
    public void setPlayerLane(int laneNumber)
    {
        if (laneNumber == 1)
        {
            playerLane = lane1.getLane();
        }
        else if (laneNumber == 2)
        {
            playerLane = lane2.getLane();
        }
        else if (laneNumber == 3)
        {
            playerLane = lane3.getLane();
        }
    }

}
