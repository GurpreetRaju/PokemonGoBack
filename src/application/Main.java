package application;
import controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
        @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(Main.class.getResource("/application/sample.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        
        GameController controller = new GameController();
        
        loader.setController(controller);
        Parent root = loader.load();
        primaryStage.setTitle("PokemonGB");
        primaryStage.setScene(new Scene(root, 671, 622));
        primaryStage.setResizable(false);
        primaryStage.show();
        
        controller.toss();
    }
    public static void main(String[] args) 
    {
        launch(args);
    }
}
