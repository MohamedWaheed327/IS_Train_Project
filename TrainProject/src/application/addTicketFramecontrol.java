package application;

import functions.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addTicketFramecontrol {

    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private ComboBox<String> choose;

    @FXML
    private TextField cost;

    @FXML
    private TextField endtime;

    @FXML
    private Button select;

    @FXML
    private TextField starttime;

    @FXML
    void addE(ActionEvent event) {
        String currentdate = LocalDate.now().toString();
        String start_time = "'" + currentdate + " " + starttime.getText() + ":00:00'";
        String end_time = "'" + currentdate + " " + endtime.getText() + ":00:00'";
        String cost_ = cost.getText();

        String train_id = variables.train_id;
        try {
            String url = "jdbc:mysql://localhost:3306/train";
            Connection con = DriverManager.getConnection(url, "root", "root");
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from train where train_id = " + train_id);
            rs.next();
            for (int i = 1; i <= Integer.valueOf(rs.getString(2)); i++) {
                add_ticket.fun(Integer.toString(i) + train_id, train_id,
                        rs.getString(3), rs.getString(4), start_time, end_time, cost_, Integer.toString(i));
            }
            JOptionPane.showMessageDialog(null, "tickets added successfully");
            con.close();
        } catch (Exception e) {
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

    @FXML
    void chooseE(ActionEvent event) {
        String val = choose.getValue();
        String x = "";
        int i = 2;
        while (val.charAt(i) != ' ') {
            x += val.charAt(i);
            i++;
        }
        variables.train_id = x;
    }

    @FXML
    void selectE(ActionEvent event) {
        choose.getItems().clear();

        try {
            String url = "jdbc:mysql://localhost:3306/train";
            Connection con = DriverManager.getConnection(url, "root", "root");
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from train");
            int N = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                String s = "ID";
                for (int i = 1; i <= N; i++) {
                    if (i > 1)
                        s += " - ";
                    s += rs.getString(i);
                }
                choose.getItems().add(s);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
