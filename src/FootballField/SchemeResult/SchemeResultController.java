package FootballField.SchemeResult;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class SchemeResultController {

    @FXML
    private Label attack;
    @FXML
    private Label defense;
    @FXML
    private Label red;
    @FXML
    private Label yellow;
    @FXML
    private Label assess;

    public void setScheme(double attack, double defense,double[] discipline){
        setTextFormat(this.attack,attack);
        setTextFormat(this.defense,defense);
        red.setText(String.format("%.0f",discipline[0]));
        yellow.setText(String.format("%.0f",discipline[1]));
        assess.setText("Attack: "+evaluation(attack)+"\n"+"Defense: "+evaluation(defense));
    }

    private void setTextFormat(javafx.scene.control.Label label, double data){
        label.setText(String.format("%.1f",data*100)+"%");
    }

    private String evaluation(double data){
        String assessment="";

        if(data<0.5 && data>0.3){
            assessment="You can improve it...";
        }
        else if(data<0.3){
            assessment="Please check your choice carefully!!";
        }
        else if(data>0.5){
            assessment="Good Job!";
        }

        return assessment;
    }




}
