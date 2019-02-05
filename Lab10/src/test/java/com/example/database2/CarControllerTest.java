package com.example.database2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarControllerTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.port = 8080;
    }

    @Test
    public void getCarByVin_ExistingVin()  {
        given().port(port).when().request("GET", "/user/car/dfs3423").then().body("model", equalTo("Zonda"));
    }

    @Test
    public void getCarByVin_ExistingVin_ResponseTime() {
        given().port(port).when().request("GET", "/user/car/dfs3423").then().time(lessThan(5000L));
    }

    @Test
    public void getCarByVin_WrongRequestType()  {
        given().port(port).when().request("PATCH", "/user/car/xx").then().statusCode(405);
    }

    @Test
    public void getAllCars() {
        given().port(port).when().request("GET", "/user/cars").then().statusCode(200);
    }

    @Test
    public void addingCarOwner_noParams() {
        given().port(port).when().request("PUT", "/user/carAddOwner").then().statusCode(404);
    }

    @Test
    public void getAllOwners() {
        given().port(port).when().request("GET", "/user/owners").then().statusCode(200);
    }

    @Test
    public void getOwnerByPESEL_ExistingPESEL()  {
        given().port(port).when().request("GET", "/user/owner/510628416").then().body("surname", equalTo("Escobar"));
    }

    @Test
    public void getOwnerByPESEL_NonExistingPESEL()  {
        given().port(port).when().request("GET", "/user/owner/xsx").then().statusCode(404);
    }

    @Test
    public void getOwnerByPESEL_ExistingPESEL_BodyCheck()  {
        given().port(port).when().request("GET", "/user/owner/510628416").then()
                .body("$", hasKey("surname"))
                .body("$", not(hasKey("children")));
    }

    @Test
    public void getOwnerByPESEL_ExistingPESEL_ContentType()  {
        given().port(port).when().request("GET", "/user/owner/510628416").then()
                .contentType(ContentType.JSON);
    }
}