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

public class userLoginFramecontrol {

    @FXML
    private Button back;

    @FXML
    private Button createaccount;

    @FXML
    private Button signin;

    @FXML
    private TextField tfpassword;

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
    void createaccountE(ActionEvent event) {
        Stage primaryStage = new Stage();
        clear.clear();
        variables.openStages.add(primaryStage);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("createaccountFrame.fxml"));
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
        String user_password = tfpassword.getText();
        try {
            if (user_name_.isEmpty()) {
                JOptionPane.showMessageDialog(null, "please enter the user name");
            } else if (user_password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "please enter the password");
            } else if (is_valid_user.is_valid_user(user_name_, user_password)) {
                variables.curUser = user_name_;
                Stage primaryStage = new Stage();
                clear.clear();
                variables.openStages.add(primaryStage);
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("userDashboardtFrame.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                } catch (IOException e) {
                }
                primaryStage.setTitle("Your Train App");
                primaryStage.show();
            } else {
                JOptionPane.showMessageDialog(null, "invalid user name or password");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
