package customTools;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMail {
	public static void sendMail(String to, String from, String subject, String body, boolean bodyIsHTML)
			throws MessagingException {
		System.out.println("MAilll");
		// 1 - get a mail session
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", 8081);
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.quitwait", "false");
        //NOTE: Session object is part of javax.mail.Session
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);
        
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        if (bodyIsHTML) {
        message.setContent(body, "text/html");
        } else {
        message.setText(body);
        }
        
        Address fromAddress = new InternetAddress(from);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);
        
        Transport transport = session.getTransport();
        transport.connect();
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
	}
}
