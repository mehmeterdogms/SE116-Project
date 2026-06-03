package objectville.engine;

import objectville.cells.Cell;
import objectville.map.MapLoader;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            // For "java -jar ObjectVilleGame.jar mymap.txt" or "java -jar ObjectVilleGame.jar 5" like something.
            System.out.println("ERROR. Enter the map name and tick rate.");
            //This return causes the code stop.
            return;
        }
        String mapName = args[0];
        String tick = args[1];

        int tickRate;

        try {
            tickRate = Integer.parseInt(tick);
            if (tickRate < 0) {
                // For "java -jar ObjectVilleGame.jar mymap.txt -3" or like something.
                System.out.println("ERROR. Tick rate can not be a negative number.");
                //This return causes the code stop.
                return;
            }
            // For "java -jar ObjectVilleGame.jar mymap.txt X" or like something.
        } catch (NumberFormatException e) {
            System.out.println("ERROR. Enter the number.");
            //This return causes the code stop.
            return;
        }
        try {
            // I deeply researched this thing and I remembered Lab 11, we were used something like this.
            Path mapPath = Paths.get(mapName);
            Cell[][] map = MapLoader.loadMap(mapPath);
            GameEngine gameEngine = new GameEngine(map);

            gameEngine.runSimulation(tickRate);
            //This code actually only for control, but its help to me to find errors.
        } catch (Exception e) {
            System.out.println("ERROR. An issue occurred while running simulation.");
        }

    }
}
