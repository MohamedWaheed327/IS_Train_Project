package functions;

import java.sql.*;

public class is_valid_user {
    public static boolean fun(String username, String password) throws Exception {
        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from user_db where user_name_ = \"" + username
                + "\" and user_password = \"" + password + "\"");
        boolean x = rs.next();
        con.close();
        return x;
    }
}
