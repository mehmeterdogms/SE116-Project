package objectville.map;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import objectville.cells.Cell;
import objectville.cells.Position;
//utilities
import objectville.cells.utilities.PowerPlant;
import objectville.cells.utilities.WaterPumpingStation;
import objectville.cells.utilities.InternetHub;
//infrastructure & empty
import objectville.cells.infrastructure.Road;
import objectville.cells.zones.EmptyCell;
//services
import objectville.cells.services.Hospital;
import objectville.cells.services.PoliceStation;
import objectville.cells.services.School;
//Zones
import objectville.cells.zones.Housing;
import objectville.cells.zones.Industrial;
import objectville.cells.zones.Commercial;

public class MapLoader {

    //converts the map to 2D Array after loading
    public static Cell[][] loadMap(Path filePath){
        List <char[]> lines = new ArrayList<>(); //changed string to char based on the sample input


        int maxColumns = 0;

        //read file line by line, ignoring empty lines
        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)){
          String line;

            while (true) {
                 line = bufferedReader.readLine();

                if (line == null){
                    break;
                }

                line = line.trim();

                //ignores empty lines to not create invalid rows
                if (!line.isEmpty()) {
                    //if there are any space in txt we must skip or the least code (unknown cell = empty cell)
                    //will be used, it will be wrong
                    char[] tokens = line.replace(" ", "").toCharArray();
                    lines.add(tokens);

                    if (tokens.length > maxColumns){
                        maxColumns = tokens.length;
                    }
                }
            }
        }

        //If an error occurs while reading the file,returns an empty map
        catch (IOException e){
            System.out.println("Error occurred while loading the map : "+ e.getMessage());
            return new Cell[0][0];
        }

        if (lines.isEmpty()){
            return new Cell[0][0];
        }


        //Defining Array dimensions
        int rows = lines.size();
        int columns = maxColumns;
        Cell[][] gameMap = new Cell[rows][columns];

        // r (rows) represents y-axis, c (columns) represents x-axis
        for ( int r = 0 ; r < rows ; r++){

            char[] tokens = lines.get(r);


          for ( int c = 0 ; c < columns ; c++ ){

              char symbol = 'E'; // Default : empty cell

              //Prevents index errors
              if ( c < tokens.length) {
                  symbol = tokens[c];
              }
              Position position = new Position(r,c);
              gameMap[r][c] = createCell(symbol,position);
          }
        }
        return gameMap;
    }

//Creates the correct cell type for the symbols
    private static Cell createCell (char symbol, Position position){

        if (symbol == 'P') {
            return new PowerPlant(position);
        } else if (symbol == 'W'){
            return new WaterPumpingStation(position);
        } else if (symbol == 'T'){
            return new InternetHub(position);
        }
        // Infrastructure & Empty Cells
        else if (symbol =='R') {
            return new Road(position);
        } else if (symbol == 'E') {
            return new EmptyCell(position);
        }

        // services
        else if (symbol == 'D'){
            return new Hospital(position);
        } else if (symbol == 'F') {
            return new PoliceStation(position);
        } else if (symbol =='S'){
            return new School(position);
        }

        // Zones
        else if (symbol =='H'){
            return new Housing(position);
        } else if (symbol =='I'){
            return new Industrial(position);
        } else if (symbol =='C'){
            return new Commercial(position);
        }


        // Returning an EmptyCell if the symbol is unknown for solving possible errors could change later, not needed
        else {
            return new EmptyCell(position);
        }

    }
}