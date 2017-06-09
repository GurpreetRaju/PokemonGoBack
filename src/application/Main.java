package application;
import controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
    
        @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(Main.class.getResource("/application/sample.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        loader.setController(GameController.getInstance());
        Parent root = loader.load();

        GameController.getInstance().makeUIResponsive();
        primaryStage.setTitle("PokemonGB");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        GameController.getInstance().init();
    }
    public static void main(String[] args) 
    {
        launch(args);
    }
}
