package objectville.cells.services;

import objectville.cells.Position;

public class PoliceStation extends ServiceBuilding {
    // Police station provides security service with radius 5
    public PoliceStation(Position position) {
        super(5, position, 'F');
    }

    @Override
    public String getServiceType() {
        return "Security";
    }
}