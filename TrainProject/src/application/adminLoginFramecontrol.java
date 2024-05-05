package application;

import java.awt.HeadlessException;
import java.io.IOException;
import functions.*;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    void signinE(ActionEvent event) throws HeadlessException, IOException, Exception {
        String user_name_ = tfusername.getText();
        String user_password = passwordtf.getText();

        if (user_name_.isEmpty()) {
            Stage primaryStage = new Stage();
            clear.fun();
            variables.openStages.add(primaryStage);
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("adminDashboardFrame.fxml")));
            primaryStage.setScene(scene);
            primaryStage.setTitle("Your Train App");
            primaryStage.show();
            JOptionPane.showMessageDialog(null, "please enter a user name");
        } else if (user_password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "please enter a password");
        } else if (is_valid_admin.fun(user_name_, user_password)) {
            Stage primaryStage = new Stage();
            clear.fun();
            variables.openStages.add(primaryStage);
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("adminDashboardFrame.fxml")));
            primaryStage.setScene(scene);
            primaryStage.setTitle("Your Train App");
            primaryStage.show();
        } else {
            JOptionPane.showMessageDialog(null, "wrong user name or password");
        }

    }

}
