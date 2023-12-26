package package1;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class adminLoginFramecontrol {

    @FXML
    private Button back;

    @FXML
    private TextField passwordtf;

    @FXML
    private Button signin;

    @FXML
    private TextField tfusername;

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
    void signinE(ActionEvent event) {
        String user_name_ = tfusername.getText();
        String user_password = passwordtf.getText();
        try {
            if (user_name_.isEmpty()) {
                JOptionPane.showMessageDialog(null, "please enter a user name");
            } else if (user_password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "please enter a password");
            } else if (is_valid_admin.is_valid_admin(user_name_, user_password)) {
                Stage primaryStage = new Stage();
                clear.clear();
                variables.openStages.add(primaryStage);
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("adminDashboardFrame.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                } catch (IOException e) {
                }
                primaryStage.setTitle("Your Train App");
                primaryStage.show();
            } else {
                JOptionPane.showMessageDialog(null, "wrong user name or password");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

}
