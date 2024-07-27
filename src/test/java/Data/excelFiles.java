package Data;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class excelFiles
{
    static FileInputStream ex=null;
    static  String filepath = System.getProperty("user.dir")+"/src/test/java/Data/";
    public FileInputStream getFileInputStream(String filepath)
    {

        File srcFile = new File(filepath);


        try{ex = new FileInputStream(srcFile);}
        catch (FileNotFoundException e){
            System.out.println("Test Data File Not Found Treminating process : Check file path of TestData");
            System.exit(0);
        }
        return ex;

    }

    public Object[][] getExcelData(String filename) throws IOException {
        ex = getFileInputStream(filepath + filename);
        XSSFWorkbook wb = new XSSFWorkbook(ex);
        XSSFSheet sheet = wb.getSheetAt(0);

        int totalNuOfRows = (sheet.getLastRowNum() + 1);
        int totalNuOfCols = 2; // Assuming you know the number of columns is 2

        String[][] arrayExcelOfData = new String[totalNuOfRows][totalNuOfCols];

        for (int i = 0; i < totalNuOfRows; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < totalNuOfCols; j++) {
                if (row != null) {
                    // Check if the cell is null
                    if (row.getCell(j) != null) {
                        arrayExcelOfData[i][j] = row.getCell(j).toString();
                    } else {
                        // Assign a default value or keep it null/empty
                        arrayExcelOfData[i][j] = ""; // or you can use "NULL" or any default value
                    }
                }
            }
        }
        wb.close();
        ex.close();
        return arrayExcelOfData;}
}