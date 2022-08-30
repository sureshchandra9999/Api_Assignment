package utility;



import java.io.File;
import java.io.IOException;



import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;





public class ExcelUtils {




	// util method for fetching data from excel

	public static Object[][] getData(String path, String sheetname) {

		Object[][] objarr=null;

		// read data from file at the given path, and for resp sheet
		try {
			Workbook wb=WorkbookFactory.create(new File(path));

			Sheet sh=wb.getSheet(sheetname);

			int rows=sh.getLastRowNum();
			int cols=sh.getRow(1).getLastCellNum();

			objarr=new Object[rows][cols];

			for(int i=0;i<rows;i++)
			{
				for(int j=0;j<cols;j++)
				{
					objarr[i][j]=sh.getRow(i+1).getCell(j).toString();
				}
			}

		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return objarr;
	}




}