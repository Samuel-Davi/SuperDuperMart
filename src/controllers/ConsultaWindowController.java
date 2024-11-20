package controllers;

import dao.ComprasDAO;
import dao.ProdutosDAO;
import dao.VendaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import model.Compras;
import model.Produtos;
import model.Vendas;
import view.App;

public class ConsultaWindowController {

    VendaDAO vdao = new VendaDAO();
    ProdutosDAO pdao = new ProdutosDAO();
    ComprasDAO cdao = new ComprasDAO();

    @FXML
    private Button buttonVoltar;

    @FXML
    private ComboBox<String> comboBoxSelecionado;

    @FXML
    private ComboBox<String> comboBoxTipo;

    @FXML
    private TextArea textArea;

    @FXML
    void voltar(ActionEvent event) throws Exception {
        App.changeScene("../view/MenuWindow.fxml");
    }

    @FXML
    void initialize() throws Exception {
        comboBoxTipo.getItems().addAll("vendas", "compras", "produtos");
        comboBoxTipo.valueProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue!= null &&!newValue.equals(oldValue)){
                if(newValue.equals("vendas")){
                    comboBoxSelecionado.getItems().clear();
                    comboBoxSelecionado.getItems().addAll(vdao.getIds());
                }
                if(newValue.equals("compras")){
                    comboBoxSelecionado.getItems().clear();
                    comboBoxSelecionado.getItems().addAll(cdao.getIds());
                }
                if(newValue.equals("produtos")){
                    comboBoxSelecionado.getItems().clear();
                    comboBoxSelecionado.getItems().addAll(pdao.getNomeProdutos());
                }
            }
        });
        comboBoxSelecionado.valueProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue != null && !newValue.equals(oldValue)){
                if(comboBoxTipo.getValue().equals("vendas")){
                    textArea.setText(impressaoVenda(vdao.getVendasPorIds(newValue)));
                }
                if(comboBoxTipo.getValue().equals("compras")){
                    textArea.setText(impressaoCompra(cdao.getCompraPorIds(newValue)));
                }
                if(comboBoxTipo.getValue().equals("produtos")){
                    textArea.setText(impressaoProduto(pdao.getProdutoPorNome(newValue)));
                }
            }
        });
    }

    String impressaoProduto(Produtos p){
        return "Nome Produto: " + p.getNome_produto() + "\n" +
                "Descrição: " + p.getDescricao() + "\n" +
                "Preço de Compra: " + p.getPreco_compra() + "\n" +
                "Preço de Venda: " + p.getPreco_venda() + "\n" +
                "Estoque Atual: " + p.getEstoque_atual() + "\n" +
                "Data do Cadastro: " + pdao.getDataPorNome(p.getNome_produto());
    }

    String impressaoCompra(Compras c){
        return "Compra: " + c.getId() + "\n" +
                "Nome Produto: " + c.getNomeProduto() + "\n" +
                "Descrição do Produto: " + c.getDescricao() + "\n" +
                "Quantidade: " + c.getQuantidade() + "\n" +
                "Preço Unitário: " + c.getPrecoUnitario() + "\n" +
                "Data da Compra: " + cdao.getDataPorId(c.getId());
    }

    String impressaoVenda(Vendas v){
        return "Venda: " + v.getId() + "\n" +
                "Nome Produto: " + v.getNomeProduto() + "\n" +
                "Valor Pago: " + v.getValorPago() + "\n" +
                "Troco: " + v.getTroco() + "\n" +
                "Forma de Pagamento: " + v.getFormaPagamento() + "\n" +
                "Data da Venda: " + vdao.getDataPorId(v.getId());
    }

}
