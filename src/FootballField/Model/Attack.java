package FootballField.Model;

import java.util.HashMap;

public class Attack {
    private double dribble;
    private double pitching;
    private double cross;
    private double shooting;
    private double header;
    private double freeKick;

    public void getData(HashMap<String,Double> attackRecord){
        for (String key : attackRecord.keySet()) {
            double value=attackRecord.get(key);
            switch (key){
                case "dribble" :dribble=value; break;
                case "pitching" :pitching=value; break;
                case "cross" :cross=value; break;
                case "shooting" :shooting=value; break;
                case "header" :header=value; break;
                case "freeKick" :freeKick=value;
            }
        }
    }

    // when calculate attack success rate of scheme, use this
    public double getAverageAttack(){
        return (dribble+pitching+cross+shooting+header+freeKick)/6;
    }


    //Getter and Setter
    public double getDribble() {
        return dribble;
    }

    public void setDribble(double dribble) {
        this.dribble = dribble;
    }

    public double getPitching() {
        return pitching;
    }

    public void setPitching(double pitching) {
        this.pitching = pitching;
    }

    public double getCross() {
        return cross;
    }

    public void setCross(double cross) {
        this.cross = cross;
    }

    public double getShooting() {
        return shooting;
    }

    public void setShooting(double shooting) {
        this.shooting = shooting;
    }

    public double getHeader() {
        return header;
    }

    public void setHeader(double header) {
        this.header = header;
    }

    public double getFreeKick() {
        return freeKick;
    }

    public void setFreeKick(double freeKick) {
        this.freeKick = freeKick;
    }
}
