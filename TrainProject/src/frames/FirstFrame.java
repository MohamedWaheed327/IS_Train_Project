package frames;
import functions.*;

import java.io.IOException;

import application.variables;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class FirstFrame extends Application{

    public void start(Stage primaryStage) {
        clear.clear();
        variables.openStages.add(primaryStage);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FirstFrame.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (IOException e) {
        }
        primaryStage.setTitle("Your Train App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}