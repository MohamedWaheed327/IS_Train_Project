import java.sql.*;

public class App {
    public static void query(String sql) throws Exception {
        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        System.out.println(rs.getString(1));
        con.close();
    }

    public static void main(String[] args) throws Exception {
        query("select tname from test where id = 1");
        query("select tname from test where id = 2");
    }
}