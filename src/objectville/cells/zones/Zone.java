package objectville.cells.zones;

import objectville.cells.Cell;
import objectville.cells.Position;

public abstract class Zone implements Cell {

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
// )
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
        return null;
    }
    @Override
    public boolean isPassable() {
        return false;
    }
    @Override
    public void updateState(){  // will be filled in the future by upcoming parts of the simulation.

    }


}
