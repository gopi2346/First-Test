package com.org.RestAssured;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class APITesting {
	
	public static void main(String[] args) {
			
			String name = "Gopi";
			String id = "";
		
			RestAssured.baseURI = "https://rahulshettyacademy.com";
			
			String response = RestAssured.given().body(RequestData.createPlace("Gopi", "1000, Tamil Nadu, India"))
			.queryParam("key", "qaclick123")
			.when().post("/maps/api/place/add/json")
			.then().log().all().statusCode(200).extract().response().asString();
			
			JsonPath js = new JsonPath(response);
			id = js.get("place_id");
			System.out.println("Place Id is =" +id);
			
			
			String getResponse = RestAssured.given().queryParam("key", "qaclick123")
			.queryParam("place_id", id)
			.log().all()
			.when().get("/maps/api/place/add/json")
			.then().log().all().statusCode(200).extract().response().asString();
			
			JsonPath js1 = new JsonPath(getResponse);
			String n = js1.get("name");
			SoftAssert sf = new SoftAssert();
			sf.assertEquals(name, n);
			sf.assertAll();
			
			

	}
}
