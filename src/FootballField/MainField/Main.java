package FootballField.MainField;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("FootballStrategyApp");
        initFootballFieldLayout();

    }


    private void initFootballFieldLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("MainField.fxml"));
            HBox root=loader.load();
            Scene scene = new Scene(root, 1000,600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Football Tactics");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
