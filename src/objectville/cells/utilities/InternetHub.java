package objectville.cells.utilities;

import objectville.cells.Position;

public class InternetHub extends UtilityProvider {
    //Without parameters.
    public InternetHub(Position position) {
        super(position);
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
