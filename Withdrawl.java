
package bankmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Withdrawl extends JFrame implements ActionListener {

    JTextField t1, t2;
    JButton b1, b2, b3;
    JLabel l1, l2, l3, l4;

    // Withdrawal limit
    private static final double WITHDRAWAL_LIMIT = 10000.00;
    private boolean pinValidated = false;  // Track if the PIN is validated

    Withdrawl() {
        setFont(new Font("System", Font.BOLD, 22));
        Font f = getFont();
        FontMetrics fm = getFontMetrics(f);
        int x = fm.stringWidth("WITHDRAWAL");
        int y = fm.stringWidth(" ");
        int z = getWidth() - (3 * x);
        int w = z / y;
        String pad = "";
        pad = String.format("%" + w + "s", pad);
        setTitle(pad + "WITHDRAWAL");

        l1 = new JLabel("MAXIMUM DAILY WITHDRAWAL");
        l1.setFont(new Font("System", Font.BOLD, 40));

        l2 = new JLabel("IS RS.10,000");
        l2.setFont(new Font("System", Font.BOLD, 40));

        l3 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        l3.setFont(new Font("System", Font.BOLD, 35));

        l4 = new JLabel("Enter Pin");
        l4.setFont(new Font("System", Font.BOLD, 14));

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

        l4.setBounds(620, 10, 80, 30);
        add(l4);

        t2.setBounds(700, 10, 40, 30);
        add(t2);

        l1.setBounds(90, 100, 800, 60);
        add(l1);

        l2.setBounds(270, 160, 800, 60);
        add(l2);

        l3.setBounds(120, 260, 800, 60);
        add(l3);

        t1.setBounds(210, 340, 360, 50);
        add(t1);

        b1.setBounds(220, 400, 160, 50);
        add(b1);

        b2.setBounds(400, 400, 160, 50);
        add(b2);

        b3.setBounds(300, 550, 200, 50);
        add(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        // Input validation to allow only digits in the withdrawal field
        t1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                // Allow only digits and backspace
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    ke.consume(); // Ignore non-digit characters
                    JOptionPane.showMessageDialog(null, "Please enter digits only.");
                }
            }
        });

        // Initially, disable the withdrawal field
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
                        JOptionPane.showMessageDialog(null, "PIN validated. Please enter the withdrawal amount.");

                        // Enable the withdrawal amount field
                        t1.setEnabled(true);
                        t1.setText(""); // Clear the withdrawal amount field

                        // Change the button text to "WITHDRAW"
                        b1.setText("WITHDRAW");
                        b1.removeActionListener(this);
                        b1.addActionListener(this);  // Rebind action listener for withdrawal
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid PIN! Please try again.");
                        t2.setText("");  // Clear the PIN field
                    }
                } else {
                    // Step 2: After PIN validation, proceed with withdrawal

                    // Check if withdrawal amount is entered
                    if (a.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Withdraw");
                        return;
                    }

                    // Convert withdrawal amount to double
                    double withdrawalAmount = Double.parseDouble(a);

                    // Check if withdrawal amount exceeds the limit
                    if (withdrawalAmount > WITHDRAWAL_LIMIT) {
                        JOptionPane.showMessageDialog(null, "Withdrawal limit exceeded! The maximum withdrawal amount is Rs. " + WITHDRAWAL_LIMIT);
                        return;
                    }

                    conn c1 = new conn();

                    // Query the database to get user details using the entered PIN
                    ResultSet rs = c1.s.executeQuery("select * from bank where pin = '" + b + "' order by sno desc limit 1");

                    double balance = 0;
                    if (rs.next()) {
                        String pin = rs.getString("pin");
                        String accno = rs.getString("accno");

                        balance = rs.getDouble("balance");
                        double sno = rs.getDouble("sno");
                        sno++;

                        // Check if the balance is sufficient for withdrawal
                        if (balance >= withdrawalAmount) {
                            balance -= withdrawalAmount; // Deduct the withdrawal amount
                            String q1 = "insert into bank(pin, deposit, withdraw, balance, accno, sno) values('" + pin + "', 0, '" + withdrawalAmount + "', '" + balance + "', '" + accno + "', '" + sno + "')";
                            c1.s.executeUpdate(q1);
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient Balance to Withdraw Rs. " + withdrawalAmount);
                            return;
                        }
                    }

                    // Notify the user about the successful withdrawal
                    JOptionPane.showMessageDialog(null, "Rs. " + a + " Debited Successfully");

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
        new Withdrawl().setVisible(true);
    }
}
