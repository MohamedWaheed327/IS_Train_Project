package package1;

import java.sql.*;

public class is_valid_admin {
    public static boolean is_valid_admin(String username, String password) throws Exception {
        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from admin_db where user_name_ = \"" + username
                + "\" and user_password = \"" + password + "\"");
        boolean x = rs.next();
        con.close();
        return x;
    }
}
