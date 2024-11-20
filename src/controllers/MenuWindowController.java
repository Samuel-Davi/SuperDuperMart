package controllers;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import utils.ConfirmationMessage;
import utils.QuestionMessage;
import view.App;

public class MenuWindowController {

    @FXML
    private Button buttonAlterar;

    @FXML
    private Button buttonComprar;

    @FXML
    private Button buttonConsultar;

    @FXML
    private Button buttonNovaCompra;

    @FXML
    private Button buttonNovaVenda;

    @FXML
    private Button buttonRelatorios;

    @FXML
    private Button buttonRemocao;

    @FXML
    private Button buttonSair;

    @FXML
    void alterar(ActionEvent event) throws Exception {
        ArrayList <String> options = new ArrayList<>();
        options.add("produtos");
        options.add("compras");
        options.add("vendas");
        String result = QuestionMessage.showQuestionMessage(options);

        if(result == "produtos") App.changeScene("../view/AlterarProdutos.fxml");
        if(result == "compras") App.changeScene("../view/AlterarCompras.fxml");
        if(result == "vendas") App.changeScene("../view/AlterarVendas.fxml");
    }

    @FXML
    void comprar(ActionEvent event) throws Exception {
        App.changeScene("../view/ComprarWindow.fxml");
    }

    @FXML
    void consultar(ActionEvent event) {

    }

    @FXML
    void remover(ActionEvent event) {

    }

    @FXML
    void sair(ActionEvent event) {
        boolean result = ConfirmationMessage.showConfirmationMessage("Saída", "Tem certeza que deseja sair?",
        "Sim", "Não");
        if(result) System.exit(0);
    }

    @FXML
    void vender(ActionEvent event) throws Exception {
        App.changeScene("../view/VendaWindow.fxml");
    }

    @FXML
    void verLucro(ActionEvent event) {

    }

    @FXML
    void verRelatorios(ActionEvent event) {

    }

}
