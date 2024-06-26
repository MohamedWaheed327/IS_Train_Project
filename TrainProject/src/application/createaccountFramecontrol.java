package application;

import java.io.IOException;
import functions.*;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class createaccountFramecontrol {

    @FXML
    private Button back;

    @FXML
    private RadioButton female;

    @FXML
    private ToggleGroup group1;

    @FXML
    private RadioButton male;

    @FXML
    private Button signup;

    @FXML
    private TextField tfnationalid;

    @FXML
    private TextField tfpassword;

    @FXML
    private TextField tfuseremail;

    @FXML
    private TextField tfusername;

    @FXML
    private TextField tfuserphone;

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
    void signupE(ActionEvent event) throws Exception {
        String user_name_ = tfusername.getText();
        String user_password = tfpassword.getText();
        String user_phone = tfuserphone.getText();
        String user_email = tfuseremail.getText();
        String national_id = tfnationalid.getText();
        String gender = "female";
        if (male.isSelected()) {
            gender = "male";
        }

        if (user_name_.isEmpty() || user_password.isEmpty() || user_email.isEmpty() || user_phone.isEmpty()
                || national_id.isEmpty() || !(female.isSelected() || male.isSelected())) {
            JOptionPane.showMessageDialog(null, "please fill out all the fields");
        } else if (!not_unique.fun("user_name_", "user_db", "\"" + user_name_ + "\"")) {
            add_user.fun(national_id, user_name_, user_password, user_email, user_phone, gender);
            JOptionPane.showMessageDialog(null, "Account created successfully");
        } else {
            JOptionPane.showMessageDialog(null, "This user name already exists!!");
        }
    }

}
