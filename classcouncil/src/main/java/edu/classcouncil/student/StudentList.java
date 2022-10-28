package edu.classcouncil.student;

import java.util.ArrayList;
import java.util.List;



public class StudentList {
	
	private List<Student> studentList;
	
	public StudentList() {
		studentList = new ArrayList<Student>();
	}
	
	public List<Student> getStudentList() {
		return studentList;
		
	}
	
	public void addStudent(int id, String name, String email) {
		Student newStudent = new Student(id, name, email);
		studentList.add(newStudent);
	}
	
	public int getSize() {
		return studentList.size();
	}

}
