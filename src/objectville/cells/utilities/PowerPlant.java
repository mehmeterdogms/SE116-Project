package objectville.cells.utilities;

public class PowerPlant extends UtilityProvider {
    //Without Parameters.
    public PowerPlant() {
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