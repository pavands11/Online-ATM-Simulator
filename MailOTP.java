package bank.management.system;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailOTP {

    public static void sendEmail(String recipientEmail, int OTP) {
        // Sender's email ID needs to be mentioned
        String from = "ronakrjainmandya@gmail.com";
        String password = "aqgl zqoq myvr jouv";

        // Assuming you are sending email from through Gmail's SMTP server
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

            // Set Subject: header field
            message.setSubject("OTP from PTR CO-Operative Bank");

            // Now set the actual message
            message.setText("Your OTP is " + OTP + "\nPlease do not share it with anyone");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        sendEmail("chirag973one@gmail.com", 123456);
    }
}
