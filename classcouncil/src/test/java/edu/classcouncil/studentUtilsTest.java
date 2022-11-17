package edu.classcouncil;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.classcouncil.email.emailUtils;
import edu.classcouncil.pair.RandomPair;
import edu.classcouncil.student.Student;
import edu.classcouncil.student.studentUtils;

public class studentUtilsTest {

	@Test
	public void newStudentTest() {

		Student newStudent = new Student("001", "Cheng Xing", "cxing@bowdoin.edu", "-", "-");

		Assertions.assertEquals("001", newStudent.getId());
		// System.out.println(newStudent.toString());

	}

//	@Test
//	public void randomPairTest() {
//		ArrayList<Student> studentList = new ArrayList<Student>();
//		String str = "SeniorClassweek1 copy 2.xlsx";
//		studentList = studentUtils.readExcel("/Users/edwardxc/git/Emails/" + str);
//		RandomPair randomPair = new RandomPair();
//		ArrayList<Student> newStudentList = randomPair.shuffleList(studentList);
//		try {
//			studentUtils.writeExcel(newStudentList, "/Users/edwardxc/git/Emails/" + str);
//		} catch (Exception e) {
//			System.out.println("failed writing list");
//		}
//	}

	/*
	 * @Test public void emailSenderTest() { ArrayList<Student> studentList = new
	 * ArrayList<Student>(); // String str = "SeniorClass.xlsx"; String str =
	 * "SeniorClass.xlsx"; studentList =
	 * studentUtils.readExcel("/Users/edwardxc/git/Emails/" + str); RandomPair
	 * randomPair = new RandomPair(); ArrayList<Student> newStudentList =
	 * randomPair.shuffleList(studentList); try {
	 * studentUtils.writeExcel(newStudentList, "/Users/edwardxc/git/Emails/" + str);
	 * } catch (Exception e) { System.out.println("failed writing list"); }
	 * 
	 * emailUtils emailSender = new emailUtils(); try {
	 * emailSender.send(studentList,0); } catch (Exception e) {
	 * System.out.println("failed sending email"); }
	 * 
	 * }
	 */

	@Test
	public void emailSenderTest2() {


		emailUtils emailSender = new emailUtils();

		ArrayList<Student> studentList2 = studentUtils
				.readExcel("/Users/edwardxc/git/Emails/" + "X_unsortedweek2.xlsx");
		try {
			emailSender.sendTwoEmails(studentList2, 218);
		} catch (Exception e) {
			System.out.println("failed sending email");
		}

	}
	
	

//	@Test
//	public void sendPairEmailTest() {
//
//		emailUtils emailSender = new emailUtils();
//		try {
//			emailSender.sendPairEmail("cxing@bowdoin.edu", "edwardxc@126.com", "subject1", "body");
//		} catch (MessagingException e) {
//			System.out.println("failed sending email");
//		}
//
//	}

	/*
	 * @Test public void convertSortedtoUnsorted() { ArrayList<Student> studentList
	 * = new ArrayList<Student>(); String str = "ClassCouncilweek1.xlsx"; //String
	 * str = "ClassCouncilweek1.xlsx";
	 * 
	 * studentList = studentUtils.readExcel("/Users/edwardxc/git/Emails/" + str);
	 * try {
	 * studentUtils.writeExcel(studentUtils.writeUnsortedFromSorted(studentList),
	 * "/Users/edwardxc/git/Emails/" + "ClassCouncilweek1Unsorted.xlsx"); } catch
	 * (Exception e) { System.out.println("failed writing list"); }
	 * 
	 * }
	 */

}
