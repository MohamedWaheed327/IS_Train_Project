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
    void backE(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        clear.fun();
        variables.openStages.add(primaryStage);
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FirstFrame.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Your Train App");
        primaryStage.show();
    }

    @FXML
    void createaccountE(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        clear.fun();
        variables.openStages.add(primaryStage);
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("createaccountFrame.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Your Train App");
        primaryStage.show();
    }

    @FXML
    void signinE(ActionEvent event) throws HeadlessException, Exception {
        String user_name_ = tfusername.getText();
        String user_password = tfpassword.getText();

        if (user_name_.isEmpty()) {
            JOptionPane.showMessageDialog(null, "please enter the user name");
        } else if (user_password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "please enter the password");
        } else if (is_valid_user.fun(user_name_, user_password)) {
            variables.curUser = user_name_;
            Stage primaryStage = new Stage();
            clear.fun();
            variables.openStages.add(primaryStage);
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("userDashboardFrame.fxml")));
            primaryStage.setScene(scene);
            primaryStage.setTitle("Your Train App");
            primaryStage.show();
        } else {
            JOptionPane.showMessageDialog(null, "invalid user name or password");
        }
    }

}
