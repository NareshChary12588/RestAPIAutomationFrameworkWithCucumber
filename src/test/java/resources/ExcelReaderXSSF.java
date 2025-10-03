package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderXSSF {
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private String filePath;
	
	public ExcelReaderXSSF(String filePath, String sheetName) throws IOException {
		try {
			FileInputStream fis=new FileInputStream(filePath);
			workbook=new XSSFWorkbook(fis);
			sheet=workbook.getSheet(sheetName);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public String getCellData(int rowNum, int colNum) {
		XSSFRow row=sheet.getRow(rowNum);
		XSSFCell cell=row.getCell(colNum);
		return cell.toString();
		
	}
	
	public int getRowCount() {
		int rowCount=sheet.getPhysicalNumberOfRows();
		return rowCount;
	}

	public int getColCount() {
	int colCount=sheet.getRow(0).getPhysicalNumberOfCells();
		return colCount;
	}
	
	//To read the data dynamically using data provider
	
	public Object[][] getExcelData(){
			int rows=getRowCount();
			int cols=getColCount();
			Object[][] data=new Object[rows-1][cols]; //exclude the header row
			for (int i=1;i<rows;i++) {
				for(int j=0;j<cols;j++) {
					data[i-1][j]=sheet.getRow(i).getCell(j).toString();
				}
			}
			return data;
	}
	
	public void setCellData(int rowNum,int colNum,String value) {
		try {
			XSSFRow row=sheet.getRow(rowNum);
			if(row==null) {
				row=sheet.createRow(rowNum);
			}
			XSSFCell cell=row.getCell(colNum);
			if(cell==null) {
				cell=row.createCell(colNum);
			}
			cell.setCellValue(value);
			//save changes to file
			FileOutputStream fos=new FileOutputStream(filePath);
			workbook.write(fos);
			fos.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
