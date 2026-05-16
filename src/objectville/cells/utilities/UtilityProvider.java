package objectville.cells.utilities;

import objectville.cells.Cell;
import objectville.cells.Position;

public abstract class UtilityProvider implements Cell {
    //The game rule capacity is 100. So, I am locked it.
    protected final int capacity = 100;

    //Capacity is constant value so we don't need fill the Constructor.
    public UtilityProvider() {
    }

    //This method will show us how we are going to use the BFS.
    public int getCapacity() {
        return capacity;
    }

    //This method shows us which type of utilities.
    public abstract String getProvidedUtilityType();

    //This method will be show us where is the utility.(Stub)
    @Override
    public Position getPosition() {
        return null;
    }
    //This method shows us is it passable or not.(Stub)
    @Override
    public boolean isPassable() {
        return false;
    }
    //This method will show us the changes.(Stub)
    @Override
    public void updateState() {

    }
}
