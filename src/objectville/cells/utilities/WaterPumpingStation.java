package objectville.cells.utilities;

public class WaterPumpingStation extends UtilityProvider {
    //Without parameters.
    public WaterPumpingStation() {
    }

    @Override
    public String getProvidedUtilityType() {
        return "Water"; //Provide type.
    }

    @Override
    public char getSymbol() {
        return 'W';//Utility's symbol.
    }
}