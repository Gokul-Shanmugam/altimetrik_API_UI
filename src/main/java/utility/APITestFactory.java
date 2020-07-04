package utility;

import io.restassured.path.json.JsonPath;


public class APITestFactory {
	

	public String getJSONValue(String res,String jPath){
		JsonPath js = new JsonPath(res);
		return js.getString(jPath);
	}
	
}
