package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {

	public static RequestSpecification request;
	public static ResponseSpecification response;
	public static Properties prop;
	public static PrintStream log;
	public static FileInputStream propFile;

	public RequestSpecification requestSpecification() throws IOException {

		if (request == null) {
			log = new PrintStream(new FileOutputStream("logFile.txt"));
			request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI")).setContentType(ContentType.JSON)
					.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
			return request;
		}
		return request;

	}

	public ResponseSpecification responseSpecification() {
		ResponseSpecification response = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		return response;

	}

	public static String getGlobalValue(String key) throws IOException {
		propFile = new FileInputStream(
				new File(System.getProperty("user.dir") + "\\src\\test\\java\\resources\\global.properties"));
		prop = new Properties();
		prop.load(propFile);
		return prop.getProperty(key);
	}

}
