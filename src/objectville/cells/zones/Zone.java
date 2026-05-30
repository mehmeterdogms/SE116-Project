package objectville.cells.zones;

import objectville.cells.Cell;
import objectville.cells.Position;

public abstract class Zone implements Cell {

    //values that are needed for level 3 output, ResourceManager
    protected int receivedPopulation = 0;
    protected int receivedLifeStyle = 0;
    protected int receivedGoods = 0;

    //zones wanted amounds at least 1
    protected int neededElectricity = 1;
    protected int neededWater = 1;
    protected int neededInternet = 1;

    //the amound zones have taken
    protected int takenElectricity = 0;
    protected int takenWater = 0;
    protected int takenInternet = 0;

    //the amounds zones created
    protected int givenPopulation = 0;
    protected int givenLifeStyle =0;
    protected int givenGoods = 0;

    //the min taken value of that tick
    protected int m=0;

    protected Position position;
    public Zone(Position position){
        this.position = position;
    }


    //These methods are added for ResourceManager
    public int getReceivedPopulation() {return receivedPopulation;}

    public void setReceivedPopulation(int receivedPopulation) {this.receivedPopulation = receivedPopulation;}

    public int getReceivedLifeStyle() {return receivedLifeStyle;}

    public void setReceivedLifeStyle(int receivedLifeStyle) {this.receivedLifeStyle = receivedLifeStyle;}

    public int getReceivedGoods() {return receivedGoods;}

    public void setReceivedGoods(int receivedGoods) {this.receivedGoods = receivedGoods;}

    //These fields and methods are added for ServiceManager (
    private boolean hasSchool;
    private boolean hasHospital;
    private boolean hasPoliceStation;

    public boolean HasSchool() { return hasSchool; }

    public void setHasSchool(boolean hasSchool) {
        this.hasSchool = hasSchool;
    }

    public boolean HasHospital() {
        return hasHospital;
    }

    public void setHasHospital(boolean hasHospital) {
        this.hasHospital = hasHospital;
    }

    public boolean HasPoliceStation() {
        return hasPoliceStation;
    }

    public void setHasPoliceStation(boolean hasPoliceStation) {
        this.hasPoliceStation = hasPoliceStation;
    }
    protected int level = 0; // Starts from 0

    public  int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        if (level >= 0 && level <= 3) {
            this.level = level;
        }
    }

    public abstract int calculateM();
    // Calculate the minimum utility factor 'm'

    public abstract void calculateOutput();
    // Calculate what resource this zone generates

    // Methods mandated by the Cell Interface

    @Override
    public Position getPosition() {
        return this.position;
    }
    @Override
    public boolean isPassable() {
        return false;
    }
    @Override
    public void updateState(){  // will be filled in the future by upcoming parts of the simulation.
    }

    public int getNeededElectricity() {
        // this code is made for not update manually needed utilities
        //if level is 0 we want 1
        if (this.level == 0) {
            return 1;
        }

        if (this instanceof Industrial) {
            return Math.max(1, this.givenGoods);
        } else if (this instanceof Housing) {
            return Math.max(1, this.givenPopulation);
        } else if (this instanceof Commercial) {
            return Math.max(1, this.givenLifeStyle);
        }
        return 1; // for any kind of mistake in the code and crashes
    }

    public void setNeededElectricity(int neededElectricity) {
        this.neededElectricity = neededElectricity;
    }

    public int getNeededWater() {
        // this code is made for not update manually needed utilities
        //if level is 0 we want 1
        if (this.level == 0) {
            return 1;
        }

        if (this instanceof Industrial) {
            return Math.max(1, this.givenGoods);
        } else if (this instanceof Housing) {
            return Math.max(1, this.givenPopulation);
        } else if (this instanceof Commercial) {
            return Math.max(1, this.givenLifeStyle);
        }

        return 1;// for any kind of mistake in the code and crashes
    }

    public void setNeededWater(int neededWater) {
        this.neededWater = neededWater;
    }

    public int getNeededInternet() {
        // this code is made for not update manually needed utilities
        //if level is 0 we want 1
        if (this instanceof Industrial) { //Industrial do not use Internet
            return 0;
        }

        if (this.level == 0) {
            return 1;
        }

        if (this instanceof Housing) {
            return Math.max(1, this.givenPopulation);
        } else if (this instanceof Commercial) {
            return Math.max(1, this.givenLifeStyle);
        }

        return 1;// for any kind of mistake in the code and crashes
    }

    public void setNeededInternet(int neededInternet) {
        this.neededInternet = neededInternet;
    }

    public int getTakenElectricity() {
        return takenElectricity;
    }

    public void setTakenElectricity(int takenElectricity) {
        this.takenElectricity = takenElectricity;
    }

    public int getTakenWater() {
        return takenWater;
    }

    public void setTakenWater(int takenWater) {
        this.takenWater = takenWater;
    }

    public int getTakenInternet() {
        return takenInternet;
    }

    public void setTakenInternet(int takenInternet) {
        this.takenInternet = takenInternet;
    }

    public int getGivenPopulation() {
        return givenPopulation;
    }

    public void setGivenPopulation(int givenPopulation) {
        this.givenPopulation = givenPopulation;
    }

    public int getGivenLifeStyle() {
        return givenLifeStyle;
    }

    public void setGivenLifeStyle(int givenLifeStyle) {
        this.givenLifeStyle = givenLifeStyle;
    }

    public int getGivenGoods() {
        return givenGoods;
    }

    public void setGivenGoods(int givenGoods) {
        this.givenGoods = givenGoods;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }


}
