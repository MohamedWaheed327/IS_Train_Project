package application;


import java.io.IOException;
import functions.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class userDashboardFramecontrol {

    @FXML
    private Button back;

    @FXML
    private Button bookedtickets;

    @FXML
    private Button bookticket;

    @FXML
    private Label lbwelcome;

    @FXML
    void backE(ActionEvent event) {
        Stage primaryStage = new Stage();
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


    @FXML
    void bookticketE(ActionEvent event) {
        Stage primaryStage = new Stage();
        clear.clear();
        variables.openStages.add(primaryStage);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("bookTicketFrame.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (IOException e) {
        }
        primaryStage.setTitle("Your Train App");
        primaryStage.show();
    }

}