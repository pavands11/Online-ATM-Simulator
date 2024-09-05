package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Pin extends JFrame implements ActionListener {
    JButton b1, b2, b3;
    JPasswordField p1, p2;
    JTextField otpField, gmailField;
    String pin;
    int generatedOTP;
    boolean otpSent = false;

    Pin(String pin) {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1550, 830);
        add(l3);

        JLabel label1 = new JLabel("CHANGE YOUR PIN");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(430, 180, 400, 35);
        l3.add(label1);

        // Registered gmail ID Label and Field
        JLabel gmailLabel = new JLabel("Enter Registered Mail ID: ");
        gmailLabel.setForeground(Color.WHITE);
        gmailLabel.setFont(new Font("System", Font.BOLD, 16));
        gmailLabel.setBounds(430, 210, 250, 35);
        l3.add(gmailLabel);

        gmailField = new JTextField();
        gmailField.setBackground(new Color(65, 125, 128));
        gmailField.setForeground(Color.WHITE);
        gmailField.setBounds(620, 215, 230, 25);
        gmailField.setFont(new Font("Raleway", Font.BOLD, 14));
        l3.add(gmailField);

        // OTP Label and Field
        JLabel otpLabel = new JLabel("Enter OTP: ");
        otpLabel.setForeground(Color.WHITE);
        otpLabel.setFont(new Font("System", Font.BOLD, 16));
        otpLabel.setBounds(430, 255, 150, 35);
        l3.add(otpLabel);

        otpField = new JTextField();
        otpField.setBackground(new Color(65, 125, 128));
        otpField.setForeground(Color.WHITE);
        otpField.setBounds(700, 255, 150, 25);
        otpField.setFont(new Font("Raleway", Font.BOLD, 14));
        l3.add(otpField);

        // New PIN Label and Fields
        JLabel label2 = new JLabel("Enter New PIN: ");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(430, 295, 250, 35);
        l3.add(label2);

        p1 = new JPasswordField();
        p1.setBackground(new Color(65, 125, 128));
        p1.setForeground(Color.WHITE);
        p1.setBounds(700, 295, 150, 25);
        p1.setFont(new Font("Raleway", Font.BOLD, 14));
        l3.add(p1);

        JLabel label3 = new JLabel("Re-Enter New PIN: ");
        label3.setForeground(Color.WHITE);
        label3.setFont(new Font("System", Font.BOLD, 14));
        label3.setBounds(430, 335, 250, 35);
        l3.add(label3);

        p2 = new JPasswordField();
        p2.setBackground(new Color(65, 125, 128));
        p2.setForeground(Color.WHITE);
        p2.setBounds(700, 335, 150, 25);
        p2.setFont(new Font("Raleway", Font.BOLD, 14));
        l3.add(p2);

        // Buttons
        b1 = new JButton("SEND OTP");
        b1.setBounds(750, 365, 100, 35);
        b1.setBackground(new Color(65, 125, 128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(410, 410, 100, 35);
        b2.setBackground(new Color(65, 125, 128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);

        b3 = new JButton("CHANGE");
        b3.setBounds(750, 410, 100, 35);
        b3.setBackground(new Color(65, 125, 128));
        b3.setForeground(Color.WHITE);
        b3.addActionListener(this);
        l3.add(b3);

        setSize(1550, 1080);
        setLayout(null);
        setLocation(0, 0);
        setVisible(true);
    }

    private void sendOTP(String gmail) {
        // Simulate OTP generation and sending
        Random rand = new Random();
        generatedOTP = (int) (Math.random() * 900000) + 100000;
        System.out.println("Generated OTP: " + generatedOTP); // For testing purpose

        // Placeholder for SMS sending service
        MailOTP.sendEmail(gmail,generatedOTP);
        // In production, integrate with an SMS gateway like Twilio
        JOptionPane.showMessageDialog(null, "OTP has been sent to your registered gmail.");
        otpSent = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String pin1 = p1.getText();
            String pin2 = p2.getText();
            String gmail = gmailField.getText();

            if (e.getSource() == b1) {
                // Check if the gmail field is not empty before sending OTP
                if (gmail.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter your registered gmail ID.");
                    return;
                }
                sendOTP(gmail);
            }

            if (e.getSource() == b3) {
                if (!otpSent) {
                    JOptionPane.showMessageDialog(null, "Please generate a OTP.");
                    return;
                }

                if(generatedOTP!=Integer.parseInt(otpField.getText())){
                    JOptionPane.showMessageDialog(null, "OTP is incorrect. Please try again", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!pin1.equals(pin2)) {
                    JOptionPane.showMessageDialog(null, "Entered pins do not match.");
                    return;
                }

                if (p1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter New pin.");
                    return;
                }

                if (p2.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Re-Enter New pin.");
                    return;
                }

                Connn c = new Connn();
                String q1 = "update bank set pin = '" + pin1 + "' where pin = '" + pin + "'";
                String q2 = "update login set pin = '" + pin1 + "' where pin = '" + pin + "'";
                String q3 = "update signupthree set pin = '" + pin1 + "' where pin = '" + pin + "'";

                c.statement.executeUpdate(q1);
                c.statement.executeUpdate(q2);
                c.statement.executeUpdate(q3);

                JOptionPane.showMessageDialog(null, "PIN changed successfully.");
                setVisible(false);
                new main_Class(pin);

            } else if (e.getSource() == b2) {
                new main_Class(pin);
                setVisible(false);
            }

        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Pin("");
    }
}
