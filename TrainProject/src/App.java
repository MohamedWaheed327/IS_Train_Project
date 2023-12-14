import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");
        String Query = "select * from test";
        PreparedStatement ps = con.prepareStatement(Query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.next());
        }
        
        con.close();
    }
}