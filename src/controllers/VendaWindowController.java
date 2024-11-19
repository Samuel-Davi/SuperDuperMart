package controllers;

import dao.VendaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import utils.ConfirmationMessage;
import utils.ErrorMessage;
import view.App;

public class VendaWindowController {

    VendaDAO vdao = new VendaDAO();

    @FXML
    private ComboBox<String> boxProdutos;

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonConfirma;

    @FXML
    private ComboBox<String> formaPagamento;

    @FXML
    private TextField quantidadeVenda;

    @FXML
    private TextField trocoVenda;

    @FXML
    private TextField valorPagoVenda;

    @FXML
    private TextField valorTotalVenda;

    @FXML
    private TextField valorUnitarioVenda;

    @FXML
    void cancelaVenda(ActionEvent event) throws Exception {
        boolean realmenteCancela = ConfirmationMessage.showConfirmationMessage(
            "Confirmação",
            "Você realmente deseja cancelar a venda?",
            "Sim",
            "Não");
        
        if(realmenteCancela) App.changeScene("../view/MenuWindow.fxml");
    }

    @FXML
    void confirmaVenda(ActionEvent event) {
        if(boxProdutos.getValue() == null || quantidadeVenda.getText() == null ||
         valorUnitarioVenda.getText() == null || valorPagoVenda.getText() == null || formaPagamento.getValue() == null){
            ErrorMessage.showErrorMessage(
                "Erro!",
                "Preencha os campos corretamente"
            );
            return;
        }
    }

    @FXML
    void initialize(){
        vdao.getNomesProdutos(boxProdutos);
        carregaFormasDePagamento(formaPagamento);
        quantidadeVenda.textProperty().addListener((observable, oldValue, newValue) -> {
            Double valorUnitario = 0.0;
            if(!valorUnitarioVenda.getText().isEmpty()) valorUnitario = Double.valueOf(valorUnitarioVenda.getText());
            if (!(newValue.equals(oldValue))){
                Double val = 0.0;
                if(newValue.endsWith(".")) newValue  = newValue + "0";
                if ((!newValue.isEmpty())){
                    val = Integer.valueOf(newValue)*valorUnitario;
                }
                valorTotalVenda.setText(val.toString());
            }
        });
        valorUnitarioVenda.textProperty().addListener((observable, oldValue, newValue) -> {
            Double valorQuantidade = 0.0;
            if(!quantidadeVenda.getText().isEmpty()) valorQuantidade = Double.valueOf(quantidadeVenda.getText());
            if (!(newValue.equals(oldValue))){
                Double val = 0.0;
                if(newValue.endsWith(".")) newValue  = newValue + "0";//verificando quando colocar a casa decimal
                if ((!newValue.isEmpty())){
                    val = Integer.valueOf(newValue)*valorQuantidade;
                }
                valorTotalVenda.setText(val.toString());
            }
        });
        
    }

    void carregaFormasDePagamento(ComboBox<String> formaPagamento){
        formaPagamento.getItems().add("Pix");
        formaPagamento.getItems().add("Dinheiro");
        formaPagamento.getItems().add("Débito");
        formaPagamento.getItems().add("Crédito");
    }

}
