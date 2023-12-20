import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class App extends JFrame {
    public static void query(String sql) throws Exception {
        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");
        Statement st = con.createStatement();
        if (st.execute(sql)) {
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int N = rsmd.getColumnCount();

            for (int i = 1; i <= N; i++) {
                System.out.print(rsmd.getColumnName(i) + " ");
            }
            System.out.println("");

            while (rs.next()) {
                for (int i = 1; i <= N; i++) {
                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println("");
            }
        }
        con.close();
    }

    public static boolean not_unique(String column, String table, String value) throws Exception {
        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from " + table + " where " + column + " = " + value);
        boolean x = rs.next();
        con.close();
        return x;
    }

    public static void add_user(String national_id, String user_name_, String user_password, String user_email,
            String user_phone, String gender) throws Exception {
        if (not_unique("user_name_", "user_db", "\"" + user_name_ + "\"")) {
            System.out.println("this user name is already taken");
            return;
        }
        national_id = "\"" + national_id + "\",";
        user_name_ = "\"" + user_name_ + "\",";
        user_password = "\"" + user_password + "\",";
        user_email = "\"" + user_email + "\",";
        user_phone = "\"" + user_phone + "\",";
        gender = "\"" + gender + "\"";
        query("insert into user_db values(" + national_id + user_name_ + user_password + user_email + user_phone +
                gender + ")");
    }

    public static void add_admin(String user_name_, String user_password) throws Exception {
        if (not_unique("user_name_", "admin_db", "\"" + user_name_ + "\"")) {
            System.out.println("this user name is already taken");
            return;
        }
        user_name_ = "\"" + user_name_ + "\",";
        user_password = "\"" + user_password + "\"";
        query("insert into admin_db values(" + user_name_ + user_password + ")");
    }

    public static void add_train(String train_id, String seats_number, String start_station, String end_station)
            throws Exception {

        if (not_unique("train_id", "train", train_id)) {
            System.out.println("this train is already added");
            return;
        }
        int N = Integer.valueOf(seats_number);

        train_id = train_id + ",";
        seats_number = seats_number + ",";
        start_station = "\"" + start_station + "\",";
        end_station = "\"" + end_station + "\",";
        query("insert into train values(" + train_id + seats_number + start_station + end_station + seats_number
                + "0" + ")");

        for (int i = 1; i <= N; i++) {
            query("insert into seat values(" + Integer.toString(i) + "," + train_id + "false" + ")");
        }
    }

    public static void add_ticket(String ticket_id, String train_id, String start_station, String end_station,
            String start_time, String expected_end_time, String cost, String seat_id)
            throws Exception {

        if (not_unique("ticket_id", "ticket", ticket_id)) {
            System.out.println("this ticket is already added");
            return;
        }

        ticket_id = ticket_id + ",";
        train_id = train_id + ",";
        start_station = "\"" + start_station + "\",";
        end_station = "\"" + end_station + "\",";
        start_time = "\"" + start_time + "\",";
        expected_end_time = "\"" + expected_end_time + "\",";
        cost = cost + ",";
        query("insert into ticket values(" + ticket_id + train_id + start_station +
                end_station + start_time
                + expected_end_time + cost + seat_id + ")");
    }

    public static void book_ticket(String ticket_id, String user_name_) throws Exception {
        if (not_unique("ticket_id", "booked_tickets", ticket_id)) {
            System.out.println("this ticket is already booked");
            return;
        }
        ticket_id = ticket_id + ",";
        user_name_ = "\"" + user_name_ + "\"";
        query("insert into booked_tickets values(" + ticket_id + user_name_ + ")");
    }

    Font mainFont = new Font("Segoe print", Font.BOLD, 15);

    public void FirstFrame() {
        // btn panel
        JButton signin = new JButton("sign in");
        signin.setFont(mainFont);
        signin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        JButton signup = new JButton("sign up");
        signup.setFont(mainFont);
        signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        JPanel btnpanel = new JPanel();
        btnpanel.setLayout(new GridLayout(2, 1, 30, 30));
        btnpanel.setOpaque(false);
        btnpanel.add(signin);
        btnpanel.add(signup);
        // main panel
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BorderLayout());
        mainpanel.setBackground(new Color(10, 10, 100));
        mainpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainpanel.add(btnpanel, BorderLayout.SOUTH);

        add(mainpanel);
        setTitle("first frame");
        setSize(500, 600);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        App x = new App();
        x.FirstFrame();
    }
}