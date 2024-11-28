package controllers;

import java.util.ArrayList;

import dao.AdminDAO;
import dao.ComprasDAO;
import dao.ProdutosDAO;
import dao.VendaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Admin;
import utils.ConfirmationMessage;
import utils.ErrorMessage;
import utils.QuestionMessage;
import view.App;

public class MenuWindowController {

    ProdutosDAO pdao = new ProdutosDAO();
    VendaDAO vdao = new VendaDAO();
    ComprasDAO cdao = new ComprasDAO();
    AdminDAO adao = new AdminDAO();


    @FXML
    private Button buttonAlterar;

    @FXML
    private Button buttonLucro;

    @FXML
    private Button buttonConsultar;

    @FXML
    private Button buttonNovaCompra;

    @FXML
    private Button buttonNovaVenda;

    @FXML
    private Button buttonRelatorios;

    @FXML
    private Button buttonRemocao;

    @FXML
    private Button buttonSair;

    @FXML
    void alterar(ActionEvent event) throws Exception {
        Admin adm = adao.getUsuarioAtivo();
        String nome = adm.getUsername();
        boolean res = adao.isAdm(nome);
        if(res){
            ArrayList <String> options = new ArrayList<>();
            options.add("produtos");
            options.add("compras");
            options.add("vendas");
            String result = QuestionMessage.showQuestionMessage(options);

            if(result == "produtos"){
                if(pdao.getNomeProdutos() == null || pdao.getNomeProdutos().size() == 0){
                    ErrorMessage.showErrorMessage(
                    "Erro na alteração",
                    "Nenhum produto cadastrado!"
                    );
                    return;
                }else{
                    App.changeScene("../view/AlterarProdutos.fxml");
                }
            }
            if(result == "compras"){
                if(cdao.getIds()==null || cdao.getIds().size()==0){
                    ErrorMessage.showErrorMessage(
                        "Erro!",
                        "Não há compras para alterar");
                    return;
                } else{
                    App.changeScene("../view/AlterarCompras.fxml");
                }
            }
            if(result == "vendas"){
                if(vdao.getIds()==null || vdao.getIds().size()==0){
                    ErrorMessage.showErrorMessage(
                        "Erro!",
                        "Não há vendas para alterar");
                    return;
                }else{
                    App.changeScene("../view/AlterarVendas.fxml");
                }
            }
        }else{
            ErrorMessage.showErrorMessage(
                "Erro!",
                "Você não possui permissão para alterar!"
            );
        }
    }

    @FXML
    void comprar(ActionEvent event) throws Exception {
        App.changeScene("../view/ComprarWindow.fxml");
    }

    @FXML
    void consultar(ActionEvent event) throws Exception {
        if((pdao.getNomeProdutos() == null || pdao.getNomeProdutos().size() == 0) && 
        (cdao.getIds().size() == 0 || cdao.getIds() == null) && (vdao.getIds().size() == 0 || vdao.getIds() == null)){
            ErrorMessage.showErrorMessage(
            "Erro na consulta",
            "Nenhum valor cadastrado!"
            );
            return;
        }else{
            App.changeScene("../view/ConsultaWindow.fxml");
        }
    }

    @FXML
    void remover(ActionEvent event) throws Exception {
        Admin adm = adao.getUsuarioAtivo();
        String nome = adm.getUsername();
        boolean res = adao.isAdm(nome);
        if(res){
            if(pdao.getNomeProdutos() == null || pdao.getNomeProdutos().size() == 0){
                ErrorMessage.showErrorMessage(
                "Erro na remoção!",
                "Nenhum produto cadastrado!"
                );
                return;
            }else{
                App.changeScene("../view/RemocaoProdutos.fxml");
            }
        }else{
            ErrorMessage.showErrorMessage(
                "Erro!",
                "Você não possui permissão para remover!"
            );
            return;
        }
    }

    @FXML
    void sair(ActionEvent event) throws Exception {
        boolean result = ConfirmationMessage.showConfirmationMessage("Saída", "Tem certeza que deseja sair?",
        "Sim", "Não");
        if(result) App.changeScene("../view/LoginWindow.fxml");;
    }

    @FXML
    void vender(ActionEvent event) throws Exception {
        if(cdao.getIds().size() == 0){
            ErrorMessage.showErrorMessage(
            "Erro na inserção de venda",
            "Nenhuma compra cadastrada!"
            );
            return;
        }
        App.changeScene("../view/VendaWindow.fxml");
    }

    @FXML
    void verLucro(ActionEvent event) throws Exception {
        Admin adm = adao.getUsuarioAtivo();
        String nome = adm.getUsername();
        boolean res = adao.isAdm(nome);
        if(res){
            if(vdao.getIds().size() == 0 && cdao.getIds().size() == 0){
                ErrorMessage.showErrorMessage(
                "Erro na verificação de lucro",
                "Nenhuma venda e compra cadastrada!"
                );
                return;
            }
            App.changeScene("../view/LucroGeral.fxml");
        }else{
            ErrorMessage.showErrorMessage(
                "Erro!",
                "Você não possui permissão para ver o lucro geral!"
            );
            return;
        }
    }

    @FXML
    void verRelatorios(ActionEvent event) {

    }

}
