package application;


import java.io.IOException;
import functions.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FirstFramecontrol {

    @FXML
    private Button signinasadmin;

    @FXML
    private Button signinasuser;

    @FXML
    void signinasadminE(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        clear.clear();
        variables.openStages.add(primaryStage);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("adminLoginFrame.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (IOException e) {
        }
        primaryStage.setTitle("Your Train App");
        primaryStage.show();
    }

    @FXML
    void signinasuserE(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        clear.clear();
        variables.openStages.add(primaryStage);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("userLoginFrame.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (IOException e) {
        }
        primaryStage.setTitle("Your Train App");
        primaryStage.show();
    }
}
