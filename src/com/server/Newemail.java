package com.server;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Newemail {
	
	public static void mailsend(String msg, String toEmail) {

    System.setProperty("https.protocols", "TLSv1.2");

    String fromEmail = "cybersecurity2025.26@gmail.com";
    final String username = fromEmail;
    final String password = "jmtq sanl tnuz xews";

    Properties properties = new Properties();
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.starttls.required", "true");
    properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

    Session session = Session.getInstance(properties, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });

    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Access System  BLOCKED IP ");

        message.setText(msg);

        Transport.send(message);
        System.out.println("Email sent successfully!");

    } catch (MessagingException e) {
        e.printStackTrace();
    }
}
	public static void main(String[] args) {
		String toEmail = "ajinkyaransubhe7@gmail.com";
	//	222mailsend("Not Available Quantity", toEmail);
//		mailsend("demo","om",toEmail);	
    }

}
