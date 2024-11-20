package utils;

import java.util.ArrayList;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class QuestionMessage {
    public static String showQuestionMessage(ArrayList<String> options){

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(options);
        comboBox.setValue(options.getFirst()); // Valor padrão

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Escolha uma opção:");
        alert.setHeaderText("Selecione uma opção abaixo:");

        VBox content = new VBox(comboBox);
        content.setSpacing(10);
        alert.getDialogPane().setContent(content);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            return comboBox.getValue();
        }
        return "";

    }
}
