package restassured;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TestRestAssured {

    private static RequestSpecification requestSpecification;
    private static ResponseSpecification responseSpecification;

    @BeforeAll
    static void setUp(){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
        requestSpecification= new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("X-Custom-Header", "RestAssuredDemo")
            .build();

        responseSpecification= new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(5000L))
            .build();
    }


    @AfterAll
    static void tearDown(){
        RestAssured.reset();
    }

    @Test
    public void firstRequestDemo(){
        given()
            //.baseUri("https://jsonplaceholder.typicode.com/") <-- another way to add base ID
            .log().all()
        .when()
            .get("/posts/1")
        .then()
            .log().all()
            .statusCode(200);
    }

    @Test
    public void secondTest(){
        given()
            //.baseUri("https://jsonplaceholder.typicode.com/") <-- another way to add base ID
            .log().parameters()
            .queryParam("userId", 1)
        .when()
            .get("/posts")
        .then()
            .log().body()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("size()", greaterThan(0));
    }

    @Test
    public void testUsers(){
        given()
            //.baseUri("https://jsonplaceholder.typicode.com/") <-- another way to add base ID
            .log().all()
        .when()
            .get("/users/1")
        .then()
            .log().body()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("name", equalTo("Leanne Graham"))
            .body("address.geo.lat", notNullValue());
    }

    @Test
    public void getPost(){
        given()
            .spec(requestSpecification)
            .log().all()
        .when()
            .get("/posts/1")
        .then()
            .spec(responseSpecification)
            .log().all();
    }
}
