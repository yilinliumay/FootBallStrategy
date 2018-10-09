package FootballField.Model;

import java.util.HashMap;

public class Discipline {

    private double redCard;
    private double yellowCard;



    public void getData(HashMap<String,Double> attackRecord){
        for (String key : attackRecord.keySet()) {
            double value=attackRecord.get(key);
            switch (key){
                case "red" :redCard=value;
                case "yellow" :yellowCard=value;

            }
        }
    }

    //Getter and Setter
    public double getRedCard() {
        return redCard;
    }

    public void setRedCard(double redCard) {
        this.redCard = redCard;
    }

    public double getYellowCard() {
        return yellowCard;
    }

    public void setYellowCard(double yellowCard) {
        this.yellowCard = yellowCard;
    }
}
