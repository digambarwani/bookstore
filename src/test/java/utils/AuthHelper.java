package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AuthHelper {

    public static String getToken() {
        RestAssured.baseURI = "http://127.0.0.1:8000";

        long timestamp = System.currentTimeMillis();
        String email = "testuser" + timestamp + "@example.com";
        String password = "Password123";

        String signupBody = """
            {
              "email": "%s",
              "password": "%s"
            }
        """.formatted(email, password);

        Response signupResponse = RestAssured
            .given()
                .header("Content-Type", "application/json")
                .body(signupBody)
            .when()
                .post("/signup");

        if (signupResponse.getStatusCode() != 200) {
            throw new RuntimeException("Signup failed! Status: " + signupResponse.getStatusCode());
        }

        String loginBody = """
            {
              "email": "%s",
              "password": "%s"
            }
        """.formatted(email, password);

        Response loginResponse = RestAssured
            .given()
                .header("Content-Type", "application/json")
                .body(loginBody)
            .when()
                .post("/login");

        if (loginResponse.getStatusCode() != 200) {
            throw new RuntimeException("Login failed! Status: " + loginResponse.getStatusCode());
        }

        return loginResponse.jsonPath().getString("access_token");
    }
}
