package com.autodns.gateway;

import helpers.MyDataProvider;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class APITest {

    @Test(
            description = "Убеждаемся, что что количество тегов равно 15",
            dataProvider = "countTags",
            dataProviderClass = MyDataProvider.class
    )
    public void checkCountTags(String url, int expectedStatusCode, int expectedCountTags) {
        Response response = given()
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();

        int actualCountTags = response.htmlPath().getList("**.findAll { it.name() != null }").size();

        assertEquals(actualCountTags, expectedCountTags,
                "Ожидаемое количество тегов: %s. Действительное количество тегов: %s"
                        .formatted(expectedCountTags, actualCountTags));

    }
}
