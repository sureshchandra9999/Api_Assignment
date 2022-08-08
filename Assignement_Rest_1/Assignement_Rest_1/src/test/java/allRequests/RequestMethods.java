package allRequests;

import java.util.HashMap;

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
	@Test(dataProvider="LibraryBooks")
	public void init(String method,String baseUri,String endpoint, String reqheaders, String StatusCode,String Payload)
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
			//System.out.println(str);
          break;
		default:
			break;
		}

	}


	@DataProvider(name="LibraryBooks")
	public Object[][] getData()
	{
		return ExcelUtils.getData("./TestData/Test.xlsx","Sheet2");
	}

}
