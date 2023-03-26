/**
* This class creates the vehicle object and its attributes for the Need for Java package.
* @author Parsa Mousavi
* @version 1.0.0
*/
public class Vehicle
{
    private String Make;
    private int boostSpeed;
    private int damageSustainability;
    private int maxFuel;
    private int availableFuel;

    /**
    * This default constructor initialises the fields with default values.
    */
    public Vehicle()
    {
        Make = "unknown";
        boostSpeed = 0;
        damageSustainability = 0;
        maxFuel = 0;
        availableFuel = 0;
    }

    /**
    * This non-default constructor initialises the fields with user given values.
    * @param Make String the type of vehicle.
    * @param boostSpeed Int specifies boostSpeed value.
    * @param damageSustainability Int specifies damageSustainability value.
    * @param maxFuel Int specifies maxFuel value.
    */
    public Vehicle(String Make, int boostSpeed,int maxFuel, int damageSustainability)
    {
        this.Make = Make;
        this.boostSpeed = boostSpeed;
        this.damageSustainability = damageSustainability;
        this.maxFuel = maxFuel;
        availableFuel = maxFuel;
    }

    /**
    * This method adjusts the vehicle Fuel based on game difficulty.
    * @param Difficulty used to determine the % of fuel for this game difficulty.
    * @param playerVehicle provides the player vehicle on which the fuel is adjusted.
    */
    public void adjustFuel(String Difficulty, Vehicle playerVehicle)
    {
        if (Difficulty.equals("Moderate"))
        {
            playerVehicle.setAvailableFuel((int)(playerVehicle.getMaxFuel() * 0.8));
        }  
        else if (Difficulty.equals("Hard"))
        {
            playerVehicle.setAvailableFuel((int)(playerVehicle.getMaxFuel() * 0.5));
        }    
    }

    /**
    * This is the Accessor method for availableFuel.
    * @return returns integer value of availableFuel.
    */
    public int getAvailableFuel()
    {
        return availableFuel;
    }

    /**
    * This is the Accessor method for boostSpeed
    * @return returns integer value of boostSpeed
    */
    public int getBoostSpeed()
    {
        return boostSpeed;
    }

    /**
    * This is the Accessor method for damageSustainability
    * @return returns integer value of damageSustainability
    */
    public int getDamageSustainability()
    {
        return damageSustainability;
    }

    /**
    * This is the Accessor method for Make of the vehicle
    * @return returns String value of Make
    */
    public String getMake()
    {
        return Make;
    }

    /**
    * This is the Accessor method for maxFuel
    * @return returns integer value of maxFuel
    */
    public int getMaxFuel()
    {
        return maxFuel;
    }

    /**
    * This Mutator method changes the value of availableFuel.
    * @param Fuel Int specifies the amount of available fuel.
    */
    public void setAvailableFuel(int Fuel)
    {
        availableFuel = Fuel;
    }

    /**
    * This Mutator method changes the value of boostSpeed.
    * @param newBoostSpeed Int specifies the new value of boostSpeed.
    */
    public void setBoostSpeed(int newBoostSpeed)
    {
        boostSpeed = newBoostSpeed;
    }

    /**
    * This Mutator method changes the value of damageSustainability.
    * @param newDamageSustainability Int specifies the new value of damageSustainability.
    */
    public void setDamageSustainability(int newDamageSustainability)
    {
        damageSustainability = newDamageSustainability;
    }

    /**
    * This Mutator method changes the make of the vehicle.
    * @param newMake String specifies the new Make of the vehicle.
    */
    public void setMake(String newMake)
    {
        Make = newMake;
    }

    /**
    * This Mutator method changes the value of maxFuel.
    * @param newMaxFuel Int specifies the new value of maxFuel.
    */
    public void setMaxFuel(int newMaxFuel)
    {
        maxFuel = newMaxFuel;
    }

    /**
    * Prints out the values of the fields for the user.
    */
    public void vehicleDisplay()
    {
        System.out.print("Make = " + Make + ", ");
        System.out.print("Boost Speed = " + boostSpeed + ", ");
        System.out.print("Fuel = " + availableFuel + ", ");
        System.out.print("Damage Sustainability = " + damageSustainability + ".\n");
    }
}
