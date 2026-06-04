package objectville.engine;

import objectville.cells.Cell;
import objectville.cells.zones.Commercial;
import objectville.cells.zones.Housing;
import objectville.cells.zones.Industrial;
import objectville.cells.zones.Zone;
import objectville.engine.systems.ResourceManager;
import objectville.engine.systems.ServiceManager;
import objectville.engine.systems.UtilitySystem;
import objectville.io.DisplayManager;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private Cell[][] map;
    private ServiceManager serviceManager;
    private UtilitySystem utilitySystem;
    private ResourceManager resourceManager;
    private DisplayManager displayManager;

    public GameEngine(Cell[][] map) {
        this.map = map;
        // We need to use methods so we need to use "new".
        this.serviceManager = new ServiceManager();
        this.utilitySystem = new UtilitySystem();
        this.resourceManager = new ResourceManager();
        this.displayManager = new DisplayManager();
    }
    //This method turns to Cell[][] to arraylist.
    public List<Cell> getCellsAsList() {
        List<Cell> cellList = new ArrayList<>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                cellList.add(map[i][j]);

            }
        }
        return cellList;
    }
    //This is where the actual game engine runs. Not so much thing.
    public void runSimulation(int totalTicks) {
        for (int i = 1; i <= totalTicks; i++) {
            //I did it this way so it would look like "output.txt".
            System.out.println("Tick " + i);
            serviceManager.distributeServices(map, displayManager);
            utilitySystem.distributeUtilities(map, displayManager);
            if (i > 1) {
                resourceManager.distributePreviousTickResources(getCellsAsList(), displayManager);
            }
            for (int j = 0; j < map.length; j++) {
                for (int k = 0; k < map[j].length; k++) {
                    if (map[j][k] instanceof Zone) {
                        //Down-casting the Cell interface.
                        Zone zone = (Zone) map[j][k];
                        int oldLevel = zone.getLevel();

                        //We want to look like "output.txt" so I made like this:
                        switch (zone) {
                            case Housing house ->
                                    displayManager.logResourceGenerated(zone, "population", house.getGivenPopulation());
                            case Industrial ind ->
                                    displayManager.logResourceGenerated(zone, "goods", ind.getGivenGoods());
                            case Commercial com ->
                                    displayManager.logResourceGenerated(zone, "lifestyle", com.getGivenLifeStyle());
                            default -> {
                            }
                        }

                        //If level changes, we are saved on top.
                        zone.updateState();
                        int newLevel = zone.getLevel();

                        //Level ups logs.
                        if (newLevel > oldLevel) {
                            displayManager.logLevelUp(zone, oldLevel, newLevel);
                        } else if (newLevel < oldLevel) {
                            displayManager.logLevelDown(zone, oldLevel, newLevel);
                        }
                    }

                }

            }

            resourceManager.poolResources(getCellsAsList());

        }

    }
}