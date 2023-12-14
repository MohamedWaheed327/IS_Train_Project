import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");

        String Query = "select tname from test where id = 1";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(Query);
        rs.next();
        System.out.println(rs.getString(1));
        
        con.close();
    }
}