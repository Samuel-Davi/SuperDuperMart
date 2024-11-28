package controllers;

import java.math.BigDecimal;
import java.util.ArrayList;

import dao.ProdutosDAO;
import dao.VendaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Compras;
import model.Produtos;
import model.Vendas;
import utils.ConfirmationMessage;
import utils.ErrorMessage;
import utils.SuccessMessage;
import view.App;

public class AlterarVendaController {

    VendaDAO vdao = new VendaDAO();
    ProdutosDAO pdao = new ProdutosDAO();

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonConfirmar;

    @FXML
    private ComboBox<String> comboBoxId;

    @FXML
    private ChoiceBox<String> comboBoxProduto;

    @FXML
    private ComboBox<String> formaPagamento;

    @FXML
    private TextField quantidadeField;

    @FXML
    private TextField valorPago;

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
        Produtos produto = pdao.getProdutoPorId(comboBoxId.getValue());

        Vendas venda = new Vendas(
            Integer.parseInt(comboBoxId.getValue()), 
            new BigDecimal(valorPago.getText()), 
            formaPagamento.getValue(), 
            new BigDecimal(Integer.parseInt(quantidadeField.getText())*Double.valueOf(produto.getPreco_venda().toString())), 
            pdao.getProdutoPorId(comboBoxProduto.getValue()), 
            produto.getPreco_venda(), 
            Integer.parseInt(quantidadeField.getText()));
        vdao.alterarVenda(venda);
        SuccessMessage.showSucessMessage(
            "Sucesso!",
            "Alteração feita com sucesso!\nObs: alteração na venda não afeta o estoque do produto!!!");
        App.changeScene("../view/MenuWindow.fxml");
    }

    @FXML
    void initialize() throws Exception {
        ArrayList<String> formasdepagamento = new ArrayList<String>();
        formasdepagamento.add("Dinheiro");
        formasdepagamento.add("Pix");
        formasdepagamento.add("Débito");
        formasdepagamento.add("Crédito");
        comboBoxId.getItems().addAll(vdao.getIds());
        formaPagamento.getItems().addAll(formasdepagamento);
        comboBoxProduto.getItems().addAll(pdao.getIdsProdutos());
        comboBoxId.valueProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue!= null &&!newValue.equals(oldValue)){
                mudaInformacoes(newValue);
            }
        });
    }

    void mudaInformacoes(String newValue){
        if(newValue == null) return;

        Vendas v = vdao.getVendasPorIds(newValue);
        formaPagamento.setValue(v.getFormaPagamento());
        comboBoxProduto.setValue(String.valueOf(v.getProduto().getId()));
        valorPago.setText(String.valueOf(v.getValorPago()));
        quantidadeField.setText(String.valueOf(v.getQuantidade()));
        // TODO: preencher os campos com os dados da compra com id newValue
    }

}
