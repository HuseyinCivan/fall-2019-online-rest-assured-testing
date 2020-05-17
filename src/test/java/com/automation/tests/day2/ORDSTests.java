package com.automation.tests.day2;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class ORDSTests {
    String BASE_URL = "http://3.90.112.152:1000/ords/hr";


    @Test
    @DisplayName("Get list of all employees")
    public void getAllEmployees() {
        //response can be savesd in Response object
        //prettyPeek() - method that prints response info in nice format
        //also the method returns Response object
        //response contains body, headers and status line
        //body (payload) - contains content taht we request from the web service
        //header - contains meta data
        Response response = given().
                baseUri(BASE_URL).
                when().
                get("/employees").prettyPeek();

        /**
         * RestAssured request has similar structure to BDD scenario
         * Start biulding the request aprt of the test
         *
         * give() - used for request setup and authentication
         * Syntactic sugar,
         * when() - to specify type of HTTP request: gwt, puy, post , delete , patch ,head
         * then() - to verify response, performance assertions
         */
    }

    @Test
    @DisplayName("Get Employee under specific ID")

    public void getOneEmployee() {
        // in URL we can specify path and querry parameters
        //path parameters are used to retrieve specific resounrces: for example one employee not all of them
        //{id} - path variable ,that will be replace with a value aftre comma
        //after when() we specify HTTP request type/method/verb

        Response response = given().
                baseUri(BASE_URL).
                when().get("/employees/{id}", 100).prettyPeek();

        //how we verify response ? use assertions

        response.then().statusCode(200); // to verify that status os 200

        int statusCOde = response.statusCode(); // to save status code in varible

        Assertions.assertEquals(200,statusCOde);
        //if sassertion fails , tou will get this kind of message:

//        java.lang.AssertionError: 1 expectation failed.
//        Expected status code <2010> but was <200>.

        //200 is always expected result aftre GET request


        //hamcrast verify body




    }
    /**
     * given base URI = http://3.90.112.152:1000/ords/hr
     * when user sends get request to "/countries"
     * then user verifies that status code is 200
     */

    @Test
    @DisplayName("Get list of all countries and verify that status code is 200")
    public void getAllCountries(){

                given().
                    baseUri(BASE_URL).
                when().
                    get("/countries").prettyPeek().
                then().
                    statusCode(200).statusLine("HTTP/1.1 200 OK");

                //statusLine is to verify status line

    }


}
