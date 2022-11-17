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
import javax.mail.internet.AddressException;
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

	public void send(ArrayList<Student> StudentList, int from) {
		try {
			init(2);
		} catch (MessagingException e) {
			throw new IllegalArgumentException("Fail to init email util");
		}
		for (int i = from; i < StudentList.size(); i++) {
			Student student = StudentList.get(i);
			String pairOfTheWeekId = student.getPairOfTheWeek();
			String pairOfTheWeek = "";
			for (int j = 0; j < StudentList.size(); j++) {
				if (pairOfTheWeekId.equals(StudentList.get(j).getId())) {
					pairOfTheWeek = StudentList.get(j).getName().trim();
				}
			}

			// String emailSubject = "Polar Buddy of the week2";
			String emailSubject = "Polar Buddy - week 1";
			String[] arr = (student.getName().trim()).split(" ");

			String emailBody = "Hey " + arr[0] + ",\n\nYour Polar buddy of the week is " + pairOfTheWeek;
			emailBody += ".\n\nThe tasks of the week are:\n"
					+ "Option 1: Ask a professor to take a BeReal of you and your Buddy (or selfie with)\n"
					+ "Option 2: Ask a townie (unaffiliated with Bowdoin) to take a BeReal of you and your Buddy (or selfie with)\n";

			emailBody += "\n\nRules:\n" + "1. Reach out to your new Buddy!\n"
					+ "2. Complete the activity with your Bud! Your and your Buddy will receive $3 credit for completing Option 1, or $5 credit for Option 2. "
					+ "These credits can be put towards class merch items and other prizes that are in the works!\n"
					+ "3. Send a pic/vid of you and your buddy completing the activity to Class2023 Instagram (@Bowdoin2023) or email to cxing@bowdoin.edu\n";

			emailBody += "\nBest,\nSenior Class Council" + " \n\n\n\n"
					+ "Opt-out link: https://forms.office.com/r/LQ0CHKwwRB";

			sendIndividualEmail(student.getEmail(), emailSubject, emailBody);
			System.out.println(student.getName() + pairOfTheWeek);
			// System.out.println(student.getName() + emailBody);
		}
	}

	public void sendTwoEmails(ArrayList<Student> StudentList, int from) {
		try {
			init(2);
		} catch (MessagingException e) {
			throw new IllegalArgumentException("Fail to init email util");
		}
		int size = StudentList.size();
		System.out.println("size is" + size);
		if (size % 2 == 1) {
			size = size - 1;
			System.out.println("Not sending email to Cheng as Odd number list");
		}
		for (int i = from; i < size; i += 2) {
			Student student1 = StudentList.get(i);
			Student student2 = StudentList.get(i + 1);

			// String emailSubject = "Polar Buddy of the week2";
			String emailSubject = "Polar Buddy - week 2";
			String[] name1 = (student1.getName().trim()).split(" ");
			String[] name2 = (student2.getName().trim()).split(" ");

			String emailBody = "Hey " + name1[0] + " and " + name2[0]
					+ ",\nYou guys are Polar Buddies!";
			emailBody += "\n\nThe tasks of the week are:\n"
					+ "Option 1: Bedroom Tour\n"
					+ "Option 2: Outfit Swap or Twin\n";

			emailBody += "\n\nRules:\n" + "1. Reach out to your new Buddy!\n"
					+ "2. Complete the activity with your Bud by next Wednesday (Nov 16th)! Your and your Buddy will receive $3 credit for completing Option 1, or $5 credit for Option 2. "
					+ "These credits can be put towards class merch items and other prizes (shuuu, Pub/C-Store/Cafe coupon) that are in the works!\n"
					+ "3. Send a pic/vid of you and your buddy completing the activity to Class2023 Instagram (@Bowdoin2023), or to polarbuddy@outlook.com by replying this email.\n";

			emailBody += "\nBest,\nSenior Class Council" + " \n\n\n\n"
					+ "Opt-out link: https://forms.office.com/r/LQ0CHKwwRB";

			//sendPairEmail(student1.getEmail(), student2.getEmail(), emailSubject, emailBody);
			sendPairEmail(student1.getEmail(), student2.getEmail(), emailSubject, emailBody);
			System.out.println(student1.getName() + student2.getName());
			// System.out.println(student.getName() + emailBody);
		}

	}

	public void init(int option) throws AddressException, MessagingException {

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

	public void sendIndividualEmail(String toAddress, String EmailSubject, String EmailBody) {
		try {
			/*
			 * properties = new Properties(); properties.put("mail.smtp.host", HOSTNAME);
			 * properties.put("mail.smtp.port", STARTTLS_PORT);
			 * properties.put("mail.smtp.auth", AUTH);
			 * properties.put("mail.smtp.starttls.enable", STARTTLS);
			 * properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); Authenticator auth =
			 * new Authenticator() { protected PasswordAuthentication
			 * getPasswordAuthentication() { return new PasswordAuthentication(username,
			 * password); } };
			 * 
			 * session = Session.getInstance(properties, auth);
			 * 
			 * mimeMessage = new MimeMessage(session);
			 * 
			 * mimeMessage.setFrom(new InternetAddress(username));
			 * 
			 * mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(toAddress));
			 * mimeMessage.setSubject(EmailSubject);
			 * 
			 * mimeMessage.setText(EmailBody);
			 * 
			 * Transport.send(mimeMessage);
			 * 
			 */
			System.out.println("Email Sent from " + username + " to " + toAddress);

		} catch (Exception e) {
			throw new IllegalArgumentException("Fail to send email to " + toAddress);
		}
	}

	public void sendPairEmail(String toAddress1, String toAddress2, String EmailSubject, String EmailBody) {
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

			mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(toAddress1));
			mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(toAddress2));
			mimeMessage.setSubject(EmailSubject);

			mimeMessage.setText(EmailBody);

			Transport.send(mimeMessage);
			
			System.out.println("Email Sent from " + username + " to " + toAddress1 + " + " + toAddress2);

		} catch (Exception e) {
			throw new IllegalArgumentException("Fail to send email to " + toAddress);
		}
	}

}