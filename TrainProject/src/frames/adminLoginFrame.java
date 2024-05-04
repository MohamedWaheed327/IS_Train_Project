package frames;
import functions.*;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class adminLoginFrame extends Application {

    public void start(Stage primaryStage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("adminLoginFrame.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Your Train App");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}