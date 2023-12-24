package package1;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class App extends Application {
    public void start(Stage primaryStage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("FirstFrame.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("Your Train App");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
        }
        // FirstFrame x = new FirstFrame();
        // x.FirstFrame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}