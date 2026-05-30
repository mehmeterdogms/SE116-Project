package objectville.cells.zones;
import objectville.cells.Position;
public class Industrial extends Zone {
   public Industrial(Position position){
    super(position);
}
    @Override
    public int calculateM() {
        // Will look at received Electricity and Water
        this.m = Math.min(takenElectricity, takenWater);
        return this.m;
    }

    @Override
    public void calculateOutput() {
        // Will generate Goods based on level and 'm'
        // Making sure about m is done before calculateOutput
        calculateM();
        //if level is 0 there is no goods
        if (this.level == 0) {
            this.givenGoods = 0;
        } else if (this.level == 1) {
            this.givenGoods = this.m;
        } else if (this.level == 2) {
            this.givenGoods = 2 * this.m;
        } else if (this.level == 3) {
            // Level 3: 2m + received population
            this.givenGoods = (2 * this.m) + this.receivedPopulation;
        }
    }

    @Override
    public char getSymbol() { // I for industrial
        return 'I';
    }
}