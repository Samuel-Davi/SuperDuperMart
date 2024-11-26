package controllers;

import dao.LucroDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import view.App;

public class LucroGeralController {

    LucroDAO ldao = new LucroDAO();

    @FXML
    private Button buttonVoltar;

    @FXML
    private TextField lucro;

    @FXML
    private TextField quantidadeProdutos;

    @FXML
    private TextField valorCompras;

    @FXML
    private TextField valorFinanceiro;

    @FXML
    private TextField valorVendas;

    @FXML
    void voltar(ActionEvent event) throws Exception {
        App.changeScene("../view/MenuWindow.fxml");
    }

    @FXML
    void initialize() {
        Double resValorCompra = ldao.getValorCompras(), resValorVenda = ldao.getValorVendas();
        valorCompras.setText(resValorCompra.toString());
        valorVendas.setText(resValorVenda.toString());
        lucro.setText(Double.valueOf(resValorVenda - resValorCompra).toString());
        quantidadeProdutos.setText(ldao.getQuantidadeTotal().toString());
        valorFinanceiro.setText(ldao.getValorFinanceiro().toString());
    }

}
