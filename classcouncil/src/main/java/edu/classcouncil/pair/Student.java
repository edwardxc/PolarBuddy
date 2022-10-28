package edu.classcouncil.pair;

import java.util.ArrayList;
import java.util.List;

public class Student {
	
	String name;
	String email;
	int id;
	List<Integer> pairedStudents;

	public Student(String name, String email, int id) {
		setName(name);
		setEmail(email);
		setId(id);
		initList();
		
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void initList() {
		pairedStudents =new ArrayList<Integer>();
	}
	
	
	
}


