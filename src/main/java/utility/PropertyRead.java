package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class PropertyRead {
	
	public String getPropertyData(String Property) throws IOException{
		Properties obj = new Properties(); 
		FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\data.properties");
		obj.load(objfile);
		String Value = obj.getProperty(Property);
		return Value;
		
	}

}
