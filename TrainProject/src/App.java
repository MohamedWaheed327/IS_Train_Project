import java.sql.*;

public class App {
    public static void query(String sql) throws Exception {
        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");
        Statement st = con.createStatement();
        if (st.execute(sql)) {
            ResultSet rs = st.executeQuery(sql);
        }
        con.close();
    }

    public static void main(String[] args) throws Exception {
        
    }
}