package frames;
import functions.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class createaccountFrame extends JFrame {
    Font mainFont = new Font("Segoe print", Font.BOLD, 15);

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
                    if (!not_unique.not_unique("user_name_", "user_db", "\"" + user_name_ + "\"")) {
                        add_user.add_user(national_id, user_name_, user_password, user_email, user_phone, gender);
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
                userLoginFrame x = new userLoginFrame();
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
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
