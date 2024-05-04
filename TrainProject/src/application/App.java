package application;

import java.io.IOException;
import functions.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class App extends Application {
    public void start(Stage primaryStage) throws IOException {
        clear.clear();
        variables.openStages.add(primaryStage);
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FirstFrame.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Your Train App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}