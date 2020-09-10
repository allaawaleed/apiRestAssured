package test;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Data.RequriedData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class QuickExecution {

	final String url = "https://api.github.com";
	final String token = RequriedData.getProperty("token");
	RequriedData json = new RequriedData();
	String gistID = "25c4b233f375dae92e9997bc7a92b542";
	Response verifyResponse;

	@BeforeSuite
	public void setUp() {

		RestAssured.baseURI = url;

	}

	@Test(priority = 1, description = "Get gist without token")
	public void getGist_withoutAuth() {

		verifyResponse = RestAssured.given().when().get("/gists");
		Assert.assertEquals(verifyResponse.getStatusCode(), 200);
	}

//https://developer.github.com/v3/gists/#get-a-gist
	@Test(priority = 2, description = "Get gist ID without token")
	public void getGistID_withoutAuth() {

		verifyResponse = RestAssured.given().when().get("/gists/" + gistID);// .then().assertThat()
		// .statusCode(200).extract().response();
		// .then().assertThat().rootPath("25c4b233f375dae92e9997bc7a92b542");
		Assert.assertEquals(verifyResponse.path("id"), gistID);
	}

	@Test(priority = 3, description = "Create new gist with valid token")
	public void postGist_withAuth() {
		verifyResponse = RestAssured.given().auth().oauth2(token).baseUri("https://api.github.com/gists/")
				.contentType(ContentType.JSON).header("Accept", "application/vnd.github.v3+json")
				.body(json.setCreateGistBody()).log().all().when().post();
		int statusCode = verifyResponse.getStatusCode();
		System.out.println(verifyResponse.asString());
		if (statusCode == 200) {
			Assert.assertEquals(statusCode, 200);
		} else {
			Assert.assertEquals(statusCode, 404);
		}

	}

	@Test(priority = 4, description = "Write comment gist with valid token")
	public void postComment_withAuth() {
		verifyResponse = RestAssured.given().auth().oauth2(token)
				.baseUri("https://api.github.com/gists/" + gistID + "/comments").contentType(ContentType.JSON)
				.header("Accept", "application/vnd.github.v3+json").body("hellooo").log().all().when().post();
		int statusCode = verifyResponse.getStatusCode();
		System.out.println(verifyResponse.asString());
		if (statusCode == 200) {
			Assert.assertEquals(statusCode, 200);
		} else {
			Assert.assertEquals(statusCode, 400);
		}

	}

}
