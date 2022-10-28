package edu.classcouncil.student;

import java.util.ArrayList;
import java.util.List;

public class Student {

	String name;
	String email;
	int id;
	List<Integer> pairedStudents;

	public Student(int id, String name, String email) {
		setId(id);
		setName(name);
		setEmail(email);
		initList();

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

	public void initList() {
		pairedStudents = new ArrayList<Integer>();
	}

	public List<Integer> getPairedStudents() {
		return pairedStudents;
	}

}
