package edu.classcouncil.email;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.classcouncil.student.createStudentList;

/**
 * 
 * @author Cheng Xing
 *
 */
public class EmailSender {
	Properties properties;
	Session session;
	MimeMessage mimeMessage;

	String username;
	String password;
	String HOSTNAME;
	String STARTTLS_PORT;
	boolean STARTTLS = true;
	boolean AUTH = true;
	String fromAddress;
	String toAddress;
	static String EmailSubject;
	static String EmailBody;

	String bowdoinAddress;
	String bowdoinPassword;
	String personalAddress;
	String personalPassword;

	public static void main(String args[]) throws MessagingException {
		EmailSender emailSender = new EmailSender();
		emailSender.init(2);
		setEmailSubject();
		setEmailBody();
		emailSender.sendEmail(EmailSubject, EmailBody);
		
		
		//createStudentList createNewStudentList = new createStudentList();
		//createNewStudentList.readExcel("/Users/edwardxc/git/buddySystem/Track.xlsx");
	}

	public void init(int option) {

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("/Users/edwardxc/git/email.txt"));
			bowdoinAddress = reader.readLine();
			bowdoinPassword = reader.readLine();
			personalAddress = reader.readLine();
			personalPassword = reader.readLine();
			reader.close();

		} catch (IOException e) {
			throw new IllegalArgumentException("Fail to read email address and password");
		}

		switch (option) {
		case 1:
			username = bowdoinAddress;
			password = bowdoinPassword;
			toAddress = personalAddress;
			break;
		case 2:
			username = personalAddress;
			password = personalPassword;
			toAddress = bowdoinAddress;
			break;
		}

		fromAddress = username;

		HOSTNAME = "smtp.office365.com";
		STARTTLS_PORT = "587";

	}

	public static void setEmailSubject() {
		EmailSubject = "This is a subject";

	}

	public static void setEmailBody() {
		EmailBody = "Hello World";

	}

	public void sendEmail(String EmailSubject, String EmailBody) {
		try {
			properties = new Properties();
			properties.put("mail.smtp.host", HOSTNAME);
			properties.put("mail.smtp.port", STARTTLS_PORT);
			properties.put("mail.smtp.auth", AUTH);
			properties.put("mail.smtp.starttls.enable", STARTTLS);
			properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			};

			session = Session.getInstance(properties, auth);

			mimeMessage = new MimeMessage(session);

			mimeMessage.setFrom(new InternetAddress(fromAddress));
			mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(toAddress));
			mimeMessage.setSubject(EmailSubject);

			mimeMessage.setText(EmailBody);

			Transport.send(mimeMessage);
			System.out.println("Email Sent from " + username);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}