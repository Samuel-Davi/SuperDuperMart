package utils;

import dao.AdminDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmacaoAdmin {
    // Método para exibir a janela modal
    public static boolean mostrarJanela(Stage parentStage) {
        // Variável para capturar a confirmação
        final boolean[] confirmado = {false};

        // Criar o Stage (janela modal)
        Stage janela = new Stage();
        janela.initModality(Modality.APPLICATION_MODAL); // Modal: bloqueia a janela principal
        janela.initOwner(parentStage); // Define o Stage pai
        janela.setTitle("Confirmação de Administrador");

        // Layout
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setHgap(10);
        layout.setVgap(10);

        // Campos da janela
        Label lblNome = new Label("Nome do Administrador:");
        TextField campoNome = new TextField();

        Label lblSenha = new Label("Senha de Administrador:");
        PasswordField campoSenha = new PasswordField();

        Button btnConfirmar = new Button("Confirmar");
        Button btnCancelar = new Button("Cancelar");

        // Ação ao confirmar
        btnConfirmar.setOnAction(e -> {
            String nome = campoNome.getText();
            String senha = campoSenha.getText();

            // Simular validação (substituir pela lógica real de validação)
            if (validarAdministrador(nome, senha)) {
                confirmado[0] = true;
                janela.close(); // Fecha a janela
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Nome ou senha incorretos. Tente novamente.", ButtonType.OK);
                alert.initOwner(janela); // Mostra erro sobre o modal
                alert.showAndWait();
            }
        });

        // Ação ao cancelar
        btnCancelar.setOnAction(e -> {
            confirmado[0] = false;
            janela.close();
        });

        // Adicionar os elementos ao layout
        layout.add(lblNome, 0, 0);
        layout.add(campoNome, 1, 0);
        layout.add(lblSenha, 0, 1);
        layout.add(campoSenha, 1, 1);
        layout.add(btnConfirmar, 0, 2);
        layout.add(btnCancelar, 1, 2);

        // Configurar a cena
        Scene scene = new Scene(layout, 400, 200);
        janela.setScene(scene);
        janela.showAndWait(); // Aguarda até que o modal seja fechado

        return confirmado[0];
    }

    // Método simulado para validação do administrador
    private static boolean validarAdministrador(String nome, String senha) {
        AdminDAO dao = new AdminDAO();
        return dao.validarAdministrador(nome, senha);
    }
}
