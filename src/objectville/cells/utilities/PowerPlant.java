package objectville.cells.utilities;

import objectville.cells.Position;

public class PowerPlant extends UtilityProvider {
    //Without Parameters.
    public PowerPlant(Position position) {
        super(position);
    }

    @Override
    public String getProvidedUtilityType() {
        return "Electricity";//Provide type.
    }

    @Override
    public char getSymbol() {
        return 'P';
    }//Utility's symbol.
}