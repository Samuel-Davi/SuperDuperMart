package controllers;

import java.util.Random;

import dao.ComprasDAO;
import dao.FornecedoresDAO;
import dao.ProdutosDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Fornecedores;
import model.Produtos;
import utils.SuccessMessage;
import view.App;

public class ComprarWindowController {

    FornecedoresDAO fdao = new FornecedoresDAO();
    ProdutosDAO pdao = new ProdutosDAO();
    ComprasDAO cdao = new ComprasDAO();

    @FXML
    private Button buttonConfirmar;

    @FXML
    private TextField contatoFornecedorField;

    @FXML
    private TextField nomeFornecedorField;

    @FXML
    private TextField nomeProduto;

    @FXML
    private TextField precoProduto;

    @FXML
    private TextField quantidadeProduto;

    @FXML
    void comprar(ActionEvent event) throws Exception {

        Double precoProdutoDouble = Double.parseDouble(precoProduto.getText());
        Double precoDeVenda = precoProdutoDouble*0.3 + precoProdutoDouble;

        Fornecedores f = new Fornecedores(nomeFornecedorField.getText(), contatoFornecedorField.getText());
        Produtos p = new Produtos(nomeProduto.getText(), precoProdutoDouble,
         precoDeVenda, Integer.parseInt(quantidadeProduto.getText()));
        if(!fdao.fornecedorExiste(f)) fdao.addFornecedor(f);

        if(!pdao.ProdutoExiste(p)){
            pdao.addProduto(p);
        }else{
            pdao.alterarProduto(p.getNome_produto(), precoProdutoDouble, precoDeVenda, p.getEstoque_atual());
        }

        cdao.addCompras(p, f, Integer.parseInt(quantidadeProduto.getText()));

        SuccessMessage.showSucessMessage("Sucesso!", "Compra feita com sucesso!");

        App.changeScene("../view/MenuWindow.fxml");

    }

}
