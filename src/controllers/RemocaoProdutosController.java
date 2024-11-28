package controllers;

import dao.ProdutosDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import utils.ConfirmationMessage;
import view.App;

public class RemocaoProdutosController {

    @FXML
    private Button buttonCancela;

    @FXML
    private Button buttonConfirma;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    void initialize(){
        comboBox.getItems().addAll(new ProdutosDAO().getNomeProdutos());
    }

    @FXML
    void cancela(ActionEvent event) throws Exception {
        App.changeScene("../view/MenuWindow.fxml");
    }

    @FXML
    void confirma(ActionEvent event) throws Exception {
        boolean rs = ConfirmationMessage.showConfirmationMessage(
            "Confirmação",
            "Tem certeza que deseja apagar este produto?",
            "Sim",
            "Não");

        if(rs){

            new ProdutosDAO().deleteProduto(new ProdutosDAO().getIdPorNome(comboBox.getValue()));
            App.changeScene("../view/MenuWindow.fxml");
        }
    }

}
