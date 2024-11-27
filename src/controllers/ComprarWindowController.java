package controllers;

import java.math.BigDecimal;

import dao.ComprasDAO;
import dao.ProdutosDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Produtos;
import utils.ErrorMessage;
import utils.SuccessMessage;
import view.App;

public class ComprarWindowController {

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
    private TextField qtdUM;

    @FXML
    private ComboBox<String> UM;

    @FXML
    private ComboBox<String> comboBoxMarca;

    @FXML
    private TextField novaMarca;

    @FXML
    private TextField precoProduto;

    @FXML
    private TextField quantidadeProduto;

    @FXML
    void initialize(){
        comboBoxProdutos.getItems().addAll(pdao.getIdsProdutos());
        comboBoxProdutos.getItems().add("novo");
        UM.getItems().addAll("l", "kg", "m", "g", "ml");

        comboBoxMarca.getItems().addAll(pdao.getMarcas());
        comboBoxMarca.getItems().add("nova");

        comboBoxMarca.valueProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue == "nova"){
                novaMarca.clear();
                novaMarca.setDisable(false);
            }else{
                novaMarca.clear();
                novaMarca.setDisable(true);
            }
        });

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
            qtdUM.clear();
            qtdUM.setEditable(true);
            UM.setDisable(false);
            comboBoxMarca.setDisable(false);
            precoProduto.clear();
            precoProduto.setEditable(true);
            quantidadeProduto.clear();
        }else{
            Produtos p = pdao.getProdutoPorId(newValue);
            nomeProduto.setText(p.getNome_produto());
            nomeProduto.setEditable(false);
            qtdUM.setText(p.getQtdUM());
            qtdUM.setEditable(false);
            UM.setValue(p.getUM());
            UM.setDisable(true);
            comboBoxMarca.setValue(p.getMarca());
            comboBoxMarca.setDisable(true);
            precoProduto.setText(p.getPreco_compra().toString());
            precoProduto.setEditable(false);
        }
    }

    @FXML
    void comprar(ActionEvent event) throws Exception {

        if(qtdUM.getText().isEmpty() || nomeProduto.getText().isEmpty() || precoProduto.getText().isEmpty() || 
        quantidadeProduto.getText().isEmpty() || UM.getValue().isEmpty() || comboBoxMarca.getValue().isEmpty()){
            System.out.println("Erro na compra");
            ErrorMessage.showErrorMessage(
                "Erro!",
                "Preencha os campos corretamente"
            );
            return;
        }

        Double precoProdutoDouble = Double.parseDouble(precoProduto.getText());
        Double precoDeVenda = precoProdutoDouble*0.3 + precoProdutoDouble;

        Integer id = 0;
        if(comboBoxProdutos.getValue() !=  "novo"){
            id = Integer.parseInt(comboBoxProdutos.getValue());
        }
        String marca = "";
        if(comboBoxMarca.getValue() == "nova"){
            marca = novaMarca.getText();
        }else{
            marca = comboBoxMarca.getValue();
        }
        String nomeProdutoGeral = nomeProduto.getText() + " " + "(" + qtdUM.getText() + "," + UM.getValue() + ")";
        Produtos p = new Produtos(id,nomeProdutoGeral, marca,new BigDecimal(precoProduto.getText()),
         new BigDecimal(precoDeVenda), Integer.parseInt(quantidadeProduto.getText()));
        
        if(!pdao.MarcaExiste(marca)){
            pdao.addMarca(marca);
        }
        
        if(!pdao.ProdutoExiste(p)){
            pdao.addProduto(p);
            p.setId(pdao.getIdPorNome(p.getNomeTotal()));
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
