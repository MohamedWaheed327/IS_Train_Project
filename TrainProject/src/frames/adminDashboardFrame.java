package frames;
import functions.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class adminDashboardFrame extends JFrame {
    Font mainFont = new Font("Segoe print", Font.BOLD, 15);

    public void adminDashboardFrame() {
        // btn panel
        JButton addtrain = new JButton("Add a train");
        addtrain.setFont(mainFont);
        addtrain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                addTrainFrame x = new addTrainFrame();
                x.addTrainFrame();
            }
        });

        JButton addtickets = new JButton("Add tickets");
        addtickets.setFont(mainFont);
        addtickets.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                addTicketFrame x = new addTicketFrame();
                x.addTicketFrame();
            }
        });

        JButton removetrain = new JButton("remove a train");
        removetrain.setFont(mainFont);
        removetrain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                removeTrainFrame x = new removeTrainFrame();
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

}
