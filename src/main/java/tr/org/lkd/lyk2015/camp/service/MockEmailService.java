package tr.org.lkd.lyk2015.camp.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class MockEmailService implements EmailService {

	@Override
	public void sendEmailConfirmation(String name, String surname, String email, String subject, String content) {
		final String username = "lyk2015java@gmail.com";
		final String from = "lyk2015java@gmail.com";
		final String password = "510B619-J1|#!rD";
		final String to = email;

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			// message.setText();
			message.setContent("Dear " + name + " " + surname
					+ ", \n\n No spam to my email, please!<br><br>Basvurunuzun onaylanmasi icin asagidaki linke tiklayin."
					+ "<br><br>" + content, "text/html; charset=utf-8");

			Transport.send(message);

			System.out.println("Done");
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
