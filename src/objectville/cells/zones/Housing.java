package objectville.cells.zones;
import objectville.cells.Position;
public class Housing extends Zone{
    public Housing(Position position){
        super(position);
    }
    @Override
    public int calculateM(){
        // Will look at received Electricity, Water, and Internet
        //Math.min takes out the lowest value of 2 int inside
        int minOfElcWat = Math.min(takenElectricity, takenWater);
        this.m = Math.min(minOfElcWat, takenInternet);
        return this.m;
    }

    @Override
    public void calculateOutput() {
        // Population based on level and "m"
        // Making sure about m is done before calculateOutput
        calculateM();
        //if level is 0 there is no population
        if (this.level == 0) {
            this.givenPopulation = 0;
        } else {
            // Population formula is level * m
            this.givenPopulation = this.level * this.m;
        }
    }

    @Override
    public char getSymbol(){ // H for housing
        return 'H';
    }
}
