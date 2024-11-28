package controllers;

import dao.AdminDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Admin;
import utils.ConfirmacaoAdmin;
import utils.ErrorMessage;
import utils.SuccessMessage;
import view.App;

public class CadastroController {

    AdminDAO adao = new AdminDAO();

    @FXML
    private ComboBox<String> boxPapel;

    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField senhaField;

    @FXML
    private TextField usernameField;

    @FXML
    void addUser(ActionEvent event) throws Exception {
        if(usernameField.getText().isEmpty() || senhaField.getText().isEmpty() || boxPapel.getValue().isEmpty()){
            ErrorMessage.showErrorMessage("Erro", "Preencha os campos!");
            return;
        }
        boolean admConfirma = ConfirmacaoAdmin.mostrarJanela(App.getMainStage());
        if(admConfirma){
            Admin a =  new Admin(usernameField.getText(), senhaField.getText(), boxPapel.getValue());
            if(adao.addUsuario(a)){
                SuccessMessage.showSucessMessage(
                    "Sucesso", "Usuario inserido com sucesso!");
            }else{
                ErrorMessage.showErrorMessage("Erro", "Usuario com esse nome ja existe!");
            }
            App.changeScene("../view/LoginWindow.fxml");
        }
    }

    @FXML
    void initialize(){
        boxPapel.getItems().addAll("adm", "funcionario");
    }

    @FXML
    void login(ActionEvent event) throws Exception {
        App.changeScene("../view/LoginWindow.fxml");
    }

}
