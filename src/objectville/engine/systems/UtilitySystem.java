
package objectville.engine.systems;
import objectville.cells.Cell;
import objectville.cells.Position;
import objectville.cells.utilities.UtilityProvider;
import objectville.cells.zones.Zone;
import java.util.ArrayList;
import objectville.cells.infrastructure.Road;

public class UtilitySystem{
    //I am making a method for not writing the same thing again and again in if's
    //it's for checking neighbor cells and give utility or continue with road
    public void checkNeighbor(Cell[][] map, int nextX, int nextY, String type, boolean[][] lookedCells, ArrayList<Position> cellToVisit, int[] globalCapacity){
        if (globalCapacity[0] <= 0) {
            return;
        }
        //checking if we already looked at it
        if (lookedCells[nextX][nextY]){
            return;
        }
        Cell neighbor = map[nextX][nextY];
        //if neighbor is a road, energy flows freely without losing any capacity
        if (neighbor instanceof Road){
            lookedCells[nextX][nextY] = true;
            cellToVisit.add(neighbor.getPosition());
        }
        //checking if it is a zone it  is important because it could be empty cell or utility provider too
        else if (neighbor instanceof Zone){
            Zone zone = (Zone) neighbor;
            //We lock the cell so it cant be processed again
            lookedCells[nextX][nextY] = true;
            int required = 0;
            if (type.equals("Electricity")) {
                required = zone.getNeededElectricity()- zone.getTakenElectricity();
            } else if (type.equals("Water")) {
                required = zone.getNeededWater()- zone.getTakenWater();
            } else if (type.equals("Internet")) {
                required = zone.getNeededInternet()- zone.getTakenInternet();
            }
            //required must never be negative if it is we should reset to 0
            required = Math.max(0,required);
            //giving as much as we can
            int deliveredEnergy = Math.min(globalCapacity[0], required);

            if(deliveredEnergy > 0) {
                //adding on top because otherwise we can lose some value if there are more than 1 provider
                if (type.equals("Electricity")){
                    zone.setTakenElectricity(zone.getTakenElectricity() + deliveredEnergy);
                } else if (type.equals("Water")) {
                    zone.setTakenWater(zone.getTakenWater() + deliveredEnergy);
                } else if (type.equals("Internet")){
                    zone.setTakenInternet(zone.getTakenInternet() + deliveredEnergy);
                }
            }
            //look for the remaining energy
            globalCapacity[0] -=deliveredEnergy;
            //if capacity exists even full cells must distribute.
            if (globalCapacity[0]> 0){
                cellToVisit.add(zone.getPosition());
            }

        }
    }

    //bfs algorithm to deliver utility
    public void BFS(Cell[][] map, Position startPos,String type, int startCapacity){
        int rows = map.length;
        int cols = map[0].length;
        //Listing cells that looked to prevent infinite loops
        boolean[][] lookedCells = new boolean[rows][cols];
        //important ArrayList for storing the coordinates
        ArrayList<Position> cellToVisit = new ArrayList<>();
        //pointer of the cellsToVisit list
        int listCounter = 0;
        //integers are pass by value so they wouldn't change outside the methods this is why we used an Array.
        int[] globalCapacity = new int[1];
        globalCapacity[0] = startCapacity;
        //making cells looked cell and storing their locations
        cellToVisit.add(startPos);
        lookedCells[startPos.getX()][startPos.getY()] = true;
        //loop until the end of the list
        while (listCounter < cellToVisit.size()) {
        if(globalCapacity[0]<= 0){
            break;
        }
            Position currentPosition = cellToVisit.get(listCounter);
           listCounter++;

            int currentX = currentPosition.getX();
            int currentY = currentPosition.getY();

            //in BFS a random order has been written since it is not specified in the instructions (left to right).
            //upper neighbor
            if (currentX - 1 >= 0) {
                checkNeighbor(map, currentX - 1, currentY, type, lookedCells, cellToVisit, globalCapacity);
            }
            //right neighbor
            if (currentY + 1 < cols) {
                checkNeighbor(map, currentX, currentY + 1, type, lookedCells, cellToVisit, globalCapacity);
            }
            //down neighbor
            if (currentX + 1 < rows) {
                checkNeighbor(map, currentX + 1, currentY, type, lookedCells, cellToVisit, globalCapacity);
            }

            //left neighbor
            if (currentY - 1 >= 0) {
                checkNeighbor(map, currentX, currentY - 1, type, lookedCells, cellToVisit, globalCapacity);
            }


        }
    }


    public void distributeUtilities(Cell[][] map){
        int rows = map.length;
        //start whit 0. col
        int cols = map[0].length;

        //we are reading all the map and resetting values to 0
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                //checking cells to find zones
                if (map[r][c] instanceof Zone){
                    //down-casting from cell to zone
                    Zone zone = (Zone) map[r][c];
                    //reset the amounts taken for every tick
                    zone.setTakenElectricity(0);
                    zone.setTakenWater(0);
                    zone.setTakenInternet(0);
                }
            }
        }
        //finding providers
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Cell currentCell = map[r][c];
                if (currentCell instanceof UtilityProvider) {
                    UtilityProvider provider = (UtilityProvider) currentCell;
                    String type = provider.getProvidedUtilityType();

                    BFS(map, provider.getPosition(), type, 100);
                }
            }
        }
    }
}
