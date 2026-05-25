package objectville.cells.services;

import objectville.cells.Cell;
import objectville.cells.Position;

public abstract class ServiceBuilding implements Cell {
    protected int radius;
    protected Position position;
    protected char symbol;

    public ServiceBuilding(int radius, Position position, char symbol) {
        this.radius = radius;
        this.position = position;
        this.symbol = symbol;
    }

    // Returns the type of service this building provides
    public abstract String getServiceType();

    // Returns the radius of effect for this service building
    public int getRadius() {
        return radius;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public void updateState() {
        // Empty - to be filled later
    }
}