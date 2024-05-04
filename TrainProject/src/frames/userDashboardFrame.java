package frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class userDashboardFrame extends JFrame {
    Font mainFont = new Font("Segoe print", Font.BOLD, 15);
    public String curUser;

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

}
