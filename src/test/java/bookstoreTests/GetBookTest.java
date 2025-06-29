package bookstoreTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class GetBookTest extends BaseTest {

    @Test(dependsOnMethods = "bookstoreTests.CreateBookTest.createNewBook_ShouldReturn200")
    public void getBook_ShouldReturnCorrectBook() {
        Response response = RestAssured
            .given()
                .header("Authorization", "Bearer " + token)
            .when()
                .get("/books/" + createdBookId)
            .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), createdBookId);
        System.out.println("Get test completed");
    }
    
    @Test
    public void getBook_NonExistingId_ShouldReturn404() {
        given()
            .header("Authorization", "Bearer " + token)
        .when()
            .get("/books/99999")
        .then()
            .statusCode(404);
    }
}
