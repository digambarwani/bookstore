package bookstoreTests;

import io.restassured.RestAssured;
import utils.AuthHelper;
import utils.ConfigReader;

public class BaseTest {
    public static String token;
    public static int createdBookId;

    static {
        RestAssured.baseURI = ConfigReader.getBaseURI();
        token = AuthHelper.getToken();
    }
}
