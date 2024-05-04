package functions;

import java.sql.*;

public class not_unique {
    public static boolean fun(String column, String table, String value) throws Exception {
        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from " + table + " where " + column + " = " + value);
        boolean x = rs.next();
        con.close();
        return x;
    }
}
