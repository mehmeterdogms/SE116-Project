package objectville.cells.zones;

public class Housing extends Zone{

    @Override
    public int calculateM(){
        // Will look at received Electricity, Water, and Internet
        return 0;
    }

    @Override
    public void calculateOutput() {
        // Population based on level and "m"
    }

    @Override
    public char getSymbol(){ // H for housing
        return 'H';
    }
}
