package objectville.cells.zones;

public class Commercial extends Zone{
    @Override
    public int calculateM(){
        return 0;
    }

    @Override
    public void calculateOutput(){
        //generate Lifestyle based on level and 'm'
    }

    @Override
    public char getSymbol(){ // C for commercial
        return 'C';
    }

}
