package application;

import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JOptionPane;
import functions.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    void addtrainE(ActionEvent event) throws HeadlessException, Exception {
        String train_id = trainid.getText();
        String seat_number = seatnumber.getText();
        String start_station = startstation.getText();
        String end_station = endstation.getText();

        if (train_id.length() == 0 || seat_number.length() == 0 || start_station.length() == 0 || end_station.length() == 0) {
            JOptionPane.showMessageDialog(null, "fill out all fields.");
            return;
        }

        if (not_unique.fun("train_id", "train", train_id)) {
            JOptionPane.showMessageDialog(null, "Train already exists!!");
        } else {
            add_train.fun(train_id, seat_number, start_station, end_station);
            JOptionPane.showMessageDialog(null, "Train added successfully!!");
        }
    }

    @FXML
    void backE(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        clear.fun();
        variables.openStages.add(primaryStage);
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("adminDashboardFrame.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Your Train App");
        primaryStage.show();
    }

}
