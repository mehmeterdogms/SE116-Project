package objectville.cells.zones;
import objectville.cells.Position;
public class Commercial extends Zone {
    public Commercial(Position position) {
        super(position);

    }

    @Override
    public int calculateM() {
        // commercial zones needs all three utilities
        int minElcWat = Math.min(takenElectricity, takenWater);
        this.m =Math.min(minElcWat, takenInternet);
        return this.m;
    }

    @Override
    public void calculateOutput(){
        // Making sure about m is done before calculateOutput
        calculateM();
        //if level is 0 there is no LifeStyle
        if (this.level == 0) {
            this.givenLifeStyle = 0;
        } else if (this.level == 1) {
            this.givenLifeStyle = this.m;
        } else if (this.level == 2) {
            this.givenLifeStyle = 2 * this.m;
        } else if (this.level == 3) {
            // Level 3: 2m + min(received population, received goods)
            this.givenLifeStyle = (2 * this.m) + Math.min(this.receivedPopulation, this.receivedGoods);
        }

    }

    @Override
    public char getSymbol(){ // C for commercial
        return 'C';
    }

}
