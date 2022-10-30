package edu.classcouncil.pair;

import java.util.ArrayList;
import java.util.Collections;

import edu.classcouncil.student.Student;

public class RandomPair {

	//ArrayList<Student> studentList = new ArrayList<Student>();
	
	public RandomPair() {
		
	}

	public ArrayList<Student> createPairs(ArrayList<Student> studentLists) {
		ArrayList<Student> newStudentList = new ArrayList<Student>(studentLists);
	
		
		
		boolean removeCheng = false;
		Student temp = studentLists.get(0);
		if(studentLists.size()%2==1) {
			
			for(int i =0;i<newStudentList.size();i++) {
				if("Cheng Xing".equals(newStudentList.get(i).getName())){
					temp = newStudentList.remove(i);
					System.out.println("removed Cheng");
					System.out.println(newStudentList.size());
				}
			}
		}
		
		
		Collections.shuffle(newStudentList);
		if(removeCheng) {
			newStudentList.add(temp);
		}
		

		return newStudentList;
	}

}
