package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {

	public ArrayList<String> getData(String testcaseName) throws IOException {
		ArrayList<String> a = new ArrayList<String>();

		FileInputStream fis = new FileInputStream(
				"F:\\RestAPIAutomation\\APIFramework\\src\\main\\java\\ExcelUtility.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		int sheetCount = workBook.getNumberOfSheets();
		for (int i = 0; i < sheetCount; i++) {
			if (workBook.getSheetName(i).equalsIgnoreCase("Application")) {
				XSSFSheet sheet = workBook.getSheetAt(i); // sheet is a collection of rows
				Iterator<Row> rows = sheet.iterator(); // row is a collection of columns
				Row firstRow = rows.next();
				Iterator<Cell> cells = firstRow.cellIterator();
				int k = 0;
				int column = 0;
				while (cells.hasNext()) {
					Cell cell = cells.next();
					if (cell.getStringCellValue().equalsIgnoreCase("Testcases")) {
						column = k;
					}
					k++;
				}
				System.out.println(column);
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell cell = cv.next();
							if (cell.getCellType() == CellType.STRING) {
								a.add(cell.getStringCellValue());
							} else {
								a.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
							}
						}
					}
				}
			}
		}
		return a;
	}

	
}
