import java.util.Arrays;

/**
* This class creates an array that represents a Lane.
* @author Parsa Mousavi
* @version 1.0.0
*/
public class Lane
{
    private String[] lane;

    /**
    * This default constructor initialises the lane field with default values.
    */
    public Lane()
    {
        lane = new String[0];
    }

    /**
    * This non-default  constructor initialises the lane field with given length value.
    * @param lengthOfLane specifies the length of lane array
    */
    public Lane(int lengthOfLane)
    {
        lane = new String[lengthOfLane];
        drawRoadLines(lengthOfLane);
    }

    /**
    * This display method prints out the lane field in a string format.
    */
    public void displayLane()
    {
        System.out.println(Arrays.toString(lane));
    }

    /**
    * This method adds the road lines to the lane array.
    * @param lengthOfLane used to draw appropriate number of lines.
    */
    private void drawRoadLines(int lengthOfLane)
    {
        for (int i = 0; i < lengthOfLane; i++)
        {
            lane[i] = "-";
        }
    }

    /**
    * This is the Accessor method for lane
    * @return returns the String[] value of lane.
    */
    public String[] getLane()
    {
        return lane;
    }

    /**
    * This Mutator method changes the value of lane.
    * @param lengthOfLane initialises the new value of lane.
    */
    public void setLane(int lengthOfLane)
    {
        lane = new String[lengthOfLane];
    }
}
