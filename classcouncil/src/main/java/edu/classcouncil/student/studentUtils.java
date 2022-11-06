package edu.classcouncil.student;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class studentUtils {

	// List<Student> studentList = new ArrayList<Student>();

	public static ArrayList<Student> readExcel(String filePath) {
		ArrayList<Student> studentList = new ArrayList<Student>();
		String Id = "";
		String name = "";
		String email = "";
		String pairOfTheWeek = "";
		String pairedStudentsString = "";

		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			Iterator<Row> itr = sheet.iterator();
			// System.out.println(filePath);

			while (itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					Id = cell.getStringCellValue();
					cell = cellIterator.next();
					name = cell.getStringCellValue();
					cell = cellIterator.next();
					email = cell.getStringCellValue();
					cell = cellIterator.next();
					pairOfTheWeek = cell.getStringCellValue();
					cell = cellIterator.next();
					pairedStudentsString = cell.getStringCellValue();
				}
				studentList.add(new Student(Id, name, email, pairOfTheWeek, pairedStudentsString));
				// System.out.println(name);
			}

			wb.close();
		} catch (Exception e) {
			throw new IllegalArgumentException("Error when creating student list");
		}
		return studentList;

	}

	public static void writeExcel(ArrayList<Student> studentList, String filePath) throws Exception {
		String[] temp = new String[5];

		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet spreadsheet = workbook.createSheet(" Student Data ");
			XSSFRow row;

			int rowid = 0;
			for (Student student : studentList) {
				temp = student.toStringArray();
				row = spreadsheet.createRow(rowid++);
				for (int i = 0; i < temp.length; i++) {
					Cell cell = row.createCell(i);
					cell.setCellValue(temp[i]);
				}
			}

			FileOutputStream out = new FileOutputStream(
					new File(filePath.substring(0, filePath.length() - 5) + "s.xlsx"));
			workbook.write(out);
			out.close();
			workbook.close();
		} catch (Exception e) {
			throw new IllegalArgumentException("Error when exporting student list");
		}

	}
}
