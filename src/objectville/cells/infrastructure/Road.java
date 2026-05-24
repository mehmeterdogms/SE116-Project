package objectville.cells.infrastructure;

import objectville.cells.Cell;
import objectville.cells.Position;

public class Road implements Cell {
    private final Position position;

    public Road(Position position) {
        this.position = position;
    }


    @Override
    public boolean isPassable() { // Roads are passable
        return true;
    }

    @Override
    public char getSymbol() { // R for road
        return 'R';
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void updateState(){ // Roads shouldn't change during ticks

    }
}
