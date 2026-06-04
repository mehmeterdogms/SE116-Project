package objectville.cells.zones;
import objectville.cells.Position;
public class Commercial extends Zone {
    public Commercial(Position position) {
        super(position);

    }

    @Override
    public void updateState(){
        // set the level to 0 if the required utilities are not met
        if (this.takenElectricity == 0 || this.takenWater == 0 || this.takenInternet == 0 ){
            this.level = 0;
        } else {
            boolean meetsRequirements = this.takenElectricity >= this.getNeededElectricity() &&
                    this.takenWater >= this.getNeededWater() &&
                    this.takenInternet >= this.getNeededInternet();
            if (!meetsRequirements ) { // Gradually reduce the level by 1 if the requirements are not met
                this.level = Math.max(0, this.level - 1);
            } else {
                if (this.level == 0) {
                    this.level = 1;
                } else if (this.level == 1) { // Security is needed for level 2
                    if (this.HasPoliceStation()) {
                        this.level = 2;
                    }
                } else if (this.level == 2) { //excess population and excess goods are needed for lvl3
                    if (this.HasPoliceStation() && this.receivedPopulation > 0 && this.receivedGoods > 0) {
                        this.level = 3;
                    }
                } else if (this.level == 3) { // Cheks if it still has security and other necessities  to hold its level 3 status
                    if (!this.HasPoliceStation() || this.receivedPopulation == 0 || this.receivedGoods == 0) {
                        this.level = 2;
                    }
                }
            }

        }

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
