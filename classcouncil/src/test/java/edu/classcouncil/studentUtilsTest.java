package edu.classcouncil;

import java.util.ArrayList;
import java.util.List;

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
	/*
	 * @Test public void randomPairTest() { ArrayList<Student> studentList = new
	 * ArrayList<Student>(); String str = "Track.xlsx"; studentList =
	 * studentUtils.readExcel("/Users/edwardxc/git/Emails/" + str); RandomPair
	 * randomPair = new RandomPair(); ArrayList<Student> newStudentList =
	 * randomPair.shuffleList(studentList); try {
	 * studentUtils.writeExcel(newStudentList, "/Users/edwardxc/git/Emails/" + str);
	 * } catch (Exception e) { System.out.println("failed writing list"); } }
	 */

	@Test
	public void emailSenderTest() {
		ArrayList<Student> studentList = new ArrayList<Student>();
		// String str = "SeniorClass.xlsx";
		String str = "ClassCouncil.xlsx";
		studentList = studentUtils.readExcel("/Users/edwardxc/git/Emails/" + str);
		RandomPair randomPair = new RandomPair();
		ArrayList<Student> newStudentList = randomPair.shuffleList(studentList);
		try {
			studentUtils.writeExcel(newStudentList, "/Users/edwardxc/git/Emails/" + str);
		} catch (Exception e) {
			System.out.println("failed writing list");
		}

		emailUtils emailSender = new emailUtils();
		try {
			emailSender.send(studentList);
		} catch (Exception e) {
			System.out.println("failed sending email");
		}

	}

}
