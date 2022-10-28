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

	public void readExcel(String filePath) {
		int Id = 0;
		String name = "";
		String email = "";

		try {
			File file = new File(filePath); // creating a new file instance
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file
			while (itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
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

		System.out.println(studentList.getSize());

	}

}
