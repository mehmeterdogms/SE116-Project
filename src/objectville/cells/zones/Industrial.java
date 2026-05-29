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
        //if level is 0 there is no population
        if (this.level == 0) {
            this.givenGoods = 0;
        } else {
            // goods formula is level * m
            this.givenGoods = this.level * this.m;
        }
    }

    @Override
    public char getSymbol() { // I for industrial
        return 'I';
    }
}