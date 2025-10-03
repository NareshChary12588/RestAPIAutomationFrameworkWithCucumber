package resources;

import java.io.IOException;
import java.util.ArrayList;

public class testReadExcelData {

	public static void main(String[] args) throws IOException {
		
		ReadExcelData testData=new ReadExcelData();
		ArrayList<String> output=testData.getData("purchase");
		System.out.println(output.get(0));
		System.out.println(output.get(1));
		System.out.println(output.get(2));
		System.out.println(output.get(3));
	}
	

}
