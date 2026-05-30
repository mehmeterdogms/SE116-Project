package objectville.engine.systems;

import objectville.cells.Cell;
import objectville.cells.zones.Commercial;
import objectville.cells.zones.Housing;
import objectville.cells.zones.Industrial;

import java.util.List;
import java.util.stream.Collectors;

public class ResourceManager {

  private int populationPool;
  private int lifestylePool;
  private int goodsPool;

  // With each tick, generated resources by zones are stored in pools
  public void poolResources(List<Cell> cells){
      for (Cell cell: cells){

          if ( cell instanceof Housing ){
       Housing house = (Housing) cell;
       house.calculateOutput();
       populationPool +=house.getGivenPopulation();
          }

          else if (cell instanceof Industrial){
              Industrial industrial = (Industrial) cell;
              industrial.calculateOutput();
              goodsPool += industrial.getGivenGoods();
          }

          else if (cell instanceof Commercial){
              Commercial commercial = (Commercial) cell;
              commercial.calculateOutput();
              lifestylePool += commercial.getGivenLifeStyle();
          }
      }
  }

   public void distributePreviousTickResources (List <Cell> cells){

      // separates the cells into lists for distribution
       List <Cell> housingCells = cells.stream()
               .filter(c -> c instanceof Housing)
               .collect(Collectors.toList()); // can changed to toList in latest

      List <Cell> industrialCells = cells.stream()
              .filter(c -> c instanceof Industrial)
              .collect(Collectors.toList());

      List <Cell> commercialCells = cells.stream()
              .filter(c -> c instanceof Commercial)
              .collect(Collectors.toList());

      int totalWorkerZones = industrialCells.size() + commercialCells.size();

      //distribute population to industrial and commercial zones
      if ( totalWorkerZones > 0 ) {
          int populationPerZone = populationPool / totalWorkerZones ;

          for (Cell cell : industrialCells){
              ((Industrial) cell ).setReceivedPopulation(populationPerZone);
          }

          for ( Cell cell : commercialCells){
              ((Commercial) cell).setReceivedPopulation(populationPerZone);
          }
          // saves the remaining resources for the next tick
          populationPool %= totalWorkerZones;
      }

      // distribute goods to commercial zones
      if ( commercialCells.size() > 0){
          int goodsPerZone = goodsPool / commercialCells.size();

          for ( Cell cell : commercialCells ){
              ((Commercial)cell).setReceivedGoods(goodsPerZone);
          }
            goodsPool %= commercialCells.size();
      }

      //distributes lifestyle to housing zones
      if (housingCells.size() > 0){
          int lifestylePerZone = lifestylePool / housingCells.size();

          for (Cell cell : housingCells){
              ((Housing)cell).setReceivedLifeStyle(lifestylePerZone);
          }
          lifestylePool %= housingCells.size();
      }
   }
}