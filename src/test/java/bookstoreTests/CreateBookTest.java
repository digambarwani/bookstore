package bookstoreTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateBookTest extends BaseTest {

    @Test
    public void createNewBook_ShouldReturn200() {
        String requestBody = """
            {
              "name": "Learning API Automation",
              "author": "Digambar",
              "published_year": 2024,
              "book_summary": "Book created for chain test."
            }
        """;

        Response response = RestAssured
            .given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
            .when()
                .post("/books/")
            .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);

        String name = response.jsonPath().getString("name");
        Assert.assertEquals(name, "Learning API Automation");

        int id = response.jsonPath().getInt("id");
        createdBookId = id;

        System.out.println("Created book ID: " + createdBookId);
        System.out.println("Create test completed");
    }
    
    @Test
    public void createBook_WithoutAuth_ShouldReturn403() {
        String bodyForNoAuth = """
            {
              "name": "Bad Book",
              "author": "No Auth",
              "published_year": 2025,
              "book_summary": "Should fail because no token."
            }
        """;

        given()
            .header("Content-Type", "application/json")
            .body(bodyForNoAuth)
        .when()
            .post("/books/")
        .then()
            .statusCode(403);
    }
    
    @Test
    public void createBook_MissingFields_ShouldReturn422() {
        String body = """
            {
              "name": "Missing Author"
            }
        """;

        given()
            .header("Authorization", "Bearer " + token)
            .header("Content-Type", "application/json")
            .body(body)
        .when()
            .post("/books/")
        .then()
            .statusCode(422); /*Bug: Actual Status code should be 422, missing fields in request. 
        						Received 500 as status code. This should not showcase as server error.*/
    }
}
