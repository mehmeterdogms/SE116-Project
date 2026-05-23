package objectville.cells.services;

import objectville.cells.Position;

public class Hospital extends ServiceBuilding {
    // Hospital provides health service with radius 3
    public Hospital(Position position) {
        super(3, position, 'D');
    }

    @Override
    String getServiceType() {
        return "Health";
    }
}