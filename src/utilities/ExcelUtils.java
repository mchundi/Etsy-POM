package utilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;

	//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
	public void setExcelFile(String Path,String SheetName) throws Exception {
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			System.out.println("Excel sheet opened");
		} catch (Exception e){
			throw (e);
		}
	}
	//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
	public String getCellData(int RowNum, int ColNum) throws Exception{
		try{
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			return CellData;
		}catch (Exception e){
			return"";
		}
	}
	//This method is to write in the Excel cell, Row num and Col num are the parameters
	@SuppressWarnings("static-access")
	public void setCellData(String Result,  int RowNum, int ColNum) throws Exception    {
		try{
			Row  = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} 
			else {
				Cell.setCellValue(Result);
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		}catch(Exception e){
			throw (e);
		}
	}
    //this method is to navigate to the row that has the testcase name to be retrieved
	public int getRowContains(String sTestCaseName, int colNum) throws Exception{
		int i;
		try {
			int rowCount = new ExcelUtils().getRowUsed();
			for ( i=0 ; i<rowCount; i++){
				if  (new ExcelUtils().getCellData(i,colNum).equalsIgnoreCase(sTestCaseName)){
					break;
				}
			}
			return i;
		}catch (Exception e){
			System.out.println("Class ExcelUtil | Method getRowContains | Exception desc : " + e.getMessage());
			throw(e);
		}
	}
	//this method is used to get the count of rows that has data
	public int getRowUsed() throws Exception {
		try{
			int RowCount = ExcelWSheet.getLastRowNum();
			System.out.println("Total number of Row used return as < " + RowCount + " >.");		
			return RowCount;
		}catch (Exception e){
			System.out.println("Class ExcelUtil | Method getRowUsed | Exception desc : "+e.getMessage());
			System.out.println(e.getMessage());
			throw (e);
		}
	}
	
	public String getTestCaseName(String sTestCase)throws Exception{
		String value = sTestCase;
		try{
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");	
			value = value.substring(posi + 1);
			return value;
				}catch (Exception e){
			System.out.println("Class Utils | Method getTestCaseName | Exception desc : "+e.getMessage());
			throw (e);
					}
			}
}