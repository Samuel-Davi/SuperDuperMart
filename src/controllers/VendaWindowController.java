package controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;

import dao.VendaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import utils.ConfirmationMessage;
import utils.ErrorMessage;
import utils.SuccessMessage;
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
    void confirmaVenda(ActionEvent event) throws Exception {
        if(boxProdutos.getValue() == null || quantidadeVenda.getText() == null ||
         valorUnitarioVenda.getText() == null || valorPagoVenda.getText() == null || formaPagamento.getValue() == null){
            ErrorMessage.showErrorMessage(
                "Erro!",
                "Preencha os campos corretamente"
            );
            return;
        }
        if(!quantidadeVenda.getText().matches("\\d+(\\.\\d+)?")||
            !valorUnitarioVenda.getText().matches("\\d+(\\.\\d+)?")||
            !valorPagoVenda.getText().matches("\\d+(\\.\\d+)?")){
            ErrorMessage.showErrorMessage(
                "Erro!",
                "Preencha os campos corretamente"
            );
            return;
        }

        boolean resultVenda = vdao.addVenda(Integer.parseInt(quantidadeVenda.getText()),
        Double.parseDouble(valorUnitarioVenda.getText()), Double.parseDouble(valorTotalVenda.getText()),
        Double.parseDouble(valorPagoVenda.getText()), Double.parseDouble(trocoVenda.getText()),
        boxProdutos.getValue(), formaPagamento.getValue());

        if(!resultVenda){
            ErrorMessage.showErrorMessage("Erro!", "Falha ao realizar a venda.");
            limpaCampos();
            return;
        }

        boolean resultConfirmation = ConfirmationMessage.showConfirmationMessage("Venda feita com sucesso!", "Deseja efetuar outra venda?",
        "Sim", "Não");

        if(resultConfirmation){
            limpaCampos();
        }else{
            App.changeScene("../view/MenuWindow.fxml");
        }
    }

    void limpaCampos(){
        boxProdutos.setValue(null);
        quantidadeVenda.clear();
        valorUnitarioVenda.clear();
        valorTotalVenda.setText("0.00");
        valorPagoVenda.clear();
        trocoVenda.setText("0.00");
        formaPagamento.setValue(null);
    }

    @FXML
    void initialize(){
        vdao.getNomesProdutos(boxProdutos);
        carregaFormasDePagamento(formaPagamento);
        
        boxProdutos.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null && !newValue.equals(oldValue)){
                mudaValorUnitario(newValue);
            }
        });

        quantidadeVenda.textProperty().addListener((observable, oldValue, newValue) -> {
            Double valorUnitario = 0.0;
            Integer estoqueProduto = vdao.getEstoqueProduto(boxProdutos.getValue());
            if(!newValue.isEmpty()){
                if(Integer.valueOf(newValue) > estoqueProduto){
                    ErrorMessage.showErrorMessage("Estoque insuficiente!", "Estoque insuficiente para a quantidade escolhida.");
                    quantidadeVenda.setText(estoqueProduto.toString());
                }
                if(!valorUnitarioVenda.getText().isEmpty()) valorUnitario = Double.valueOf(valorUnitarioVenda.getText());
            if (!(newValue.equals(oldValue))){
                Double val = 0.0;
                if(newValue.endsWith(".")) {
                    ErrorMessage.showErrorMessage("Erro", "Não é possivel colocar decimal na quantidade!");
                    newValue = "1";
                    quantidadeVenda.setText("0");
                }
                val = Integer.valueOf(newValue)*valorUnitario;
                BigDecimal bd = new BigDecimal(Double.toString(val));
                bd = bd.setScale(2, RoundingMode.HALF_UP); // Arredonda para 2 casas decimais
                Double arredondado = bd.doubleValue();
                valorTotalVenda.setText(arredondado.toString());
            }
            }
            
        });
        valorUnitarioVenda.textProperty().addListener((observable, oldValue, newValue) -> {
            Integer valorQuantidade = 0;
            if(!quantidadeVenda.getText().isEmpty()) valorQuantidade = Integer.valueOf(quantidadeVenda.getText());
            if (!(newValue.equals(oldValue))){
                Double val = 0.0;
                if(newValue.endsWith(".")) newValue  += "0";
                if ((!newValue.isEmpty())){
                    val = Double.valueOf(newValue)*valorQuantidade;
                }
                BigDecimal bd = new BigDecimal(Double.toString(val));
                bd = bd.setScale(2, RoundingMode.HALF_UP); // Arredonda para 2 casas decimais
                Double arredondado = bd.doubleValue();
                valorTotalVenda.setText(arredondado.toString());
            }
        });
        valorPagoVenda.textProperty().addListener((observable, oldValue, newValue) ->{
            Double valorTotal = Double.valueOf(valorTotalVenda.getText());;
            if (!(newValue.equals(oldValue))){
                Double val = 0.0;
                if(newValue.endsWith(".")) newValue  += "0";
                if ((!newValue.isEmpty())){
                    val = Double.valueOf(newValue) - valorTotal;
                }
                if (val < 0.0) val = 0.0;
                BigDecimal bd = new BigDecimal(Double.toString(val));
                bd = bd.setScale(2, RoundingMode.HALF_UP); // Arredonda para 2 casas decimais
                Double arredondado = bd.doubleValue();
                trocoVenda.setText(arredondado.toString());
            }
        });
        
    }

    void mudaValorUnitario(String nomeProduto){
        Double valorUnitario = vdao.getValorUnitarioPorNome(nomeProduto);
        if(valorUnitario!= 0.0) valorUnitarioVenda.setText(valorUnitario.toString());
        else {
            ErrorMessage.showErrorMessage("Erro!", "Valor Unitário do produto não encontrado.");
            valorUnitarioVenda.setText("Erro");
        } 
    }

    void carregaFormasDePagamento(ComboBox<String> formaPagamento){
        formaPagamento.getItems().addAll("Pix", "Dinheiro", "Débito", "Crédito");
    }

}
