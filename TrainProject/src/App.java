import java.sql.*;

public class App {
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
        if (not_unique("user_name_", "user_db", user_name_)) {
            System.out.println("this user name is already taken");
            return;
        }
        national_id = national_id + ",";
        user_name_ = "\"" + user_name_ + "\",";
        user_password = "\"" + user_password + "\",";
        user_email = "\"" + user_email + "\",";
        user_phone = "\"" + user_phone + "\",";
        gender = "\"" + gender + "\"";
        query("insert into user_db values(" + national_id + user_name_ + user_password + user_email + user_phone +
                gender + ")");
    }

    public static void add_admin(String user_name_, String user_password) throws Exception {
        if (not_unique("user_name_", "admin_db", user_name_)) {
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

    public static void main(String[] args) throws Exception {
        query("select * from user_db");
        query("select * from admin_db");
        query("select * from train");
        query("select * from seat");
    }
}