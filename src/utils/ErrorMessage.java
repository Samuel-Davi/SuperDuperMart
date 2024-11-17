package utils;

import javafx.scene.control.Alert;

public class ErrorMessage {
    public static void showErrorMessage(String title, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
