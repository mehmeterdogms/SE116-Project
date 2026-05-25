package objectville.cells.services;

import objectville.cells.Position;

public class School extends ServiceBuilding {
    // School provides education service with radius 4
    public School(Position position) {
        super(4, position, 'S');
    }

    @Override
    public String getServiceType() {
        return "Education";
    }
}