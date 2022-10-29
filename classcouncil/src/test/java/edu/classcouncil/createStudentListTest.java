package edu.classcouncil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.classcouncil.student.Student;
import edu.classcouncil.student.StudentList;
import edu.classcouncil.student.createStudentList;


public class createStudentListTest {
	
	@Test
	public void commandConstructorTest() {
		
		createStudentList createNewStudentList = new createStudentList();
		
		
		createNewStudentList.readExcel("/Users/edwardxc/git/Emails/Track.xlsx");
		StudentList studentList = new StudentList();
		studentList.addStudent(1,"Cheng Xing","cxing@bowdoin.edu","-");
	Assertions.assertEquals(1, studentList.getSize());
		
		
		Student newStudent = new Student(1,"Cheng Xing","cxing@bowdoin.edu","-");
		
		Assertions.assertEquals(1, newStudent.getId());
	
	}
	
	

}
