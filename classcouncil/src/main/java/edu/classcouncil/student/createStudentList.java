package edu.classcouncil.student;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class createStudentList {

	StudentList studentList;

	public createStudentList() {

		studentList = new StudentList();

	}

	public StudentList getStudentList() {
		return studentList;
	}

	public void readExcel(String filePath) {
		int Id = 0;
		String name = "";
		String email = "";
		String pairedStudentsString ="";

		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			Iterator<Row> itr = sheet.iterator();
			System.out.println(filePath);
			while (itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					Id = (int) cell.getNumericCellValue();
					System.out.println(Id);
					cell = cellIterator.next();
					name = cell.getStringCellValue();
					System.out.println(name);
					cell = cellIterator.next();
					email = cell.getStringCellValue();
					System.out.println(email);
					cell = cellIterator.next();
					pairedStudentsString = cell.getStringCellValue();
					System.out.println(pairedStudentsString);
				}
				studentList.addStudent(Id, name, email,pairedStudentsString);
				System.out.println(name);
			}
			wb.close();
		} catch (Exception e) {
			throw new IllegalArgumentException("Error when creating student list");
		}

		// System.out.println(studentList.getSize());

	}

}
