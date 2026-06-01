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
    public void updateState(){ // set the level to 0 if the required utilities are not met
       if (this.takenElectricity == 0 || this.takenWater == 0){
           this.level = 0;
       } else {
           boolean meetsRequirements = this.takenElectricity >= this.getNeededElectricity() &&
                   this.takenWater >= this.getNeededWater();
           boolean WorkForce  = this.receivedPopulation > 0;
           if (!meetsRequirements || !WorkForce){
               this.level = Math.max(0, this.level - 1);
           } else  {
               if ( this.level == 0){
                   this.level = 1; // Workforce and utilities are sufficient for leve 1
               } else if (this.level == 1){
                   if  (this.HasPoliceStation()){ // A police Station is required for level 2
                       this.level = 2;
                   }
               } else if (this.level == 2){
                   if  (this.HasPoliceStation() && this.receivedPopulation > 0){ // A police Station and excess population are enough for level 3
                       this.level = 3;
                   }
               } else if (this.level == 3){
                   if  (!this.HasPoliceStation() || this.receivedPopulation == 0){ // A police Station and  excess population are required inorder to protect the level 3
                       this.level = 2;
                   }
               }
           }
       }
       this.calculateOutput();
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