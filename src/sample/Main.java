package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    public static Stage primaryStage;
    @Override
    public void start(Stage _primaryStage) throws Exception
    {
        primaryStage = _primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("loginFormat.fxml"));
        primaryStage.setTitle("Krispy Kreme Login");
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
