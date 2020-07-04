package test;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddData;
import pojo.Employee;
import utility.APITestFactory;

public class API_Test {
	
	private RequestSpecBuilder requestSpecBuilder = null;
	private RequestSpecification requestSpecification = null;
	private ResponseSpecBuilder responseSpecBuilder = null;
	private ResponseSpecification responseSpecification = null;
	private Map<String,String> headers = new HashMap<String,String>();
	APITestFactory tf = new APITestFactory();
	private Map<String,String> getData = new HashMap<String,String>();
	
	
	@BeforeClass
	public void configuration(){
		requestSpecBuilder = new RequestSpecBuilder();
		responseSpecBuilder = new ResponseSpecBuilder();
		requestSpecBuilder.setBaseUri("http://dummy.restapiexample.com");
		headers.put("Content-Type", "application/json");
		if(headers.size()>0){
			requestSpecBuilder.addHeaders(headers);
		}
		requestSpecification = requestSpecBuilder.build();
		responseSpecification= responseSpecBuilder.expectStatusCode(200).expectContentType(ContentType.JSON).build();
	}
	
	@Test(dataProvider = "addPlaceDetails", priority = 0)
	public void addPlace(String age, String name, String salary){
		AddData obj = new AddData();
		obj.setAge(age);
		obj.setName(name);
		obj.setSalary(salary);
		RequestSpecification res = given().spec(requestSpecification).body(obj);
		
		Response rs = res.when().post("/api/v1/create")
				.then().spec(responseSpecification).extract().response();
		
		getData.put("id", tf.getJSONValue(rs.asString(), "data.id"));
		getData.put("name", name);
		getData.put("cookie", ""+rs.getDetailedCookie("PHPSESSID"));
//		System.out.println("id "+getData.get("id"));
//		System.out.println("cookie "+getData.get("cookie"));

	}
	
	@DataProvider(name = "addPlaceDetails")
	public Object[][] addPlaceDetails(){
		return new Object[][]{{"20","qwerty","15000"}};
	}
	
	@Test(priority = 1)
	public void getDetails(){
		
		Employee em  = given().spec(requestSpecification).cookie("PHPSESSID",getData.get("cookie").split("=")[1].split(";")[0]).expect().defaultParser(Parser.JSON)
				//.when().get("/api/v1/employee/"+getData.get("id")).as(Employee.class);
				.when().get("/api/v1/employee/1").as(Employee.class);
		
		Assert.assertEquals("Tiger Nixon", em.getData().getEmployee_name());
	}
	
	
	@Test(dataProvider = "updateEmpDetails" , priority = 2)
	public void updateDetails(String name, String age){
		AddData obj = new AddData();
		obj.setAge(age);
		obj.setName(name);
		RequestSpecification res = given().spec(requestSpecification).body(obj).cookie("PHPSESSID",getData.get("cookie").split("=")[1].split(";")[0]);
		
		Response rs = res.when().put("/api/v1/update/"+getData.get("id"))
				.then().spec(responseSpecification).extract().response();
		
		System.out.println(rs.asString());
	}
	
	@DataProvider(name = "updateEmpDetails")
	public Object[][] updateEmpDetails(){
		return new Object[][]{{"qwerty","22"}};
	}
	
 
	

  
}
