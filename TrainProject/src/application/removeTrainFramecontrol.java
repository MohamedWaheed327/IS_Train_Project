package application;

import java.io.IOException;

import javax.swing.JOptionPane;

import functions.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class removeTrainFramecontrol {

    @FXML
    private ComboBox<String> remove;

    @FXML
    private Button removetrain;

    public void initialize() throws Exception {
        for (String s : get_train_list.fun()) {
            remove.getItems().add(s);
        }
    }

    @FXML
    void removeE(ActionEvent event) throws Exception {
        String val = remove.getValue();
        String x = "";
        int i = 2;
        while (val.charAt(i) != ' ') {
            x += val.charAt(i++);
        }
        variables.train_id = x;
    }

    @FXML
    void backE(ActionEvent event) throws IOException {
        variables.train_id = "";
        Stage primaryStage = new Stage();
        clear.fun();
        variables.openStages.add(primaryStage);
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("adminDashboardFrame.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Your Train App");
        primaryStage.show();
    }

    @FXML
    void removetrainE(ActionEvent event) throws Exception {
        if (variables.train_id.length() != 0) {
            remove_train.fun(variables.train_id);
            variables.train_id = "";
            JOptionPane.showMessageDialog(null, "Train " + variables.train_id + " removed successfully.");

            Stage primaryStage = new Stage();
            clear.fun();
            variables.openStages.add(primaryStage);
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("adminDashboardFrame.fxml")));
            primaryStage.setScene(scene);
            primaryStage.setTitle("Your Train App");
            primaryStage.show();
        } else {
            JOptionPane.showMessageDialog(null, "Choose a Train to remove.");
        }
    }
}