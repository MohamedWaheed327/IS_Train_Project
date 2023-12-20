import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class App extends JFrame {

    Font mainFont = new Font("Segoe print", Font.BOLD, 15);

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
            System.out.println("this train is already added");
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
            System.out.println("this ticket is already added");
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
            System.out.println("this ticket is already booked");
            return;
        }
        ticket_id = ticket_id + ",";
        user_name_ = "\"" + user_name_ + "\"";
        query("insert into booked_tickets values(" + ticket_id + user_name_ + ")");
    }

    public void FirstFrame() {
        // welcome label
        JLabel welcome = new JLabel();
        welcome.setText("                   welcome to Your Train App");
        welcome.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(1, 1, 10, 10));
        formPanel.setOpaque(false);
        formPanel.add(welcome);

        // btn panel
        JButton signinasadmin = new JButton("sign in as admin");
        signinasadmin.setFont(mainFont);
        signinasadmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                App x = new App();
                x.adminFrame();
            }
        });

        JButton signinasuser = new JButton("sign in as user");
        signinasuser.setFont(mainFont);
        signinasuser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                App x = new App();
                x.userFrame();
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

    public void adminFrame() {
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
                try {
                    if (is_valid_admin(user_name_, user_password)) {
                        invalid.setText("success!!");
                    } else {
                        invalid.setText("invalid user name or password");
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

    public void userFrame() {
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
                        invalid.setText("success!!");
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
                x.userFrame();
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

    public static void main(String[] args) throws Exception {
        App x = new App();
        x.FirstFrame();
        // '2023-11-16 18:06:46'
    }
}