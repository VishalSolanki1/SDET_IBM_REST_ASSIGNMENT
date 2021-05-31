package com.ibm.sdet.restassured.httpmethods;

import static io.restassured.RestAssured.given;
import org.junit.Assert;
import org.testng.annotations.Test;
import com.ibm.sdet.restassured.payloads.PetApiPayLoad;
import com.ibm.sdet.restassured.pojos.PetPojo;
import com.ibm.sdet.restassured.resources.URI;
import com.ibm.sdet.restassured.specifications.Specification;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;

public class TestPetAPI extends Specification{

	Long petId;
	String responseString;
	PetApiPayLoad petApiPayLoad = new PetApiPayLoad();

	@Test(priority = 1, enabled = true, dataProvider = "PetsAPIPayLoad")
	public void addNewPet(String categoryName, 
			String categoryId, String tagsName, String tagsId, String photoUrlName, String name,
		    String status) {
		
		Response response =
				given().spec(request).body(petApiPayLoad.getPetApiPayLoad(categoryName, 
						categoryId, tagsName, tagsId, photoUrlName, name, status)).
				when().post(URI.ADD).
				then().assertThat()
				.statusCode(200)
				.body("name", equalTo("MyPetDogs"))
				.body("status", equalTo("available"))
		        .extract().response();
		
		responseString = response.asString();
		System.out.println(response.asPrettyString());

		
		Assert.assertTrue(response.getStatusCode() == 200);
		System.out.println("PASSED: Below Pet has been successfully added");
	}

	@Test(priority = 2, enabled = true)
	public void fetchPet() {

		petId = PetApiPayLoad.getPetId(responseString);
        Response response =
				given().spec(request).
				when().get(URI.FETCH + petId).
				then().extract().response();
        
        System.out.println(response.getStatusCode());
		Assert.assertTrue(response.getStatusCode() == 200);
		System.out.println("PASSED: Verified that the Pet exists");	
	}	
}
