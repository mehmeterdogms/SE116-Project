package objectville.cells.zones;
import objectville.cells.Position;
public class Housing extends Zone{

    @Override
    public void updateState() {
        // set the level to 0 if the required utilities are not met
        if (this.takenElectricity == 0 || this.takenInternet == 0 || this.takenWater == 0) {
            this.level = 0;
        } else {
            boolean meetsRequirements = this.takenElectricity >= this.getNeededElectricity() &&
                    this.takenWater >= this.getNeededWater() &&
                    this.takenInternet >= this.getNeededInternet();

            if (!meetsRequirements) { // if the utilities are  insufficent reduce the level by 1
                this.level = Math.max(0, this.level - 1);
            } else { // utilities are enough to get to level 1
                if (this.level == 0) {
                    this.level = 1;
                } else if (this.level == 1) { // police , hospital and a school is needed for level 2
                    if (this.HasPoliceStation() && this.HasHospital() && this.HasSchool()) {
                        this.level = 2;
                    }
                } else if (this.level == 2) { // life style is required to get to lvl 3
                    if (this.HasPoliceStation() && this.HasHospital() && this.HasSchool() && this.receivedLifeStyle > 0) {
                        this.level = 3;
                    }
                } else if (this.level == 3) { // Can it keep the requirements for level 3 or reduce its level
                    if (!(this.HasPoliceStation() && this.HasHospital() && this.HasSchool() && this.receivedLifeStyle > 0)) {
                        this.level = 2;
                    }
                }
            }
        }

    }

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
        } else if (this.level == 1) {
            this.givenPopulation = this.m;
        } else if (this.level == 2) {
            this.givenPopulation = 2 * this.m;
        } else if (this.level == 3) {
            // Level 3: 2m + received lifeStyle
            this.givenPopulation = (2 * this.m) + this.receivedLifeStyle;
        }
    }

    @Override
    public char getSymbol(){ // H for housing
        return 'H';
    }
}
