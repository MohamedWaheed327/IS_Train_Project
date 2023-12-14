import java.sql.*;

public class App {
    public static void solve(String sql) throws Exception {
        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        System.out.println(rs.getString(1));
        con.close();
    }

    public static void main(String[] args) throws Exception {
        solve("select tname from test where id = 1");
    }
}