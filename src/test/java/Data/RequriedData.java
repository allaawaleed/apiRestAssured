package Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class RequriedData {

	// Read token and data from properties
	public static String getProperty(String s) {
		Properties prop = new Properties();
		try {

			prop.load(new InputStreamReader(
					new FileInputStream(System.getProperty("user.dir") + "/src/test/java/Data/config.properties"),
					"UTF-8"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop.getProperty("token");
	}

	/**
	 * Creating JSON MAP to add JSON objects
	 * https://developer.github.com/v3/gists/#create-a-gist
	 **/

	public Map<String, Object> setCreateGistBody() {
		Map<String, Object> files = new HashMap<String, Object>();
		Map<String, String> content = new HashMap<>();
		content.put("content", "First sample as quick trial in the txt file");
		files.put("HelloJava.txt", content);

		Map<String, Object> jsonBodyUsingMap = new HashMap<String, Object>();
		jsonBodyUsingMap.put("description", "Test api usign reset assured");
		jsonBodyUsingMap.put("public", true);
		jsonBodyUsingMap.put("files", files);
		return jsonBodyUsingMap;

	}

}
