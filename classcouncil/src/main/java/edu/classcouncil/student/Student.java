package edu.classcouncil.student;

import java.util.ArrayList;
import java.util.List;

public class Student {

	String name;
	String email;
	int id;
	String pairedStudentsString;
	String[] pairedStudents;

	public Student(int id, String name, String email, String pairedStudentsString) {
		setId(id);
		setName(name);
		setEmail(email);
		setPairedStudentsString(pairedStudentsString);
		setPairedStudents();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPairedStudentsString(String pairedStudentsString) {
		this.pairedStudentsString = pairedStudentsString;
	}

	public void setPairedStudents() {
		String temp=pairedStudentsString.substring(1);
	    String[] pairedStudentsIds=temp.split("-");
	    System.out.println(pairedStudentsIds.length);
	    
	}

	public String[] getPairedStudents() {
		return pairedStudents;
	}

}
