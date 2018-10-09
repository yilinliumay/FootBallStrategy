package FootballField.SchemeResult;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ExceptionBoxController {
    @FXML
    private Label message;


    // this is used to pass parameter message with MainFieldController
    public void setMessage(String mess){
     message.setText(mess);
    }
}
