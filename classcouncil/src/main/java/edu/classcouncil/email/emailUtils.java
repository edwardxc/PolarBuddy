package edu.classcouncil.email;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.classcouncil.student.Student;

/**
 * 
 * @author Cheng Xing
 *
 */
public class emailUtils {
	Properties properties;
	Session session;
	MimeMessage mimeMessage;

	String username;
	String password;
	final String HOSTNAME = "smtp.office365.com";
	final String STARTTLS_PORT = "587";
	boolean STARTTLS = true;
	boolean AUTH = true;
	String fromAddress;
	String toAddress;

	String bowdoinAddress;
	String bowdoinPassword;
	String personalAddress;
	String personalPassword;

	public static void main(String args[]) throws MessagingException {

		emailUtils emailSender = new emailUtils();

		String EmailSubject = emailSender.setEmailSubject();
		String EmailBody = emailSender.setEmailBody();

		// Option 1: using Bowdoin email
		// Option 2: using personal outlook email
		emailSender.init(1);
		emailSender.setEmailSubject();
		emailSender.setEmailBody();
		// emailSender.sendEmail(toAddress, EmailSubject, EmailBody);

	}

	public void send(ArrayList<Student> StudentList) {
		init(2);
		for (int i = 0; i < StudentList.size(); i++) {
			Student student = StudentList.get(i);
			String pairOfTheWeekId = student.getPairOfTheWeek();
			String pairOfTheWeek = "";
			for (int j = 0; j < StudentList.size(); j++) {
				if (pairOfTheWeekId.equals(StudentList.get(j).getId())) {
					pairOfTheWeek = StudentList.get(j).getName();
				}
			}

			String emailSubject = "Polar Buddy of the week2";
			String emailBody = "Hi " + student.getName() + "\nYour buddy is" + pairOfTheWeek;
			emailBody += "\nThe tasks of the week are: 1 2 ";

			sendEmail(student.getEmail(), emailSubject, emailBody);
			// System.out.println(student.getName() + emailBody);
		}
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

	}

	public String setEmailSubject() {
		String emailSubject = "This is a Subject";
		return emailSubject;

	}

	public String setEmailBody() {
		String emailBody = "This is a body";
		return emailBody;

	}

	public void sendEmail(String toAddress, String EmailSubject, String EmailBody) {
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

			mimeMessage.setFrom(new InternetAddress(username));
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