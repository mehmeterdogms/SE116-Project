package objectville.cells.utilities;

public class InternetHub extends UtilityProvider {
    //Without parameters.
    public InternetHub() {
    }

    @Override
    public String getProvidedUtilityType() {
        return "Internet";//Provide type.
    }

    @Override
    public char getSymbol() {
        return 'T';//Utility's symbol.
    }
}
