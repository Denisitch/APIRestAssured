package in.reqres;

import data.*;
import helpers.MyDataProvider;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class APITests {

    @Test(
            description = "Получаем список пользователей со второй страницы и убеждаемся, " +
                    "что имена файлов аватаров пользователей уникальны",
            dataProvider = "listUserSecondPage",
            dataProviderClass = MyDataProvider.class
    )
    public void checkUserAvatarFileNamesAreUnique(String urlSecondPage) {
        Resourse resourse = given()
                .when()
                .get(urlSecondPage)
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().as(Resourse.class);
        List<DataUser> currentData = resourse.getData();
        List<DataUser> uniqueData = resourse.getData().stream().distinct().toList();

        assertEquals(currentData.size(), uniqueData.size(),
                "Имена файлов аватаров пользователей не уникальны");  // TODO переопределить assert
    }

    @Test(
            description = "Проводим успешную авторизацию пользователя в системе",
            dataProvider = "successfulAuth",
            dataProviderClass = MyDataProvider.class
    )
    public void checkSuccessfulAuth(String email, String password, String urlRegister) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(Auth.builder().email(email).password(password).build())
                .when()
                .post(urlRegister)
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        int actualStatusCode = response.getStatusCode();
        int expectedStatusCode = 200;

        String actualToken = response.as(ResponseAuth.class).getToken();
        String expectedToken = "QpwL5tke4Pnpja7X4";

        assertEquals(actualStatusCode, expectedStatusCode,
                "Ожидаемый код состояния: %s. Действительный код состояния: %s"
                        .formatted(actualStatusCode, expectedStatusCode));

        assertEquals(actualToken, expectedToken,
                "Ожидаемый токен %s не совпадает с действительным токеном %s"
                        .formatted(actualToken, expectedToken));
    }

    @Test(
            description = "Проводим неудачную авторизацию пользователя в системе",
            dataProvider = "errorAuth",
            dataProviderClass = MyDataProvider.class
    )
    public void checkErrorAuth(String email, String urlRegister) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(Auth.builder().email(email).build())
                .when()
                .post(urlRegister)
                .then()
                .log().all()
                .statusCode(400)
                .extract().response();

        int actualStatusCode = response.getStatusCode();
        int expectedStatusCode = 400;

        String actualMessage = response.as(ResponseError.class).getError();
        String expectedMessage = "Missing password";

        assertEquals(actualStatusCode, expectedStatusCode,
                "Ожидаемый код состояния: %s. Действительный код состояния: %s"
                        .formatted(actualStatusCode, expectedStatusCode));

        assertEquals(actualMessage, expectedMessage,
                "Ожидаемое сообщение об ошибке %s не совпадает с действительным сообщением об ошибке %s"
                        .formatted(actualMessage, expectedMessage));
    }
}
