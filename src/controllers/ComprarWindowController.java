package controllers;

import java.math.BigDecimal;
import java.util.Random;

import dao.ComprasDAO;
import dao.FornecedoresDAO;
import dao.ProdutosDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Fornecedores;
import model.Produtos;
import utils.ErrorMessage;
import utils.SuccessMessage;
import view.App;

public class ComprarWindowController {

    FornecedoresDAO fdao = new FornecedoresDAO();
    ProdutosDAO pdao = new ProdutosDAO();
    ComprasDAO cdao = new ComprasDAO();

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonConfirmar;

    @FXML
    private ComboBox<String> comboBoxProdutos;

    @FXML
    private TextField nomeProduto;

    @FXML
    private TextArea descricaoProduto;

    @FXML
    private TextField precoProduto;

    @FXML
    private TextField quantidadeProduto;

    @FXML
    void initialize(){
        comboBoxProdutos.getItems().addAll(pdao.getNomeProdutos());
        comboBoxProdutos.getItems().add("novo");

        comboBoxProdutos.valueProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue!= null &&!newValue.equals(oldValue)){
                mudaInformacoes(newValue);
            }
        });
    }

    void mudaInformacoes(String newValue){
        if(newValue == "novo"){
            nomeProduto.clear();
            nomeProduto.setEditable(true);
            precoProduto.clear();
            descricaoProduto.setEditable(true);
            descricaoProduto.clear();
            precoProduto.setEditable(true);
            quantidadeProduto.clear();
        }else{
            Produtos p = pdao.getProdutoPorNome(newValue);
            nomeProduto.setText(p.getNome_produto());
            nomeProduto.setEditable(false);
            descricaoProduto.setText(p.getDescricao());
            descricaoProduto.setEditable(false);
            precoProduto.setText(p.getPreco_compra().toString());
            precoProduto.setEditable(false);
        }
    }

    @FXML
    void comprar(ActionEvent event) throws Exception {

        if(descricaoProduto.getText().isEmpty() || nomeProduto.getText().isEmpty() || precoProduto.getText().isEmpty() || quantidadeProduto.getText().isEmpty()){
            System.out.println("Erro na compra");
            ErrorMessage.showErrorMessage(
                "Erro!",
                "Preencha os campos corretamente"
            );
            return;
        }

        Double precoProdutoDouble = Double.parseDouble(precoProduto.getText());
        Double precoDeVenda = precoProdutoDouble*0.3 + precoProdutoDouble;

        Produtos p = new Produtos(nomeProduto.getText(), descricaoProduto.getText(),new BigDecimal(precoProduto.getText()),
         new BigDecimal(precoDeVenda), Integer.parseInt(quantidadeProduto.getText()));

        if(!pdao.ProdutoExiste(p)){
            pdao.addProduto(p);
        }else{
            pdao.atualizaEstoque(p.getNome_produto(), p.getEstoque_atual());
        }

        cdao.addCompras(p,Integer.parseInt(quantidadeProduto.getText()));

        SuccessMessage.showSucessMessage("Sucesso!", "Compra feita com sucesso!");

        App.changeScene("../view/MenuWindow.fxml");

    }

    @FXML
    void cancelar(ActionEvent event) throws Exception {
        App.changeScene("../view/MenuWindow.fxml");
    }

}
