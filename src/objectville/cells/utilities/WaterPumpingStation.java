package objectville.cells.utilities;

import objectville.cells.Position;

public class WaterPumpingStation extends UtilityProvider {
    //Without parameters.
    public WaterPumpingStation(Position position) {
        super(position);
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