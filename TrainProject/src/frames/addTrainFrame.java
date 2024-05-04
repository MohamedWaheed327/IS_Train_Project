package frames;

import functions.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class addTrainFrame extends JFrame {
    Font mainFont = new Font("Segoe print", Font.BOLD, 15);

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
                    if (not_unique.fun("train_id", "train", train_id)) {
                        JOptionPane.showMessageDialog(null, "Train already exists!!");
                    } else {
                        add_train.fun(train_id, seat_number, start_station, end_station);
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
                adminDashboardFrame x = new adminDashboardFrame();
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

}
