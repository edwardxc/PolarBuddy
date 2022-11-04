package edu.classcouncil;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.classcouncil.pair.RandomPair;
import edu.classcouncil.student.Student;
import edu.classcouncil.student.studentUtils;

public class studentUtilsTest {

	@Test
	public void commandConstructorTest() {
		ArrayList<Student> studentList = new ArrayList<Student>();

		studentList=studentUtils.readExcel("/Users/edwardxc/git/Emails/Track.xlsx");
		//studentUtils.readExcel("/Users/edwardxc/git/Emails/Track.xlsx");
		 RandomPair randomPair= new RandomPair();
		 ArrayList<Student> newStudentList = randomPair.shuffleList(studentList);

		try {
			studentUtils.writeExcel(newStudentList, "/Users/edwardxc/git/Emails/Track.xlsx");
		} catch(Exception e) {
			System.out.println("failed writing list");
		}
		// studentList.addStudent(1,"Cheng Xing","cxing@bowdoin.edu","-");
		 //Assertions.assertEquals(104, studentList.size());

		Student newStudent = new Student("001", "Cheng Xing", "cxing@bowdoin.edu", "-","-");

		Assertions.assertEquals("001", newStudent.getId());
		//System.out.println(newStudent.toString());

	}

}
