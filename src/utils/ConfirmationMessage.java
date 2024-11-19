package utils;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ConfirmationMessage {
    public static boolean showConfirmationMessage(String title, String content,
     String textButtonPositivo, String textButtonNegativo){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(content);

        alert.getButtonTypes().clear();

        // Adiciona botões personalizados
        ButtonType simButton = new ButtonType(textButtonPositivo);
        ButtonType naoButton = new ButtonType(textButtonNegativo);

        alert.getButtonTypes().addAll(simButton, naoButton);

        Optional<ButtonType> button = alert.showAndWait();

        return button.isPresent() && button.get() == simButton;
    }
}
