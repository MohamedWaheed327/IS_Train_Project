package application;

import functions.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class bookTicketFramecontrol {

    @FXML
    private Button back;

    @FXML
    private Button book;

    @FXML
    private ComboBox<String> destination;

    @FXML
    private Button search;

    @FXML
    private ComboBox<String> seat;

    @FXML
    private TextField time;

    @FXML
    void backE(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        clear.fun();
        variables.openStages.add(primaryStage);
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("userDashboardFrame.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Your Train App");
        primaryStage.show();
    }

    @FXML
    void bookE(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Ticket booked successfully.");
    }

    @FXML
    void destinationE(ActionEvent event) throws SQLException {
        String id = "";
        int i = 0;
        String d = destination.getValue();
        while (d.charAt(i) != ' ') {
            id += d.charAt(i);
            i++;
        }
        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");
        java.sql.Statement st = con.createStatement();
        ResultSet rs = st
                .executeQuery("select * from seat where train_id = " + id + " and visited = false");

        int N = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            String s = "";
            for (i = 1; i <= N; i++) {
                if (i > 1)
                    s += " - ";
                s += rs.getString(i);
            }
            seat.getItems().add(s);
        }
        con.close();
    }

    @FXML
    void searchE(ActionEvent event) {
        String currentdate = LocalDate.now().toString();
        String hour = "'" + currentdate + " " + time.getText() + ":00:00'";
        try {
            String url = "jdbc:mysql://localhost:3306/train";
            Connection con = DriverManager.getConnection(url, "root", "root");
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st
                    .executeQuery("select train_id, start_station, end_station from ticket where start_time = " + hour);
            int N = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                String s = "";
                for (int i = 1; i <= N; i++) {
                    if (i > 1)
                        s += " - ";
                    s += rs.getString(i);
                }
                destination.getItems().add(s);
            }
            con.close();
        } catch (Exception e) {
        }
    }

    @FXML
    void seatE(ActionEvent event) throws Exception {
        String x = seat.getValue();
        int i = 0;
        String seat_id = "", train_id = "", visited = "";
        while (x.charAt(i) != ' ') {
            seat_id += x.charAt(i);
            i++;
        }
        i += 3;
        while (x.charAt(i) != ' ') {
            train_id += x.charAt(i);
            i++;
        }
        i += 3;
        while (i < x.length()) {
            visited += x.charAt(i);
            i++;
        }
        System.out.println(visited);
        query.fun("insert into booked_tickets values(" + seat_id + train_id + ",\"" + variables.curUser + "\")");
        query.fun("update seat set visited = true where seat_id = " + seat_id);
    }

}
