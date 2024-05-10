package application;

import functions.*;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class bookTicketFramecontrol {

    @FXML
    private Button back;

    @FXML
    private Button book;

    @FXML
    private ComboBox<String> departureTime;

    @FXML
    private ComboBox<String> destination;

    @FXML
    private Label info;

    @FXML
    private ComboBox<String> seat;

    @FXML
    private ComboBox<String> startStation;

    private String seat_id, train_id, ticket_id;

    public void initialize() throws Exception {
        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");
        java.sql.Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from train");
        Set<String> S = new HashSet<>();
        while (rs.next()) {
            S.add(rs.getString(4));
        }
        for (String str : S) {
            destination.getItems().add(str);
        }
        con.close();
    }

    @FXML
    void backE(ActionEvent event) throws Exception {
        Stage primaryStage = new Stage();
        clear.fun();
        variables.openStages.add(primaryStage);
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("userDashboardFrame.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Your Train App");
        primaryStage.show();
    }

    @FXML
    void bookE(ActionEvent event) throws Exception {
        query.fun("insert into booked_tickets values(\"" + ticket_id + "\",\"" + variables.curUser + "\")");
        query.fun("update seat set visited = true where train_id = \"" + train_id + "\" AND  seat_id = " + seat_id);
        query.fun("update train set occupied_seats = occupied_seats + 1 where train_id = \"" + train_id + "\"");
        query.fun("update train set remaining_seats = remaining_seats - 1 where train_id = \"" + train_id + "\"");
        JOptionPane.showMessageDialog(null, "Ticket booked successfully.");

        Stage primaryStage = new Stage();
        clear.fun();
        variables.openStages.add(primaryStage);
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("userDashboardFrame.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Your Train App");
        primaryStage.show();
    }

    @FXML
    void departureTimeE(ActionEvent event) throws Exception {
        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");
        java.sql.Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select train_id from ticket where start_station = \""
                + startStation.getValue() + "\" AND end_station = \"" + destination.getValue()
                + "\" AND start_time = \"" + departureTime.getValue() + "\"");

        Set<String> S = new HashSet<>();
        while (rs.next()) {
            S.add(rs.getString(1));
        }

        for (String str : S) {
            rs = st.executeQuery("select seat_id from seat where train_id = \"" + str + "\" AND visited = false");
            while (rs.next()) {
                seat.getItems().add("#seat:" + rs.getString(1) + "-train:" + str);
            }
        }
        con.close();
    }

    @FXML
    void destinationE(ActionEvent event) throws Exception {
        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");
        java.sql.Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(
                "select start_station from ticket where end_station = \"" + destination.getValue() + "\"");

        Set<String> S = new HashSet<>();
        while (rs.next()) {
            S.add(rs.getString(1));
        }
        for (String str : S) {
            startStation.getItems().add(str);
        }
        con.close();
    }

    @FXML
    void seatE(ActionEvent event) throws Exception {
        String x = "", y = ""; // seat, train
        boolean ok = true;
        int i = 6;
        while (i < seat.getValue().length()) {
            if (seat.getValue().charAt(i) == '-') {
                i += 7;
                ok = false;
            }
            if (ok) {
                x += seat.getValue().charAt(i);
            } else {
                y += seat.getValue().charAt(i);
            }
            i++;
        }

        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");
        java.sql.Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select ticket_id, cost, expected_end_time from ticket where seat_id = \"" + x
                + "\" AND train_id = \"" + y + "\"");
        while (rs.next()) {
            seat_id = x;
            train_id = y;
            ticket_id = rs.getString(1);
            info.setText("Expected arrival time: " + rs.getString(3) + "\nTicket cost:" + rs.getString(2));
        }
        con.close();
    }

    @FXML
    void startStationE(ActionEvent event) throws Exception {
        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");
        java.sql.Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select start_time from ticket where start_station = \""
                + startStation.getValue() + "\" AND " + "end_station = \"" + destination.getValue() + "\"");

        Set<String> S = new HashSet<>();
        while (rs.next()) {
            S.add(rs.getString(1));
        }
        for (String str : S) {
            departureTime.getItems().add(str);
        }
        con.close();
    }
}
