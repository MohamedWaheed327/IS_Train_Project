import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");
        Statement st = con.createStatement();

        String sql = "select tname from test where id = 1";
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        System.out.println(rs.getString(1));

        con.close();
    }
}