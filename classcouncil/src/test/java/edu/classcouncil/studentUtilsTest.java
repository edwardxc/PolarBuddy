package edu.classcouncil;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.classcouncil.student.Student;
import edu.classcouncil.student.studentUtils;

public class studentUtilsTest {

	@Test
	public void commandConstructorTest() {
		List<Student> studentList = new ArrayList<Student>();

		studentList=studentUtils.readExcel("/Users/edwardxc/git/Emails/Track.xlsx");
		//studentUtils.readExcel("/Users/edwardxc/git/Emails/Track.xlsx");
		try {
			studentUtils.writeExcel(studentList, "/Users/edwardxc/git/Emails/Track.xlsx");
		} catch(Exception e) {
			
		}
		// studentList.addStudent(1,"Cheng Xing","cxing@bowdoin.edu","-");
		 //Assertions.assertEquals(104, studentList.size());

		Student newStudent = new Student(1, "Cheng Xing", "cxing@bowdoin.edu", "-","-");

		Assertions.assertEquals(1, newStudent.getId());
		//System.out.println(newStudent.toString());

	}

}
