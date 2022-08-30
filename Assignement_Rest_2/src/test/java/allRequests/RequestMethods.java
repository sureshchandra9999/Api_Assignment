package allRequests;



import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;



import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.ExcelUtils;



public class RequestMethods {
    String GETA,POSTA,PUTA,PATCHA,DELETEA;
    String[] resparr= new String[5];
    
    @Test(dataProvider="LibraryBooks")
    public void init(String method,String baseUri,String endpoint, String reqheaders, String StatusCode,String Payload) throws IOException
    {
        Response response;
        RequestSpecification request;
        JSONObject jobject;
        JsonPath json;
        request=RestAssured.given();
        request.baseUri(baseUri);



       switch (method) {
        
        case "GET":
            
            HashMap<String,String> hashmapget=new HashMap<String, String>();
            String stget = reqheaders;
            String[] arrget = stget.split(";");
            hashmapget.put(arrget[0], arrget[1]);
            request.headers(hashmapget);
            response=request.get(endpoint);
            json=new JsonPath(response.asString());
        //  System.out.println(response.getBody().asString());
            int a=response.getStatusCode();
            String str=Integer.toString(a);
            str=str+".0";
            Assert.assertEquals(str, StatusCode);
             GETA=response.asString();
           //System.out.println(str);
            



           break;
case "POST":
            
            HashMap<String,String> hashmappost=new HashMap<String, String>();
            String stpost = reqheaders;
            String[] arrpost = stpost.split(";", -3);
            hashmappost.put(arrpost[0], arrpost[1]);
            hashmappost.put(arrpost[2], arrpost[3]);
            request.headers(hashmappost);
            response = request.body(Payload).post(endpoint);
            json=new JsonPath(response.asString());
            //System.out.println(response.getBody().asString());
            int a1=response.getStatusCode();
            String str1=Integer.toString(a1);
            str=str1+".0";
            Assert.assertEquals(str, StatusCode);
             POSTA=response.asString();
            //System.out.println(str);
            break;
            
        case "PUT":



           HashMap<String,String> hashmapput=new HashMap<String, String>();
            String stput= reqheaders;
            String[] arrput = stput.split(";", -5);
            hashmapput.put(arrput[0], arrput[1]);
            hashmapput.put(arrput[2], arrput[3]);
            hashmapput.put(arrput[4], arrput[5]);
            request.headers(hashmapput);
            response = request.body(Payload).put(endpoint);
            json=new JsonPath(response.asString());
            //System.out.println(response.getBody().asString());
            int a2=response.getStatusCode();
            String str2=Integer.toString(a2);
            str=str2+".0";
            Assert.assertEquals(str, StatusCode);
            PUTA =response.asString();
            //System.out.println(str);
            break;
        case "PATCH":




            HashMap<String,String> hashmappatch=new HashMap<String, String>();
            String stpatch= reqheaders;
            String[] arrpatch = stpatch.split(";", -5);
            hashmappatch.put(arrpatch[0], arrpatch[1]);
            hashmappatch.put(arrpatch[2], arrpatch[3]);
            hashmappatch.put(arrpatch[4], arrpatch[5]);
            request.headers(hashmappatch);
            response = request.body(Payload).patch(endpoint);
            json=new JsonPath(response.asString());
            //System.out.println(response.getBody().asString());
            int a4=response.getStatusCode();
            String str4=Integer.toString(a4);
            str=str4+".0";
            Assert.assertEquals(str, StatusCode);
             PATCHA=response.asString();
            //System.out.println(str);
            break;
            
case "DELETE":
            
            HashMap<String,String> hashmapd=new HashMap<String, String>();
            String st = reqheaders;
            String[] arr = st.split(";", -5);
            hashmapd.put(arr[0], arr[1]);
            hashmapd.put(arr[2], arr[3]);
            hashmapd.put(arr[4], arr[5]);
            request.headers(hashmapd);
            response=request.delete(endpoint);
            json=new JsonPath(response.asString());
            //System.out.println(response.getBody().asString());
            int a3=response.getStatusCode();
            String str3=Integer.toString(a3);
            str=str3+".0";
            Assert.assertEquals(str, StatusCode);
             DELETEA=response.asString();
            //System.out.println(str);
          break;
        default:
            break;
        }
       Workbook wb = new HSSFWorkbook();
       
       
       OutputStream os
           = new FileOutputStream("response.csv");

       Sheet sheet = wb.createSheet("Response");
       String[] methods = new String[] { "GET","POST","PUT","PATCH","DELETE"};
       String[] fields = new String[] { GETA,POSTA,PUTA,PATCHA,DELETEA };



   // Row with index 1 is the second row in Excel sheet
       
    
    Cell cell;
    Row row1;
    //= row1.createCell(0);



   // Filling the row with the given data, one element per cell,
    // starting from the "A" column (cellIndex = 0).
    for ( int cellIndex = 0; cellIndex < methods.length; cellIndex++ ) {
        row1 = sheet.createRow( cellIndex);
        cell = row1.createCell( 0);
        cell.setCellValue( methods[cellIndex] );
        cell = row1.createCell( 1 );
        cell.setCellValue( fields[cellIndex] );
    }



   wb.write(os);
       }



@DataProvider(name="LibraryBooks")
public Object[][] getData()
{
   return ExcelUtils.getData("./TestData/Test.xlsx","Sheet2");
}
}
           