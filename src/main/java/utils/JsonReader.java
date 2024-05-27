package utils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader {
	
	public static String getTestData(String input) throws Throwable {
        String testdata;
        return testdata = (String) getJsonData().get(input);//input is the key
       
    }
	
	public static JSONObject getJsonData() throws IOException, ParseException, Throwable  {
	       
        //pass the path of the testdata.json file
        File filename = new File("D:\\QEdge\\selenium_java\\RESTAPI\\src\\test\\resources\\TestData\\testdata.json");
        //convert json file into string
        String json = FileUtils.readFileToString(filename, "UTF-8");
        //parse the string into object
        Object obj = new JSONParser().parse(json);
        //give jsonobject o that I can return it to the function everytime it get called
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;

    }
	
	public static JSONArray getJsonArray(String key) throws IOException, ParseException, Throwable {
		JSONObject jsonObject =getJsonData();
				JSONArray jsonArray = (JSONArray) jsonObject.get(key);
				return jsonArray;	
	}
	
	public static Object getJsonArrayData(String key,int index) throws IOException, ParseException, Throwable
	{
		JSONArray languages = getJsonArray(key);
		return languages.get(index);
	}
	
	

}
