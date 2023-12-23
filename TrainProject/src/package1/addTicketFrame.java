package package1;

import java.sql.*;
import java.time.LocalDate;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class addTicketFrame extends JFrame {

    Font mainFont = new Font("Segoe print", Font.BOLD, 15);
    String id = "";
    
    public void addTicketFrame() {
        // popup menu
        JButton show = new JButton("choose a train");
        JPopupMenu popupMenu = new JPopupMenu();
        try {
            String url = "jdbc:mysql://localhost:3306/train";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from train");
            int N = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                String s = "ID";
                for (int i = 1; i <= N; i++) {
                    if (i > 1)
                        s += " - ";
                    s += rs.getString(i);
                }
                String x = rs.getString(1);
                JMenuItem menuItem = new JMenuItem(s);
                menuItem.setFont(mainFont);
                menuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            id = x;
                            show.setText("train" + id);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                });
                popupMenu.add(menuItem);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // show panel
        JLabel lbfrom = new JLabel("from:");
        lbfrom.setFont(mainFont);

        JTextField tffrom = new JTextField();
        tffrom.setFont(mainFont);

        JLabel lbto = new JLabel("to:");
        lbto.setFont(mainFont);

        JTextField tfto = new JTextField();
        tfto.setFont(mainFont);

        JPanel showPanel = new JPanel();
        showPanel.setLayout(new GridLayout(3, 2, 30, 30));
        showPanel.setOpaque(false);
        show.setFont(mainFont);
        show.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                popupMenu.show(show, 0, show.getHeight());
            }
        });
        showPanel.add(show);
        showPanel.add(new JLabel(""));
        showPanel.add(lbfrom);
        showPanel.add(tffrom);
        showPanel.add(lbto);
        showPanel.add(tfto);
        showPanel.setComponentPopupMenu(popupMenu);

        // btn panel
        JButton addticket = new JButton("add tickets");
        addticket.setFont(mainFont);
        addticket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String from = tffrom.getText();
                String to = tfto.getText();
                String currentdate = LocalDate.now().toString();
                try {
                    String sql = "";
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
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton back = new JButton("back");
        back.setFont(mainFont);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                adminDashboardFrame x = new adminDashboardFrame();
                x.adminDashboardFrame();
            }
        });

        JPanel btnpanel = new JPanel();
        btnpanel.setLayout(new GridLayout(1, 2, 30, 30));
        btnpanel.setOpaque(false);
        btnpanel.add(addticket);
        btnpanel.add(back);

        // main panel
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BorderLayout());
        mainpanel.setBackground(new Color(100, 100, 255));
        mainpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainpanel.add(showPanel, BorderLayout.NORTH);
        mainpanel.add(btnpanel, BorderLayout.SOUTH);

        add(mainpanel);
        setTitle("Your Train App");
        setSize(500, 600);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
