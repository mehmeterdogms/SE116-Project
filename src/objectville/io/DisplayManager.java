package objectville.io;

import objectville.cells.Cell;
import objectville.cells.zones.Zone;

public class DisplayManager {

    public void logServiceReceived(Zone zone, String servicetype) {
        System.out.printf("%c at %d,%d received %s service%n",
                zone.getSymbol(),
                zone.getPosition().getX(),
                zone.getPosition().getY(),
                servicetype);
    }

    public void logResourceReceived(Zone zone, String resourcetype, int amount) {
        System.out.printf("%c at %d,%d received %d %s%n",
                zone.getSymbol(),
                zone.getPosition().getX(),
                zone.getPosition().getY(),
                amount,
                resourcetype);
    }

    public void logResourceGenerated(Zone zone, String resourcetype, int amount) {
        System.out.printf("%c at %d,%d generated %d %s%n",
                zone.getSymbol(),
                zone.getPosition().getX(),
                zone.getPosition().getY(),
                amount,
                resourcetype);
    }

    public void logLevelUp(Zone zone, int oldlevel, int newlevel) {
        System.out.printf("%c at %d,%d levels up from %d to %d%n",
                zone.getSymbol(),
                zone.getPosition().getX(),
                zone.getPosition().getY(),
                oldlevel,
                newlevel);
    }

    public void printGrid(Cell[][] map) {
        System.out.printf("%n--- Map State ---%n");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Cell cell = map[i][j];
                char symbol = cell.getSymbol();

                if (cell instanceof Zone) {
                    Zone zone = (Zone) cell;
                    System.out.printf("%c%d ", symbol, zone.getLevel());
                } else {
                    System.out.printf("%c0 ", symbol);
                }
            }
            System.out.printf("%n");
        }

    }
}