package application;

import java.io.IOException;

import javax.swing.JOptionPane;
import functions.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addTrainFramecontrol {

    @FXML
    private Button addtrain;

    @FXML
    private Button back;

    @FXML
    private TextField endstation;

    @FXML
    private TextField seatnumber;

    @FXML
    private TextField startstation;

    @FXML
    private TextField trainid;

    @FXML
    void addtrainE(ActionEvent event) {
        String train_id = trainid.getText();
        String seat_number = seatnumber.getText();
        String start_station = startstation.getText();
        String end_station = endstation.getText();
        try {
            if (not_unique.fun("train_id", "train", train_id)) {
                JOptionPane.showMessageDialog(null, "Train already exists!!");
            } else {
                add_train.fun(train_id, seat_number, start_station, end_station);
                JOptionPane.showMessageDialog(null, "Train added successfully!!");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    void backE(ActionEvent event) {
        Stage primaryStage = new Stage();
        clear.fun();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("adminDashboardFrame.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (IOException e) {
        }
        primaryStage.setTitle("Your Train App");
        primaryStage.show();
    }

}
