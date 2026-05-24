package objectville.cells.zones;

public class Industrial extends Zone {

    @Override
    public int calculateM() {
        // Will look at received Electricity and Water
        return 0;
    }

    @Override
    public void calculateOutput() {
        // Will generate Goods based on level and 'm'
    }

    @Override
    public char getSymbol() { // I for industrial
        return 'I';
    }
}