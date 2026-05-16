package objectville.cells;

public interface Cell{
    //Returns the character that will represent this cell
    char getSymbol();

    //Returns a Position object containing the (x, y) coordinates
    Position getPosition();

    //This indicates whether it is possible to pass through the cell.
    boolean isPassable();

    //It triggers the cell to update its internal state.
    void updateState();
}