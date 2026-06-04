package objectville.engine.systems;

import objectville.cells.Cell;
import objectville.cells.zones.Commercial;
import objectville.cells.zones.Housing;
import objectville.cells.zones.Industrial;
import objectville.cells.zones.Zone;
import objectville.io.DisplayManager;

import java.util.ArrayList;
import java.util.List;

public class  ResourceManager {

  private int populationPool;
  private int lifestylePool;
  private int goodsPool;
  //We don't need to use displayManager here, we already log the sources in GameManager.
  // With each tick, generated resources by zones are stored in pools
  public void poolResources(List<Cell> cells){
      for (Cell cell: cells){

          if ( cell instanceof Housing ){
       Housing house = (Housing) cell;
       populationPool +=house.getGivenPopulation();
          }

          else if (cell instanceof Industrial){
              Industrial industrial = (Industrial) cell;
              goodsPool += industrial.getGivenGoods();
          }

          else if (cell instanceof Commercial){
              Commercial commercial = (Commercial) cell;
              lifestylePool += commercial.getGivenLifeStyle();
          }
      }
  }

   public void distributePreviousTickResources (List <Cell> cells, DisplayManager displayManager){

      // separates the cells into lists for distribution
     List<Cell> housingCells = new ArrayList<>();
     List<Cell> industrialCells = new ArrayList<>();
     List<Cell> commercialCells = new ArrayList<>();


       // IntelliJ suggested using Stream API, but I replaced it with a single loop because it is not covered in the lecture slides
     for ( Cell cell : cells){
         if(cell instanceof Housing){
             housingCells.add(cell);
         } else if ( cell instanceof Industrial){
             industrialCells.add(cell);
         } else if ( cell instanceof Commercial){
             commercialCells.add(cell);
         }
     }

      int totalWorkerZones = industrialCells.size() + commercialCells.size();

      //distribute population to industrial and commercial zones
      if ( totalWorkerZones > 0 ) {
          int populationPerZone = populationPool / totalWorkerZones ;

          for (Cell cell : industrialCells){
              ((Industrial) cell ).setReceivedPopulation(populationPerZone);
            if (populationPerZone > 0) {
              displayManager.logResourceReceived((Zone) cell, "population", populationPerZone);
            }
          }

          for ( Cell cell : commercialCells){
              ((Commercial) cell).setReceivedPopulation(populationPerZone);
            if (populationPerZone > 0) {
              displayManager.logResourceReceived((Zone) cell, "population", populationPerZone);
            }
          }
          // Remaining resources are lost as cost of distribution
          populationPool = 0;
      }

      // distribute goods to commercial zones
      if ( commercialCells.size() > 0){
          int goodsPerZone = goodsPool / commercialCells.size();

          for ( Cell cell : commercialCells ) {
              ((Commercial) cell).setReceivedGoods(goodsPerZone);
              if (goodsPerZone > 0) {
                  displayManager.logResourceReceived((Zone) cell, "goods", goodsPerZone);
              }
          }
            goodsPool = 0;
      }

      //distributes lifestyle to housing zones
      if (housingCells.size() > 0){
          int lifestylePerZone = lifestylePool / housingCells.size();

          for (Cell cell : housingCells) {
              ((Housing) cell).setReceivedLifeStyle(lifestylePerZone);
              if (lifestylePerZone > 0) {
                  displayManager.logResourceReceived((Zone) cell, "lifestyle", lifestylePerZone);
              }
          }
          lifestylePool = 0;
      }
   }
}