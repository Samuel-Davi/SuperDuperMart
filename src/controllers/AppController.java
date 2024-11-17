package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.App;

public class AppController {

    
    @FXML
    private Button btnAperta;

    @FXML
    void apertou(ActionEvent event) throws Exception {
        App.changeScene("../view/LoginWindow.fxml");
    }

}
