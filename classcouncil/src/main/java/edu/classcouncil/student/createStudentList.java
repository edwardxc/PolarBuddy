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

		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			Iterator<Row> itr = sheet.iterator();
			while (itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					Id = (int) cell.getNumericCellValue();
					cell = cellIterator.next();
					name = cell.getStringCellValue();
					cell = cellIterator.next();
					email = cell.getStringCellValue();

				}
				studentList.addStudent(Id, name, email);
			}
			wb.close();
		} catch (Exception e) {
			throw new IllegalArgumentException("Error when creating student list");
		}

		// System.out.println(studentList.getSize());

	}

}
