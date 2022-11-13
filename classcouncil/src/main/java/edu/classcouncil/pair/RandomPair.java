package edu.classcouncil.pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.nimbusds.jose.util.ArrayUtils;

import edu.classcouncil.student.Student;
import edu.classcouncil.student.studentUtils;

public class RandomPair {

	public RandomPair() {

	}

	public ArrayList<Student> shuffleList(ArrayList<Student> studentLists) {
		ArrayList<Student> newStudentList = new ArrayList<Student>(studentLists);

		boolean removeCheng = false;
		Student temp = newStudentList.get(0);
		if (studentLists.size() % 2 == 1) {
			System.out.println("odd");

			for (int i = 0; i < newStudentList.size(); i++) {
				if (" Cheng Xing ".equals(newStudentList.get(i).getName())) {
					temp = newStudentList.remove(i);
					System.out.println("removed Cheng");
					System.out.println(newStudentList.size());
					removeCheng = true;
				}
			}
		}

		Collections.shuffle(newStudentList);

		boolean done = false;
		while (!done) {
			ArrayList<Student> tempStudentList = createPairs(newStudentList);
			System.out.println("list is shuffled" + tempStudentList.size());
			if (tempStudentList != null) {
				done = true;
				newStudentList = new ArrayList<Student>(tempStudentList);
			}
		}

		if (removeCheng) {
			newStudentList.add(temp);
		}
		
		try {
			studentUtils.writeExcel(newStudentList, "/Users/edwardxc/git/Emails/" + "X_unsorted.xlsx");
		} catch (Exception e) {
			System.out.println("failed writing list");
		}
		Collections.sort(newStudentList);
		
		try {
			studentUtils.writeExcel(newStudentList, "/Users/edwardxc/git/Emails/" + "X_sorted.xlsx");
		} catch (Exception e) {
			System.out.println("failed writing list");
		}
		return newStudentList;
	}

	public ArrayList<Student> createPairs(ArrayList<Student> studentLists) {
		ArrayList<Student> newStudentList = new ArrayList<Student>(studentLists);
		ArrayList<Student> returnStudentList = new ArrayList<Student>();
		int counter = 0;
		while (newStudentList.size() >= 2) {
			Student temp1 = newStudentList.remove(0);
			temp1.updatePairedStudents();
			Student temp2 = newStudentList.remove(0);
			boolean goodPair = false;
			while (!goodPair) {
				if (Arrays.asList(temp1.getPairedStudents()).contains(temp2.getId())) {
					System.out.println("alread paired");
					if (newStudentList.size() == 0) {
						return null;
					} else {
						newStudentList.add(temp2);
						temp2 = newStudentList.remove(0);
					}
				} else {
					goodPair = true;
				}
			}

			temp1.updateNewPair(temp2.getId());
			temp2.updatePairedStudents();
			temp2.updateNewPair(temp1.getId());
			returnStudentList.add(temp1);
			returnStudentList.add(temp2);
			counter += 2;
		}

		return returnStudentList;
	}

}
