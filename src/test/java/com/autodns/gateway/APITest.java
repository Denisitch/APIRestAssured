package com.autodns.gateway;

import helpers.MyDataProvider;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
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
                .contentType(ContentType.XML)
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();

        Document parse = Jsoup.parse(response.asString(), "", Parser.xmlParser());
        int actualCountTags = parse.select("**").size();

        assertEquals(actualCountTags, expectedCountTags,
                "Ожидаемое количество тегов: %s. Действительное количество тегов: %s"
                        .formatted(expectedCountTags, actualCountTags));

    }
}
