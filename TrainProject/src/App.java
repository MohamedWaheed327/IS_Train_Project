import java.sql.*;
import java.time.LocalDate;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class App extends JFrame {

    Font mainFont = new Font("Segoe print", Font.BOLD, 15);
    String curUser;

    public static void query(String sql) throws Exception {
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

    public static void clearAll() throws Exception {
        query("delete from user_db");
        query("delete from admin_db");
        query("delete from train");
        query("delete from seat");
        query("delete from ticket");
        query("delete from booked_tickets");
    }

    public static void displayAll() throws Exception {
        query("select * from user_db");
        query("select * from admin_db");
        query("select * from train");
        query("select * from seat");
        query("select * from ticket");
        query("select * from booked_tickets");
    }

    public static boolean not_unique(String column, String table, String value) throws Exception {
        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from " + table + " where " + column + " = " + value);
        boolean x = rs.next();
        con.close();
        return x;
    }

    public static boolean is_valid_admin(String username, String password) throws Exception {
        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from admin_db where user_name_ = \"" + username
                + "\" and user_password = \"" + password + "\"");
        boolean x = rs.next();
        con.close();
        return x;
    }

    public static boolean is_valid_user(String username, String password) throws Exception {
        String url = "jdbc:mysql://localhost:3306/train";
        Connection con = DriverManager.getConnection(url, "root", "root");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from user_db where user_name_ = \"" + username
                + "\" and user_password = \"" + password + "\"");
        boolean x = rs.next();
        con.close();
        return x;
    }

    public static void add_user(String national_id, String user_name_, String user_password, String user_email,
            String user_phone, String gender) throws Exception {
        if (not_unique("user_name_", "user_db", "\"" + user_name_ + "\"")) {
            return;
        }
        national_id = "\"" + national_id + "\",";
        user_name_ = "\"" + user_name_ + "\",";
        user_password = "\"" + user_password + "\",";
        user_email = "\"" + user_email + "\",";
        user_phone = "\"" + user_phone + "\",";
        gender = "\"" + gender + "\"";
        query("insert into user_db values(" + national_id + user_name_ + user_password + user_email + user_phone +
                gender + ")");
    }

    public static void add_admin(String user_name_, String user_password) throws Exception {
        if (not_unique("user_name_", "admin_db", "\"" + user_name_ + "\"")) {
            System.out.println("this user name is already taken");
            return;
        }
        user_name_ = "\"" + user_name_ + "\",";
        user_password = "\"" + user_password + "\"";
        query("insert into admin_db values(" + user_name_ + user_password + ")");
    }

    public static void add_train(String train_id, String seats_number, String start_station, String end_station)
            throws Exception {

        if (not_unique("train_id", "train", train_id)) {
            return;
        }
        int N = Integer.valueOf(seats_number);

        train_id = train_id + ",";
        seats_number = seats_number + ",";
        start_station = "\"" + start_station + "\",";
        end_station = "\"" + end_station + "\",";
        query("insert into train values(" + train_id + seats_number + start_station + end_station + seats_number
                + "0" + ")");

        for (int i = 1; i <= N; i++) {
            query("insert into seat values(" + Integer.toString(i) + "," + train_id + "false" + ")");
        }
    }

    public static void add_ticket(String ticket_id, String train_id, String start_station, String end_station,
            String start_time, String expected_end_time, String cost, String seat_id)
            throws Exception {

        if (not_unique("ticket_id", "ticket", ticket_id)) {
            return;
        }

        ticket_id = ticket_id + ",";
        train_id = train_id + ",";
        start_station = "\"" + start_station + "\",";
        end_station = "\"" + end_station + "\",";
        start_time = "\"" + start_time + "\",";
        expected_end_time = "\"" + expected_end_time + "\",";
        cost = cost + ",";
        query("insert into ticket values(" + ticket_id + train_id + start_station +
                end_station + start_time + expected_end_time + cost + seat_id + ")");
    }

    public static void book_ticket(String ticket_id, String user_name_) throws Exception {
        if (not_unique("ticket_id", "booked_tickets", ticket_id)) {
            return;
        }
        ticket_id = ticket_id + ",";
        user_name_ = "\"" + user_name_ + "\"";
        query("insert into booked_tickets values(" + ticket_id + user_name_ + ")");
    }

    public void remove_train(String id) throws Exception {
        query("delete from train where train_id = " + id);
        query("delete from seat where train_id = " + id);
        query("delete from ticket where train_id = " + id);
    }

    public void FirstFrame() {
        // welcome label
        JLabel welcome = new JLabel();
        welcome.setText("welcome to Your Train App");
        welcome.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(1, 3, 10, 10));
        formPanel.setOpaque(false);
        formPanel.add(new JLabel(""));
        formPanel.add(welcome);
        formPanel.add(new JLabel(""));

        // btn panel
        JButton signinasadmin = new JButton("sign in as admin");
        signinasadmin.setFont(mainFont);
        signinasadmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                App x = new App();
                x.adminLoginFrame();
            }
        });

        JButton signinasuser = new JButton("sign in as user");
        signinasuser.setFont(mainFont);
        signinasuser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                App x = new App();
                x.userLoginFrame();
            }
        });

        JPanel btnpanel = new JPanel();
        btnpanel.setLayout(new GridLayout(2, 1, 30, 30));
        btnpanel.setOpaque(false);
        btnpanel.add(signinasadmin);
        btnpanel.add(signinasuser);

        // main panel
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BorderLayout());
        mainpanel.setBackground(new Color(100, 100, 255));
        mainpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainpanel.add(formPanel, BorderLayout.CENTER);
        mainpanel.add(btnpanel, BorderLayout.SOUTH);

        add(mainpanel);
        setTitle("Your Train App");
        setSize(500, 600);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void adminLoginFrame() {
        // welcome label
        JLabel lbusername = new JLabel();
        lbusername.setText("User name:");
        lbusername.setFont(mainFont);

        JTextField tfusername = new JTextField();
        tfusername.setFont(mainFont);

        JLabel lbpassword = new JLabel();
        lbpassword.setText("Password:");
        lbpassword.setFont(mainFont);

        JTextField tfpassword = new JTextField();
        tfpassword.setFont(mainFont);

        JLabel invalid = new JLabel();
        invalid.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 1, 10, 10));
        formPanel.setOpaque(false);
        formPanel.add(lbusername);
        formPanel.add(tfusername);
        formPanel.add(lbpassword);
        formPanel.add(tfpassword);
        formPanel.add(invalid);

        // btn panel
        JButton signin = new JButton("sign in");
        signin.setFont(mainFont);
        signin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user_name_ = tfusername.getText();
                String user_password = tfpassword.getText();
                if (user_name_.isEmpty() && user_password.isEmpty()) {
                    setVisible(false);
                    App x = new App();
                    x.adminDashboardFrame();
                } else {
                    try {
                        if (is_valid_admin(user_name_, user_password)) {
                            setVisible(false);
                            App x = new App();
                            x.adminDashboardFrame();
                        } else {
                            invalid.setText("invalid user name or password");
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        JButton back = new JButton("back");
        back.setFont(mainFont);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                App x = new App();
                x.FirstFrame();
            }
        });

        JPanel btnpanel = new JPanel();
        btnpanel.setLayout(new GridLayout(2, 1, 30, 30));
        btnpanel.setOpaque(false);
        btnpanel.add(signin);
        btnpanel.add(back);

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
        setVisible(true);
    }

    public void userLoginFrame() {
        // welcome label
        JLabel lbusername = new JLabel("User name:");
        lbusername.setFont(mainFont);

        JTextField tfusername = new JTextField();
        tfusername.setFont(mainFont);

        JLabel lbpassword = new JLabel("Password:");
        lbpassword.setFont(mainFont);

        JTextField tfpassword = new JTextField();
        tfpassword.setFont(mainFont);

        JLabel invalid = new JLabel();
        invalid.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 1, 10, 10));
        formPanel.setOpaque(false);
        formPanel.add(lbusername);
        formPanel.add(tfusername);
        formPanel.add(lbpassword);
        formPanel.add(tfpassword);
        formPanel.add(invalid);

        // btn panel
        JButton signin = new JButton("sign in");
        signin.setFont(mainFont);
        signin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user_name_ = tfusername.getText();
                String user_password = tfpassword.getText();
                try {
                    if (is_valid_user(user_name_, user_password)) {
                        setVisible(false);
                        App x = new App();
                        x.curUser = user_name_;
                        x.userDashboardFrame();
                    } else {
                        invalid.setText("invalid user name or password");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton createaccount = new JButton("Create Account");
        createaccount.setFont(mainFont);
        createaccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                App x = new App();
                x.createaccountFrame();
            }
        });

        JButton back = new JButton("back");
        back.setFont(mainFont);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                App x = new App();
                x.FirstFrame();
            }
        });

        JPanel btnpanel = new JPanel();
        btnpanel.setLayout(new GridLayout(3, 1, 30, 30));
        btnpanel.setOpaque(false);
        btnpanel.add(signin);
        btnpanel.add(createaccount);
        btnpanel.add(back);

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
        setVisible(true);
    }

    public void createaccountFrame() {
        // welcome label
        JLabel lbusername = new JLabel("User name:");
        lbusername.setFont(mainFont);

        JTextField tfusername = new JTextField();
        tfusername.setFont(mainFont);

        JLabel lbnationalid = new JLabel("National ID:");
        lbnationalid.setFont(mainFont);

        JTextField tfnationalid = new JTextField();
        tfnationalid.setFont(mainFont);

        JLabel lbpassword = new JLabel("Password:");
        lbpassword.setFont(mainFont);

        JTextField tfpassword = new JTextField();
        tfpassword.setFont(mainFont);

        JLabel lbuseremail = new JLabel("User Email:");
        lbuseremail.setFont(mainFont);

        JTextField tfuseremail = new JTextField();
        tfuseremail.setFont(mainFont);

        JLabel lbuserphone = new JLabel("User phone:");
        lbuserphone.setFont(mainFont);

        JTextField tfuserphone = new JTextField();
        tfuserphone.setFont(mainFont);

        JLabel lbgender = new JLabel("gender:");
        lbgender.setFont(mainFont);

        JRadioButton male = new JRadioButton("male");
        JRadioButton female = new JRadioButton("female");
        male.setOpaque(false);
        female.setOpaque(false);
        male.setFont(mainFont);
        female.setFont(mainFont);

        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);

        JLabel invalid = new JLabel();
        invalid.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(10, 2, 10, 10));
        formPanel.setOpaque(false);
        formPanel.add(lbusername);
        formPanel.add(tfusername);
        formPanel.add(lbnationalid);
        formPanel.add(tfnationalid);
        formPanel.add(lbuseremail);
        formPanel.add(tfuseremail);
        formPanel.add(lbuserphone);
        formPanel.add(tfuserphone);
        formPanel.add(lbpassword);
        formPanel.add(tfpassword);
        formPanel.add(lbgender);
        formPanel.add(new JLabel(""));
        formPanel.add(male);
        formPanel.add(female);
        formPanel.add(invalid);

        // btn panel
        JButton signup = new JButton("sign up");
        signup.setFont(mainFont);
        signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user_name_ = tfusername.getText();
                String user_password = tfpassword.getText();
                String user_phone = tfuserphone.getText();
                String user_email = tfuseremail.getText();
                String national_id = tfnationalid.getText();
                String gender = "female";
                if (male.isSelected()) {
                    gender = "male";
                }

                try {
                    if (!not_unique("user_name_", "user_db", "\"" + user_name_ + "\"")) {
                        add_user(national_id, user_name_, user_password, user_email, user_phone, gender);
                        invalid.setText("Account created successfully");
                    } else {
                        invalid.setText("This user name already exists");
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
                x.userLoginFrame();
            }
        });

        JPanel btnpanel = new JPanel();
        btnpanel.setLayout(new GridLayout(2, 1, 30, 30));
        btnpanel.setOpaque(false);
        btnpanel.add(signup);
        btnpanel.add(back);

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
        setVisible(true);
    }

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
                    if (not_unique("train_id", "train", train_id)) {
                        JOptionPane.showMessageDialog(null, "Train already exists!!");
                    } else {
                        add_train(train_id, seat_number, start_station, end_station);
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
                            remove_train(x);
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
        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        App x = new App();
        x.FirstFrame();
    }
}