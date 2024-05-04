package application;

import java.io.IOException;
import functions.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class adminDashboardFramecontrol {

    @FXML
    private Button addtickets;

    @FXML
    private Button addtrain;

    @FXML
    private Button removetrain;

    @FXML
    private Button back;

    @FXML
    void backE(ActionEvent event) {
        Stage primaryStage = new Stage();
        clear.fun();
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

    @FXML
    void addticketsE(ActionEvent event) {
        Stage primaryStage = new Stage();
        clear.fun();
        variables.openStages.add(primaryStage);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addTicketFrame.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (IOException e) {
        }
        primaryStage.setTitle("Your Train App");
        primaryStage.show();
    }

    @FXML
    void addtrainE(ActionEvent event) {
        Stage primaryStage = new Stage();
        clear.fun();
        variables.openStages.add(primaryStage);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addTrainFrame.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (IOException e) {
        }
        primaryStage.setTitle("Your Train App");
        primaryStage.show();
    }

    @FXML
    void removetrainE(ActionEvent event) {
        Stage primaryStage = new Stage();
        clear.fun();
        variables.openStages.add(primaryStage);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("removeTrainFrame.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (IOException e) {
        }
        primaryStage.setTitle("Your Train App");
        primaryStage.show();
    }

}
