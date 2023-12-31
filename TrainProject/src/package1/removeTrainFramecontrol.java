package package1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class removeTrainFramecontrol {

    @FXML
    private ComboBox<String> remove;

    @FXML
    private Button removetrain;

    @FXML
    void removeE(ActionEvent event) {
        String val = remove.getValue();
        String x = "";
        int i = 2;
        while (val.charAt(i) != ' ') {
            x += val.charAt(i);
            i++;
        }
        try {
            remove_train.remove_train(x);
        } catch (Exception e) {
        }
        remove.setValue("");
        remove.getItems().clear();
    }

    @FXML
    void backE(ActionEvent event) {
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
    }

    @FXML
    void removetrainE(ActionEvent event) {
        remove.setValue("Choose");
        remove.getItems().clear();

        try {
            String url = "jdbc:mysql://localhost:3306/train";
            Connection con = DriverManager.getConnection(url, "root", "root");
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from train");
            int N = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                String s = "ID", id = "";
                for (int i = 1; i <= N; i++) {
                    if (i > 1)
                        s += " - ";
                    if (i == 1)
                        id = rs.getString(i);
                    s += rs.getString(i);
                }
                remove.getItems().add(s);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
