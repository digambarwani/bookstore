package bookstoreTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class DeleteBookTest extends BaseTest {

    @Test(dependsOnMethods = "bookstoreTests.UpdateBookTest.updateBook_ShouldReturnUpdatedBook")
    public void deleteBook_ShouldReturnSuccessMessage() {
        Response response = RestAssured
            .given()
                .header("Authorization", "Bearer " + token)
            .when()
                .delete("/books/" + createdBookId)
            .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.asString().contains("Book deleted"));
        System.out.println("Delete test completed");
    }
    
    @Test
    public void deleteBook_InvalidId_ShouldReturn404() {
        given()
            .header("Authorization", "Bearer " + token)
        .when()
            .delete("/books/99999")
        .then()
            .statusCode(404);
    }

}
