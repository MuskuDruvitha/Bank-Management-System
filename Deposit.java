
package bankmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Deposit extends JFrame implements ActionListener {

    JTextField t1, t2;
    JButton b1, b2, b3;
    JLabel l1, l2, l3;

    // Maximum deposit limit
    private static final double DEPOSIT_LIMIT = 500000.00;
    private boolean pinValidated = false;  // Track if the PIN is validated

    Deposit() {
        setFont(new Font("System", Font.BOLD, 22));
        Font f = getFont();
        FontMetrics fm = getFontMetrics(f);
        int x = fm.stringWidth("DEPOSIT");
        int y = fm.stringWidth(" ");
        int z = getWidth() - (5 * x);
        int w = z / y;
        String pad = "";

        pad = String.format("%" + w + "s", pad);
        setTitle(pad + "DEPOSIT");

        l1 = new JLabel("ENTER AMOUNT YOU WANT");
        l1.setFont(new Font("System", Font.BOLD, 35));

        l2 = new JLabel("TO DEPOSIT");
        l2.setFont(new Font("System", Font.BOLD, 35));

        l3 = new JLabel("Enter Pin");
        l3.setFont(new Font("System", Font.BOLD, 14));

        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 22));

        t2 = new JTextField();
        t2.setFont(new Font("Raleway", Font.BOLD, 14));

        b1 = new JButton("ENTER PIN");
        b1.setFont(new Font("System", Font.BOLD, 18));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2 = new JButton("BACK");
        b2.setFont(new Font("System", Font.BOLD, 18));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        b3 = new JButton("EXIT");
        b3.setFont(new Font("System", Font.BOLD, 18));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);

        setLayout(null);

        l3.setBounds(620, 10, 80, 30);
        add(l3);

        t2.setBounds(700, 10, 40, 30);
        add(t2);

        l1.setBounds(150, 150, 800, 60);
        add(l1);

        l2.setBounds(290, 210, 800, 60);
        add(l2);

        t1.setBounds(250, 300, 300, 50);
        add(t1);

        b1.setBounds(180, 380, 170, 50);
        add(b1);

        b2.setBounds(445, 380, 125, 50);
        add(b2);

        b3.setBounds(300, 500, 200, 50);
        add(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        // Input validation to allow only digits in the deposit field
        t1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                // Allow only digits, backspace, and delete
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    ke.consume(); // Ignore non-digit characters
                    JOptionPane.showMessageDialog(null, "Please enter digits only.");
                }
            }
        });

        // Initially, disable the deposit field
        t1.setEnabled(false);

        getContentPane().setBackground(Color.gray);

        setSize(800, 800);
        setLocationRelativeTo(null);  // Center the frame
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String a = t1.getText();
            String b = t2.getText();

            if (ae.getSource() == b1) {
                if (!pinValidated) {
                    // Step 1: Validate the PIN entered
                    if (b.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please enter your PIN.");
                        return;
                    }

                    conn c1 = new conn();

                    // Query the database to get user details using the entered PIN
                    ResultSet rs = c1.s.executeQuery("select * from bank where pin = '" + b + "'");

                    if (rs.next()) {
                        pinValidated = true; // Pin is validated
                        JOptionPane.showMessageDialog(null, "PIN validated. Please enter the deposit amount.");

                        // Enable the deposit amount field
                        t1.setEnabled(true);
                        t1.setText(""); // Clear the deposit amount field

                        // Change the button text to "DEPOSIT"
                        b1.setText("DEPOSIT");
                        b1.removeActionListener(this);
                        b1.addActionListener(this);  // Rebind action listener for deposit
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid PIN! Please try again.");
                        t2.setText("");  // Clear the PIN field
                    }
                } else {
                    // Step 2: After PIN validation, proceed with deposit

                    // Check if deposit amount is entered
                    if (a.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Deposit");
                        return;
                    }

                    // Convert deposit amount to double
                    double depositAmount = Double.parseDouble(a);

                    // Check if deposit amount exceeds the limit
                    if (depositAmount > DEPOSIT_LIMIT) {
                        JOptionPane.showMessageDialog(null, "Deposit limit exceeded! The maximum deposit amount is Rs. " + DEPOSIT_LIMIT);
                        return;
                    }

                    conn c1 = new conn();

                    // Query the database to get user details using the entered PIN
                    ResultSet rs = c1.s.executeQuery("select * from bank where pin = '" + b + "' order by sno desc limit 1");

                    if (rs.next()) {
                        String pin = rs.getString("pin");
                        String accno = rs.getString("accno");
                        double sno = rs.getDouble("sno");
                        double bal = rs.getDouble("balance");
                        System.out.println(bal);

                        // Update balance
                        bal = bal + depositAmount;
                        sno++;

                        // Insert new transaction record
                        String q1 = "insert into bank(pin,deposit,withdraw,balance,accno,sno) values('" + pin + "','" + depositAmount + "',0,'" + bal + "','" + accno + "','" + sno + "')";
                        c1.s.executeUpdate(q1);
                    }

                    // Notify the user about the successful deposit
                    JOptionPane.showMessageDialog(null, "Rs. " + a + " Deposited Successfully");
                    new Transaction().setVisible(true);
                    setVisible(false);
                }

            } else if (ae.getSource() == b2) {
                // Back button action
                new Transaction().setVisible(true);
                setVisible(false);

            } else if (ae.getSource() == b3) {
                // Exit button action
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
    }

    public static void main(String[] args) {
        new Deposit().setVisible(true);
    }
}