package FootballField.PlayerInformation;

import Data.ReadPlayers;
import FootballField.MainField.Main;
import FootballField.MainField.MainFieldController;
import FootballField.Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


import java.io.IOException;
import java.util.HashMap;

public class PlayerInfoController {
    @FXML
    private Label won;
    @FXML
    private Label drawn;
    @FXML
    private Label lost;
    @FXML
    private Label total;
    @FXML
    private Label name;
    @FXML
    private Label broken;
    @FXML
    private Label interception;
    @FXML
    private Label clearance;
    @FXML
    private Label forcedGrab;
    @FXML
    private Label headerClearance;
    @FXML
    private Label keeperRescue;
    @FXML
    private Label dribble;
    @FXML
    private Label pitching;
    @FXML
    private Label cross;
    @FXML
    private Label shooting;
    @FXML
    private Label header;
    @FXML
    private Label freeKick;
    @FXML
    private Label yellow;
    @FXML
    private Label red;




    private Player player;

    @FXML
    void initialize() {

    }

    // show data
    private void showDetail(Player player){

        name.setText(player.getName());
        total.setText(Integer.toString(player.getPlayerPerformances().size()));
        HashMap<Result, Integer> resultMap=player.getResultMap();
        won.setText(Integer.toString(resultMap.get(Result.WON)));
        drawn.setText(Integer.toString(resultMap.get(Result.DRAWN)));
        lost.setText(Integer.toString(resultMap.get(Result.LOST)));

        PlayerPerformance averagePerformance=player.getAveragePerformance();

        Defense averageDefense=averagePerformance.getDefense();

        setTextFormat(broken,averageDefense.getBroken());
        setTextFormat(interception,averageDefense.getInterception());
        setTextFormat(clearance,averageDefense.getClearance());
        setTextFormat(forcedGrab,averageDefense.getForcedGrab());
        setTextFormat(headerClearance,averageDefense.getHeaderClearance());
        setTextFormat(keeperRescue,averageDefense.getGoalkeeperRescue());

        Attack averageAttack=averagePerformance.getAttack();

        setTextFormat(dribble,averageAttack.getDribble());
        setTextFormat(pitching,averageAttack.getPitching());
        setTextFormat(cross,averageAttack.getCross());
        setTextFormat(shooting,averageAttack.getShooting());
        setTextFormat(header,averageAttack.getHeader());
        setTextFormat(freeKick,averageAttack.getFreeKick());

        Discipline averageDiscipline=averagePerformance.getDiscipline();


        yellow.setText(String.format("%.0f",averageDiscipline.getYellowCard()));
        red.setText(String.format("%.0f",averageDiscipline.getRedCard()));


    }

    // this is used to pass parameter Player with MainFieldController
    public  void setPlayer(Player player){
        this.player=player;
        showDetail(player);
    }

    private void setTextFormat(Label label, double data){
        label.setText(String.format("%.1f",data*100)+"%");
    }


}
