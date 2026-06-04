package objectville.engine.systems;

import objectville.cells.Cell;
import objectville.cells.Position;
import objectville.cells.services.ServiceBuilding;
import objectville.cells.zones.Housing;
import objectville.cells.zones.Zone;
import objectville.io.DisplayManager;

public class ServiceManager {
    // I calculate distance with Manhattan formula.
    public int calculateDistance(Position p1, Position p2) {
        int x = Math.abs(p1.getX() - p2.getX());
        int y = Math.abs(p1.getY() - p2.getY());
        return x + y;
    }

    public void distributeServices(Cell[][] map, DisplayManager displayManager) {
        //Cleaning for tick system.
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] instanceof Zone) {
                    //Down-casting the Cell interface.
                    Zone zone = (Zone) map[i][j];
                    //We always need to reset the system.
                    zone.setHasHospital(false);
                    zone.setHasSchool(false);
                    zone.setHasPoliceStation(false);
                }
            }
        }

        //I am scanning all map for decide which ones are services.
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] instanceof ServiceBuilding) {
                    //Down-casting the Cell interface.
                    ServiceBuilding building = (ServiceBuilding) map[i][j];
                    //Getting values.
                    int radius = building.getRadius();
                    Position positionBuilding = building.getPosition();
                    String type = building.getServiceType();
                    //I scanned here one more time for doesn't break map[i][j] variable.
                    for (int k = 0; k < map.length; k++) {
                        for (int l = 0; l < map[k].length; l++) {
                            if (map[k][l] instanceof Zone) {
                                //Down-casting the Cell interface.
                                Zone zone = (Zone) map[k][l];

                                Position positionZone = zone.getPosition();

                                int distance = calculateDistance(positionBuilding, positionZone);
                                //IntelliJ recommend to use enchanted switch-case. First, I was made with "if".
                                //We are using DisplayManager for the log.
                                if (distance <= radius) {
                                    switch (type) {
                                        case "Health" -> {//Health service only for housing
                                            if (zone instanceof Housing) {
                                                zone.setHasHospital(true);
                                                displayManager.logServiceReceived(zone, "health");
                                            }

                                        }
                                        case "Education" -> {//Health education only for housing
                                            if (zone instanceof Housing) {
                                                zone.setHasSchool(true);
                                                displayManager.logServiceReceived(zone, "education");
                                            }

                                        }
                                        case "Security" -> {//Security for all building
                                            zone.setHasPoliceStation(true);
                                            displayManager.logServiceReceived(zone, "security");
                                        }
                                    }
                                }

                            }

                        }

                    }

                }
            }

        }

    }
}