package functions;

import java.sql.*;

public class query {
    public static void fun(String sql) throws Exception {
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
}
