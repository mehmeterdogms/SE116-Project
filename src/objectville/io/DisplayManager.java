package objectville.io;

import objectville.cells.Cell;
import objectville.cells.zones.Zone;

    // Returns the full name of a zone based on its symbol (H -> House, C -> Commercial, I -> Industrial)
    public class DisplayManager{
        private String getZoneFullName(char symbol){
            if (symbol == 'H') return  "House";
            if (symbol == 'C') return  "Commercial";
            if (symbol == 'I') return  "Industrial";
            return "Zone";
        }
    // Logs the current tick number to the console
    public void logTick(int tickNumber) {
        System.out.printf("Tick %d%n", tickNumber);
    }
        // Logs which service a zone received along with its coordinates
        public void logServiceReceived(Zone zone, String servicetype) {
            // Symbols instead of full name (%s) added parentheses to coordinates
            System.out.printf("%s at (%d,%d) received %s service%n",
                    getZoneFullName(zone.getSymbol()),
                    zone.getPosition().getX(),
                    zone.getPosition().getY(),
                    servicetype);
        }

        public void logResourceGenerated(Zone zone, String resourcetype, int amount) {
            System.out.printf("%s at (%d,%d) generated %d %s%n",
                    getZoneFullName(zone.getSymbol()),
                    zone.getPosition().getX(),
                    zone.getPosition().getY(),
                    amount,
                    resourcetype);
        }
        // Logs the type and amount of resource a zone received along with its coordinates
        public void logResourceReceived(Zone zone, String resourcetype, int amount) {
            System.out.printf("%s at (%d,%d) received %d %s%n",
                    getZoneFullName(zone.getSymbol()),
                    zone.getPosition().getX(),
                    zone.getPosition().getY(),
                    amount,
                    resourcetype);
        }
        // Logs that a zone leveled up showing both the old and new levels
        public void logLevelUp(Zone zone, int oldlevel, int newlevel) {
            System.out.printf("%s at (%d,%d) levels up from %d to %d%n",
                    getZoneFullName(zone.getSymbol()),
                    zone.getPosition().getX(),
                    zone.getPosition().getY(),
                    oldlevel,
                    newlevel);
        }
        // added for the "special levels" down message
        public void logLevelDown(Zone zone, int oldlevel, int newlevel) {
            System.out.printf("%s at (%d,%d) levels down from %d to %d%n",
                    getZoneFullName(zone.getSymbol()),
                    zone.getPosition().getX(),
                    zone.getPosition().getY(),
                    oldlevel,
                    newlevel);
        }
        // Prints the current state of the map as a grid;
        // Zone cells are shown with their symbol and level other cells with their symbol on
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

                        System.out.printf("%c  ", symbol);
                    }
                }
                System.out.printf("%n");
            }
        }
    }