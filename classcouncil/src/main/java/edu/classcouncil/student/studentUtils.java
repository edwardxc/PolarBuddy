package edu.classcouncil.student;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class studentUtils {
	
	//List<Student> studentList = new ArrayList<Student>();
	
	public static List<Student> readExcel(String filePath) {
		List<Student> studentList = new ArrayList<Student>();
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
					cell = cellIterator.next();
					name = cell.getStringCellValue();
					cell = cellIterator.next();
					email = cell.getStringCellValue();
					cell = cellIterator.next();
					pairedStudentsString = cell.getStringCellValue();
				}
				studentList.add(new Student(Id, name, email,pairedStudentsString));
				System.out.println(name);
			}
			wb.close();
		} catch (Exception e) {
			throw new IllegalArgumentException("Error when creating student list");
		}
		return studentList;

}
}
