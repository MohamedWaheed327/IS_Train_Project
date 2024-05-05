package application;

import java.io.IOException;
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
        }
        variables.TrainList = get_train_list.fun();
        for (String s : variables.TrainList) {
            remove.getItems().add(s);
        }
    }
}
