package FootballField.Model;

import java.util.HashMap;

public class Defense {

    private double broken;
    private double interception;
    private double clearance;
    private double forcedGrab;
    private double headerClearance;
    private double GoalkeeperRescue;

    public Defense(){

        //initial(defenseRecord);
    }

    public void getData(HashMap<String,Double> defenseRecord){
        for (String key : defenseRecord.keySet()) {
            double value=defenseRecord.get(key);
            switch (key){
                case "broken" :broken=value; break;
                case "interception" :interception=value; break;
                case "clearance" :clearance=value; break;
                case "forcedGrab" :forcedGrab=value; break;
                case "headerClearance" :headerClearance=value; break;
                case "GoalkeeperRescue" :GoalkeeperRescue=value;
            }
        }
    }
    // when calculate defense success rate of scheme, use this
    public double getAverageDefense(){
        return (broken+interception+clearance+forcedGrab+headerClearance+GoalkeeperRescue)/6;
    }

    //Getter and Setter
    public double getBroken() {
        return broken;
    }

    public double getInterception() {
        return interception;
    }

    public double getClearance() {
        return clearance;
    }

    public double getForcedGrab() {
        return forcedGrab;
    }

    public double getHeaderClearance() {
        return headerClearance;
    }

    public double getGoalkeeperRescue() {
        return GoalkeeperRescue;
    }

    public void setBroken(double broken) {
        this.broken = broken;
    }

    public void setInterception(double interception) {
        this.interception = interception;
    }

    public void setClearance(double clearance) {
        this.clearance = clearance;
    }

    public void setForcedGrab(double forcedGrab) {
        this.forcedGrab = forcedGrab;
    }

    public void setHeaderClearance(double headerClearance) {
        this.headerClearance = headerClearance;
    }

    public void setGoalkeeperRescue(double goalkeeperRescue) {
        GoalkeeperRescue = goalkeeperRescue;
    }
}
