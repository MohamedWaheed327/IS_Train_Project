package functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class get_train_list {

    public static List<String> fun() throws Exception {
        List<String> x = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");
        java.sql.Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from train");
        int N = rs.getMetaData().getColumnCount();

        while (rs.next()) {
            String s = "ID";
            for (int i = 1; i <= N; i++) {
                if (i > 1)
                    s += " - ";
                s += rs.getString(i);
            }
            x.add(s);
        }
        con.close();
        return x;
    }
}
