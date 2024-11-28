package controllers;

import dao.AdminDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Admin;
import utils.ErrorMessage;
import utils.SuccessMessage;
import view.App;

public class LoginWindowController {

    AdminDAO dao = new AdminDAO();
    Admin admin = new Admin(null, null, null);

    @FXML
    private Button btnCadastro;

    @FXML
    private Button btnConfirm;

    @FXML
    private PasswordField senhaField;

    @FXML
    private TextField usernameField;

    @FXML
    void addUser(ActionEvent event) throws Exception {
        App.changeScene("../view/CadastroWindow.fxml");
    }

    @FXML
    void authenticate(ActionEvent event) throws Exception {
        admin.setSenha((senhaField.getText()));
        admin.setUsername((usernameField.getText()));
        if(admin.getSenha() ==  "" || admin.getUsername() == ""){
            System.out.println("erro no login");
            ErrorMessage.showErrorMessage(
                "Erro!",
                "Preencha os campos corretamente"
            );
            return;
        }
        if(dao.authenticate(admin)){
            System.out.println("sucesso no login");
            SuccessMessage.showSucessMessage(
                "Sucesso!",
                "Login realizado com sucesso!"
            );
            App.changeScene("../view/MenuWindow.fxml");
        }else{
            System.out.println("erro no login");
            ErrorMessage.showErrorMessage(
                "Erro!",
                "Usuário ou senha incorretos!"
            );
        } 
        usernameField.clear();
        senhaField.clear();
    }

}
