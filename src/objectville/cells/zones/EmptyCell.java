package objectville.cells.zones;

import objectville.cells.Cell;
import objectville.cells.Position;

public class EmptyCell implements Cell {
    private final Position position;

    public EmptyCell(Position position) {
        this.position = position;
    }

    @Override
    public boolean isPassable(){ // Empty cells should not be passable
        return false;
    }

    @Override
    public char getSymbol() { // E for emptyCells
        return 'E';
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void updateState(){ // Should remain static during ticks

    }
}
