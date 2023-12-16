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

    public static void add_user(String national_id, String user_name_, String user_password, String user_email,
            String user_phone, String gender) throws Exception {
        national_id = national_id + ",";
        user_name_ = "\"" + user_name_ + "\",";
        user_password = "\"" + user_password + "\",";
        user_email = "\"" + user_email + "\",";
        user_phone = "\"" + user_phone + "\",";
        gender = "\"" + gender + "\"";
        query("insert into user_db values(" + national_id + user_name_ + user_password + user_email + user_phone +
                gender + ")");
    }

    public static void add_admin() {

    }

    public static void main(String[] args) throws Exception {
        query("select * from admin_db");
    }

}