package objectville.cells;

public interface Cell{
    //Returns the character that will represent this cell
    char getSymbol();

    //Returns a Position object in terms of  (x, y) coordinates
    Position getPosition();

    //Decides if the cell is passable or not
    boolean isPassable();

    //It triggers the cell to update its internal state.
    void updateState();
}