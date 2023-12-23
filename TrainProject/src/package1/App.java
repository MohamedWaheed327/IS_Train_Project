package package1;

import java.sql.*;
import java.time.LocalDate;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class App extends JFrame {

    Font mainFont = new Font("Segoe print", Font.BOLD, 15);
    String curUser;

    public void userDashboardFrame() {
        // welcome label
        JButton account = new JButton("account");
        account.setFont(mainFont);
        account.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        JLabel welcome = new JLabel("welcome to your Train App, " + curUser);
        welcome.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 1, 10, 10));
        formPanel.setOpaque(false);
        formPanel.add(account);
        formPanel.add(welcome);

        // btn panel
        JButton bookticket = new JButton("book a ticket");
        bookticket.setFont(mainFont);
        bookticket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton bookedtickets = new JButton("booked tickets");
        bookedtickets.setFont(mainFont);
        bookedtickets.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        JPanel btnpanel = new JPanel();
        btnpanel.setLayout(new GridLayout(2, 1, 30, 30));
        btnpanel.setOpaque(false);
        btnpanel.add(bookticket);
        btnpanel.add(bookedtickets);

        // main panel
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BorderLayout());
        mainpanel.setBackground(new Color(100, 100, 255));
        mainpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainpanel.add(formPanel, BorderLayout.NORTH);
        mainpanel.add(btnpanel, BorderLayout.SOUTH);

        add(mainpanel);
        setTitle("Your Train App");
        setSize(500, 600);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void adminDashboardFrame() {
        // btn panel
        JButton addtrain = new JButton("Add a train");
        addtrain.setFont(mainFont);
        addtrain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                App x = new App();
                x.addTrainFrame();
            }
        });

        JButton addtickets = new JButton("Add tickets");
        addtickets.setFont(mainFont);
        addtickets.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                App x = new App();
                x.addTicketFrame();
            }
        });

        JButton removetrain = new JButton("remove a train");
        removetrain.setFont(mainFont);
        removetrain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                App x = new App();
                x.removeTrainFrame();
            }
        });

        JButton removetickets = new JButton("remove tickets");
        removetickets.setFont(mainFont);
        removetickets.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        JPanel btnpanel = new JPanel();
        btnpanel.setLayout(new GridLayout(4, 1, 30, 30));
        btnpanel.setOpaque(false);
        btnpanel.add(addtrain);
        btnpanel.add(removetrain);
        btnpanel.add(addtickets);
        btnpanel.add(removetickets);

        // main panel
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BorderLayout());
        mainpanel.setBackground(new Color(100, 100, 255));
        mainpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainpanel.add(btnpanel, BorderLayout.SOUTH);

        add(mainpanel);
        setTitle("Your Train App");
        setSize(500, 600);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addTrainFrame() {
        // popup menu
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("Option 1");
        JMenuItem menuItem2 = new JMenuItem("Option 2");

        menuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Option 1 selected");
            }
        });

        menuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Option 2 selected");
            }
        });

        popupMenu.add(menuItem1);
        popupMenu.add(menuItem2);
        // popupMenu.show(addtrain, 0, addtrain.getHeight());
        // btnpanel.setComponentPopupMenu(popupMenu);
        ////////////////////////////////////////////////////////////////////////////////////////

        // Sample data for the table
        Object[][] data = {
                { "John", 25, "New York" },
                { "Alice", 30, "London" }
        };

        // Column names
        String[] columnNames = { "Name", "Age", "City" };

        // Creating the table
        JTable table = new JTable(data, columnNames);

        // Adding the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        ////////////////////////////////////////////////////////////////////////////////////////
        // welcome label
        JLabel lbtrainid = new JLabel("train ID:");
        lbtrainid.setFont(mainFont);

        JTextField tftrainid = new JTextField();
        tftrainid.setFont(mainFont);

        JLabel lbseatsnumber = new JLabel("seats number:");
        lbseatsnumber.setFont(mainFont);

        JTextField tfseatsnumber = new JTextField();
        tfseatsnumber.setFont(mainFont);

        JLabel lbstartstation = new JLabel("start station:");
        lbstartstation.setFont(mainFont);

        JTextField tfstartstation = new JTextField();
        tfstartstation.setFont(mainFont);

        JLabel lbendstation = new JLabel("end station:");
        lbendstation.setFont(mainFont);

        JTextField tfendstation = new JTextField();
        tfendstation.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 10));
        formPanel.setOpaque(false);
        formPanel.add(lbtrainid);
        formPanel.add(tftrainid);
        formPanel.add(lbseatsnumber);
        formPanel.add(tfseatsnumber);
        formPanel.add(lbstartstation);
        formPanel.add(tfstartstation);
        formPanel.add(lbendstation);
        formPanel.add(tfendstation);
        // center
        JPanel test = new JPanel();
        test.setLayout(new GridLayout(4, 2, 10, 10));
        test.setOpaque(false);
        test.add(scrollPane);

        // btn panel
        JButton addtrain = new JButton("Add");
        addtrain.setFont(mainFont);
        addtrain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String train_id = tftrainid.getText();
                String seat_number = tfseatsnumber.getText();
                String start_station = tfstartstation.getText();
                String end_station = tfendstation.getText();
                try {
                    if (not_unique.not_unique("train_id", "train", train_id)) {
                        JOptionPane.showMessageDialog(null, "Train already exists!!");
                    } else {
                        add_train.add_train(train_id, seat_number, start_station, end_station);
                        JOptionPane.showMessageDialog(null, "Train added successfully!!");
                    }
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
                App x = new App();
                x.adminDashboardFrame();
            }
        });

        JPanel btnpanel = new JPanel();
        btnpanel.setLayout(new GridLayout(2, 1, 30, 30));
        btnpanel.setOpaque(false);
        btnpanel.add(addtrain);
        btnpanel.add(back);

        // main panel
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BorderLayout());
        mainpanel.setBackground(new Color(100, 100, 255));
        mainpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainpanel.add(formPanel, BorderLayout.NORTH);
        // mainpanel.add(test, BorderLayout.CENTER);
        mainpanel.add(btnpanel, BorderLayout.SOUTH);

        add(mainpanel);
        setTitle("Your Train App");
        setSize(500, 600);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

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
                            remove_train.remove_train(x);
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
                App x = new App();
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
                App x = new App();
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

    public static void main(String[] args) throws Exception {
        FirstFrame x = new FirstFrame();
        x.FirstFrame();
    }
}