package restassured;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.PostDto;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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

    private int createdPostId;

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

    @Test
    @Order(1)
    @DisplayName("CREATE - POST new post")
    void create_post_returnsCreatedResource() {
         // Request body as JSON string
        PostDto post = new PostDto(null, 1, "Test Post from REST Assured", "demo");
        Response response = given()
                .spec(requestSpecification)
                .body(post)
            .when()
                .post("/posts")
            .then()
                .statusCode(201)  // Created
                .body("title", equalTo("Test Post from REST Assured"))
                .body("body", containsString("demo"))
                .body("userId", equalTo(1))
                .body("id", notNullValue())
                .extract().response();

        // Store ID for later tests
        createdPostId = response.jsonPath().getInt("id");
        System.out.println("Created post with ID: " + createdPostId);
    }

    //put
    @Test
    void TestPut(){
        PostDto postDto = new PostDto(1, 1, "Update", "Updated");
        given()
            .spec(requestSpecification)
            .body(postDto)
            .log().all()
        .when()
            .put("/posts/"+postDto.getId())
        .then()
            .spec(responseSpecification)
            .body("body", equalTo("Updated"))
            .log().all();
    }

    //delete
    @ParameterizedTest
    @CsvSource({
        "1",
        "2",
        "3",
        "4"
    })
    public void deletePostFromValueSource(int id){
        given()
            .spec(requestSpecification)
            .log().all()
        .when()
            .delete("/posts/"+id)
        .then()
            .spec(responseSpecification)
            .log().all();
    }

    //write new class to test the get method for an individual post
    @ParameterizedTest
    @CsvSource({
        "1",
        "2",
        "3",
        "4"
    })
    void getPostFromValueSource(int id){
        given()
            .spec(requestSpecification)
            .log().all()
        .when()
            .get("/posts/"+id)
        .then()
            .spec(responseSpecification)
            .body("id", equalTo(id))
            .log().all();
    }

    
    //make a get calll to users end poiunt and make sure the name matches the fetched name for 5 different users
    @ParameterizedTest(name = "User {0} has name {1}")
    @CsvSource({
            "1, Leanne Graham",
            "2, Ervin Howell",
            "3, Clementine Bauch",
            "4, Patricia Lebsack",
            "5, Chelsey Dietrich"
    })
    void getUsersById(int id, String name){
        given()
            .spec(requestSpecification)
            .log().all()
        .when()
            .get("/users/"+id)
        .then()
            .body("id", equalTo(id))
            .body("name", equalTo(name));
    }

    // @ParameterizedTest(name="{0} /posts/{2} returns {1}")
    // @CsvSource({
    //     "GET, /"
    // })
}
