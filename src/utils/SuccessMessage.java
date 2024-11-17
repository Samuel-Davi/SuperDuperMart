package utils;

import javafx.scene.control.Alert;

public class SuccessMessage {
    public static void showSucessMessage(String title, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
}