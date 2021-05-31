package com.ibm.sdet.restassured.httpmethods;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import com.ibm.sdet.restassured.resources.URI;
import com.ibm.sdet.restassured.specifications.Specification;
import static io.restassured.RestAssured.*;

public class SoapRequestByRest extends Specification {

	
	@Test
	public void testAdd() throws IOException {
		   
		String response = 
		 given()
	          .spec(soapRequest).body(IOUtils.toString(new FileInputStream(URI.REQUEST_XML_PATH), "UTF-8")).
	     when()
	          .post("/calculator.asmx").
	     then().assertThat().statusCode(200).extract().asPrettyString();
	     
	     System.out.println("Response : " + response);
	          
	   
	}
}
