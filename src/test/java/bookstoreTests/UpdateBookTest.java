package bookstoreTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class UpdateBookTest extends BaseTest {

    @Test(dependsOnMethods = "bookstoreTests.GetBookTest.getBook_ShouldReturnCorrectBook")
    public void updateBook_ShouldReturnUpdatedBook() {
        String updateBody = """
            {
              "name": "Updated Book Title",
              "author": "Updated Author",
              "published_year": 2025,
              "book_summary": "Updated summary."
            }
        """;

        Response response = RestAssured
            .given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(updateBody)
            .when()
                .put("/books/" + createdBookId)
            .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "Updated Book Title");
        System.out.println("Update test completed");
    }
    
    @Test
    public void updateBook_InvalidId_ShouldReturn404() {
        String body = """
            {
              "name": "Doesn't Matter",
              "author": "Nobody",
              "published_year": 2025,
              "book_summary": "Should fail."
            }
        """;

        given()
            .header("Authorization", "Bearer " + token)
            .header("Content-Type", "application/json")
            .body(body)
        .when()
            .put("/books/99999")
        .then()
            .statusCode(404);
    }

}
