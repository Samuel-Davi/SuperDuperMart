package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    void alterar(ActionEvent event) {

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
