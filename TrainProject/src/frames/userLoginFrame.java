package frames;

import functions.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class userLoginFrame extends JFrame {
    Font mainFont = new Font("Segoe print", Font.BOLD, 15);

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
                    if (is_valid_user.fun(user_name_, user_password)) {
                        setVisible(false);
                        userDashboardFrame x = new userDashboardFrame();
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
                createaccountFrame x = new createaccountFrame();
                x.createaccountFrame();
            }
        });

        JButton back = new JButton("back");
        back.setFont(mainFont);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {

    }
}
