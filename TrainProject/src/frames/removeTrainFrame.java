package frames;

import functions.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class removeTrainFrame extends JFrame {
    Font mainFont = new Font("Segoe print", Font.BOLD, 15);

    public void removeTrainFrame() {
        // popup menu
        JPopupMenu popupMenu = new JPopupMenu();
        try {
            String url = "jdbc:mysql://localhost:3306/train";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from train");
            int N = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                String s = "ID", id = "";
                for (int i = 1; i <= N; i++) {
                    if (i > 1)
                        s += " - ";
                    if (i == 1)
                        id = rs.getString(i);
                    s += rs.getString(i);
                }
                String x = id;
                JMenuItem menuItem = new JMenuItem(s);
                menuItem.setFont(mainFont);
                menuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            remove_train.fun(x);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(null, "train removed successfully.");
                        popupMenu.remove(menuItem);
                    }
                });
                popupMenu.add(menuItem);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // show panel
        JPanel showPanel = new JPanel();
        showPanel.setLayout(new GridLayout(2, 1, 30, 30));
        showPanel.setOpaque(false);
        JButton show = new JButton("remove");
        show.setFont(mainFont);
        show.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                popupMenu.show(show, 0, show.getHeight());
            }
        });
        showPanel.add(show);
        showPanel.setComponentPopupMenu(popupMenu);

        // btn panel
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
        btnpanel.setLayout(new GridLayout(2, 1, 30, 30));
        btnpanel.setOpaque(false);
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
