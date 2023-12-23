package package1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FirstFrame extends JFrame {
    Font mainFont = new Font("Segoe print", Font.BOLD, 15);

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
                adminLoginFrame x = new adminLoginFrame();
                x.adminLoginFrame();
            }
        });

        JButton signinasuser = new JButton("sign in as user");
        signinasuser.setFont(mainFont);
        signinasuser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                userLoginFrame x = new userLoginFrame();
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
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
