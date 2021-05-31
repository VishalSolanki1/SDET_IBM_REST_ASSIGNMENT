package com.ibm.sdet.restassured.specifications;

import static io.restassured.RestAssured.given;

import com.ibm.sdet.restassured.payloads.PetApiPayLoad;
import com.ibm.sdet.restassured.resources.URI;
import io.restassured.specification.RequestSpecification;

public class Specification extends PetApiPayLoad{

	public RequestSpecification request = given().baseUri(URI.PET_URI).contentType("application/json");
	public RequestSpecification soapRequest = given().baseUri(URI.CALC_URI).contentType("text/xml");

}
