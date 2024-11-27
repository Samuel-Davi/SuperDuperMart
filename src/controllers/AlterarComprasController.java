package controllers;

import java.math.BigDecimal;

import dao.ComprasDAO;
import dao.ProdutosDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Compras;
import utils.ConfirmationMessage;
import utils.ErrorMessage;
import utils.SuccessMessage;
import view.App;

public class AlterarComprasController {

    ComprasDAO cdao = new ComprasDAO();
    ProdutosDAO pdao = new ProdutosDAO();

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonConfirmar;

    @FXML
    private TextArea descricaoProduto;

    @FXML
    private ChoiceBox<String> comboBoxFornecedor;

    @FXML
    private ComboBox<String> comboBoxId;

    @FXML
    private ChoiceBox<String> comboBoxProduto;

    @FXML
    private TextField precoField;

    @FXML
    private TextField quantidadeField;

    @FXML
    void cancelar(ActionEvent event) throws Exception {
        if(ConfirmationMessage.showConfirmationMessage(
            "Cancelar Alteração",
            "Deseja cancelar a alteração?", 
            "Sim",
            "Não")){
                App.changeScene("../view/MenuWindow.fxml");
            }
    }

    @FXML
    void confirmarAlteracao(ActionEvent event) throws Exception {
        Compras compra = new Compras(
            Integer.parseInt(comboBoxId.getValue()),
            comboBoxProduto.getValue(),
            new BigDecimal(precoField.getText()),
            Integer.valueOf(quantidadeField.getText()));
        cdao.alterarCompra(compra);
        SuccessMessage.showSucessMessage(
            "Sucesso!",
            "Alteração feita com sucesso!\nObs: alteração na compra não afeta o estoque do produto!!!");
        App.changeScene("../view/MenuWindow.fxml");
    }

    @FXML
    void initialize() throws Exception {
        if(cdao.getIds()==null){
            ErrorMessage.showErrorMessage(
                "Erro!",
                "Não há vendas para alterar");
            App.changeScene("../view/MenuWindow.fxml");
        }
        comboBoxId.getItems().addAll(cdao.getIds());
        comboBoxProduto.getItems().addAll(pdao.getNomeProdutos());
        comboBoxId.valueProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue!= null &&!newValue.equals(oldValue)){
                mudaInformacoes(newValue);
            }
        });
    }

    void mudaInformacoes(String newValue){
        if(newValue == null) return;

        Compras c = cdao.getCompraPorIds(newValue);
        comboBoxProduto.setValue(c.getNomeProduto());
        precoField.setText(String.valueOf(c.getPrecoUnitario()));
        quantidadeField.setText(String.valueOf(c.getQuantidade()));
        // TODO: preencher os campos com os dados da compra com id newValue
    }

}
