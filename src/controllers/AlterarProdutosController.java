package controllers;

import java.util.ArrayList;

import dao.ProdutosDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Produtos;
import utils.ConfirmationMessage;
import utils.ErrorMessage;
import utils.SuccessMessage;
import view.App;

public class AlterarProdutosController {

    ProdutosDAO pdao = new ProdutosDAO();

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonConfirmar;

    @FXML
    private TextArea descProduto;

    @FXML
    private ComboBox<String> comboBoxProdutos;

    @FXML
    private TextField estoqueAtual;

    @FXML
    private TextField precoCompra;

    @FXML
    private TextField precoVenda;


    @FXML
    void initialize() throws Exception{
        ArrayList<String> nomeProdutos = pdao.getNomeProdutos();
        comboBoxProdutos.getItems().addAll(nomeProdutos);
        comboBoxProdutos.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!= null && !newValue.equals(oldValue)){
                mudaInformacoes(newValue);
            }
        });
    }

    void mudaInformacoes(String newValue){
        Produtos p = pdao.getProdutoPorNome(newValue);
        precoCompra.setText(String.valueOf(p.getPreco_compra()));
        descProduto.setText(p.getDescricao());
        precoVenda.setText(String.valueOf(p.getPreco_venda()));
        estoqueAtual.setText(String.valueOf(p.getEstoque_atual()));
    }

    @FXML
    void cancelarAlteracao(ActionEvent event) throws Exception {
        boolean result = ConfirmationMessage.showConfirmationMessage(
            "Cancelar Alteracao",
            "Tem certeza que deseja cancelar a alteração?",
            "Sim",
            "Não"
        );
        if(result) App.changeScene("../view/MenuWindow.fxml");
    }

    @FXML
    void confirmaAlteracao(ActionEvent event) throws Exception {
        if(comboBoxProdutos.getValue() == null || precoCompra.getText().isEmpty() ||
        precoVenda.getText().isEmpty() || estoqueAtual.getText().isEmpty()){
            ErrorMessage.showErrorMessage(
                "Erro na alteração",
                "Preencha os campos corretamente!");
            return;
        }

        boolean result = ConfirmationMessage.showConfirmationMessage(
            "Confirmar Alteracao",
            "Tem certeza que deseja confirmar a alteração?",
            "Sim",
            "Não"
        );

        if(result) {
            pdao.alterarProduto(comboBoxProdutos.getValue(),
            descProduto.getText(),
            Double.parseDouble(precoCompra.getText()), 
            Double.parseDouble(precoVenda.getText()),
            Integer.parseInt(estoqueAtual.getText()));
            SuccessMessage.showSucessMessage(
                "Sucesso!",
                "Alteração realizada com sucesso!"
            );
            App.changeScene("../view/MenuWindow.fxml");
        }
            
    }

}
