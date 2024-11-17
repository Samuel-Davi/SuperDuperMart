package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    
        private static Stage mainStage;
        public static void main(String[] args) throws Exception {
            launch(args);
        }
    
        @Override
        public void start(Stage primStage) throws Exception {
            mainStage = primStage;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./App.fxml"));
            Parent root = loader.load();
            Scene tela = new Scene(root);
    
            mainStage.setTitle("Super Duper Mart");
            mainStage.setScene(tela);
            mainStage.show();
    
        }
    
        public static void changeScene(String fxml) throws Exception{
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
            Scene scene = new Scene(loader.load());           
            mainStage.setScene(scene);
        }
}
