package objectville.map;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import objectville.cells.Cell;
import objectville.cells.Position;
import objectville.cells.utilities.PowerPlant;
import objectville.cells.utilities.WaterPumpingStation;
import objectville.cells.utilities.InternetHub;


public class MapLoader {

    //converts the map to 2D Array after loading
    public static Cell[][] loadMap(Path filePath){
        List <char[]> lines = new ArrayList<>(); //changed string to char based on the sample input

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
                    char[] tokens = line.toCharArray();
                    lines.add(tokens);
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
        int columns = lines.get(0).length;
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
              Position position = new Position(c,r);
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
        return null; // going to change after other building types

    }
}