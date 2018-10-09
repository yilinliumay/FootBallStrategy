package FootballField.MainField;

import Data.ReadGames;
import Data.ReadPlayers;
import FootballField.Model.*;
import FootballField.PlayerInformation.PlayerInfoController;
import FootballField.SchemeResult.ExceptionBoxController;
import FootballField.SchemeResult.SchemeResultController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class MainFieldController {

    @FXML
    private StackPane stackPane;



    @FXML
    void initialize() {
        drawBackground();
    }

    // draw football Background
    private void drawBackground(){
        FieldBackground fieldBackground=new FieldBackground();
        fieldBackground.setId("myCanvas");
        fieldBackground.setMouseTransparent(true);
        stackPane.getChildren().add(fieldBackground);
        // bind the size of canvas with the size of stackPane
        fieldBackground.widthProperty().bind(stackPane.widthProperty());
        fieldBackground.heightProperty().bind(stackPane.heightProperty());
    }

    // button Action
    // select formation(eg: 5-3-2, 4-4-2..) by clicking image button
    public void ToScheme(ActionEvent event){

        // only the selected GridPane can be visible, and other GridPanes in StackPane is invisible.
       setVisible("pane"+((Button)event.getSource()).idProperty().getValue().substring(6));

    }

    // button Action
    // check performance of every player by clicking image button
    @FXML
    public void TurnToPlayerInfo(ActionEvent event){

        // get the playerID by the ID of clicked button.  The image button id and player ID is the same.
        String playerID=((Button)event.getSource()).idProperty().getValue();
        String playerName=new ReadPlayers().GetName(playerID);

        // turn to PlayerInfo.fxml
        try {
            Stage AlertPlayer=new Stage();
            AlertPlayer.setTitle(playerName+" Performance");
            AlertPlayer.initModality(Modality.APPLICATION_MODAL);


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FootballField/PlayerInformation/PlayerInfo.fxml"));
            AnchorPane root= loader.load();

            PlayerInfoController controller=loader.getController();

            // get the player with his performance, name
            Player player=new ReadGames().getPlayerInfo(playerID);

            // pass Player to PlayerInfo.fxml to show information
            controller.setPlayer(player);

            Scene scene = new Scene(root);
            AlertPlayer.setScene(scene);
            AlertPlayer.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    // Label Action
    // Handle Drag action
    @FXML
    public void handleDragDetection(MouseEvent mouseEvent){
        Label label=(Label) mouseEvent.getSource();
        Dragboard db=label.startDragAndDrop(TransferMode.ANY);
        ClipboardContent clipboardContent=new ClipboardContent();
        clipboardContent.putString(label.getText());
        db.setContent(clipboardContent);
        mouseEvent.consume();
    }

    @FXML
    public void handelDragOver(DragEvent dragEvent){
        if(dragEvent.getDragboard().hasString()){
            dragEvent.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    public void handelDrop(DragEvent dragEvent){
        String str=dragEvent.getDragboard().getString();
        Label label=(Label) dragEvent.getGestureTarget();
        label.setText(str);
    }

    @FXML
    public void handelDragDone(DragEvent dragEvent){
        Label label=(Label) dragEvent.getGestureSource();
        label.setTextFill(Color.RED);
        //label.setMouseTransparent(true);
    }


    // button Action
    // Turn to SchemeResult.fxml
    @FXML
    public void TurnToSchemeResult(ActionEvent event){

        // judge whether there are no DuplicateException and IntegrityException
        boolean open=true;

        GridPane gridPane=(GridPane)((Button)event.getSource()).getParent();

        Scheme scheme=new Scheme(gridPane.idProperty().getValue().substring(4));

        // set Scheme's Defenders, Forwards, MidFields
        for (Node node : gridPane.getChildren()) {
            if(node.getTypeSelector().equals("Label")) {
                try {

                    Player player = new ReadGames().getPlayerInfo(new ReadPlayers().GetID(((Label) node).getText()));
                    if(player.getID().equals(" ")) {
                        throw new IntegrityException();
                    }

                    if (node.idProperty().getValue().contains("DF")) {
                        scheme.addPlayer(player, "defender");
                    } else if (node.idProperty().getValue().contains("FW")) {
                        scheme.addPlayer(player,"forward");
                    } else if (node.idProperty().getValue().contains("MF")) {
                        scheme.addPlayer(player,"midField");
                    } else if (node.idProperty().getValue().contains("GK")) {
                        scheme.addPlayer(player,"goalKeeper");
                    }
                // to avoid one player is placed in multiple positions.
                }catch (DuplicateException d){
                    showErrorMessage(d.getMessage());
                    open=false;
                    break;
                    // to avoid there is no player in one or more positions.
                }catch (IntegrityException e){
                    showErrorMessage(e.getMessage());
                    open=false;
                    break;
                }
            }
        }

        if(open) {
            calculateAndShowScheme(scheme);
        }
    }


    // calculate final evaluation
    private void calculateAndShowScheme(Scheme scheme){
        SchemeEnum schemeAttack = null, schemeDefense = null;

        switch (scheme.getName()) {
            case "442":
                schemeAttack = SchemeEnum.Attack442;
                schemeDefense = SchemeEnum.Defend442;
                break;
            case "343":
                schemeAttack = SchemeEnum.Attack343;
                schemeDefense = SchemeEnum.Defend343;
                break;
            case "433":
                schemeAttack = SchemeEnum.Attack433;
                schemeDefense = SchemeEnum.Defend433;
                break;
            case "532":
                schemeAttack = SchemeEnum.Attack532;
                schemeDefense = SchemeEnum.Defend532;
        }

        double attackPT = scheme.ComputeAttack(schemeAttack);
        double defendPT = scheme.ComputeDefend(schemeDefense);
        double[] discipline=scheme.ComputeDiscipline();

        try {
            Stage resultBox = new Stage();
            resultBox.setTitle("Evaluation");
            resultBox.initModality(Modality.APPLICATION_MODAL);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FootballField/SchemeResult/SchemeResult.fxml"));
            AnchorPane root = loader.load();

            SchemeResultController controller = loader.getController();
            controller.setScheme(attackPT, defendPT, discipline);

            Scene scene = new Scene(root);
            resultBox.setScene(scene);
            resultBox.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void setVisible(String paneName){


        ObservableList<Node> childs = stackPane.getChildren();
        if (childs.size() > 0) {
            for(Node child:childs){
                if(child.idProperty().getValue().equals(paneName)){
                    child.setVisible(true);
                }
                else if( child.idProperty().getValue().equals("myCanvas")){
                    child.setVisible(true);
                }
                else{
                    child.setVisible(false);
                }
            }
        }

    }

    // Turn to ExceptionBox.fxml
    private void showErrorMessage(String message){
        try {
            Stage exceptionBox=new Stage();
            exceptionBox.setTitle("Warning");
            exceptionBox.initModality(Modality.APPLICATION_MODAL);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FootballField/SchemeResult/ExceptionBox.fxml"));
            AnchorPane root= loader.load();

            ExceptionBoxController controller=loader.getController();
            controller.setMessage(message);

            Scene scene = new Scene(root, 600,100);
            exceptionBox.setScene(scene);
            exceptionBox.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }








}
