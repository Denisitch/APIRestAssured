package in.reqres;

import data.*;
import helpers.MyDataProvider;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.util.Comparator.*;
import static org.testng.Assert.assertEquals;
import static specification.Specification.*;

public class APITests extends BaseTest {

    @Test(
            description = "Получаем список пользователей со второй страницы и убеждаемся, " +
                    "что имена файлов аватаров пользователей уникальны",
            dataProvider = "listUserSecondPage",
            dataProviderClass = MyDataProvider.class
    )
    public void checkUserAvatarFileNamesAreUnique(String pathSecondPage, int expectedStatusCode) {
        installSpec(responseSpecification(expectedStatusCode));

        ResourceUsers resource =
                given()
                .when()
                        .get(pathSecondPage)
                .then()
                        .extract().body().as(ResourceUsers.class);
        List<DataUser> currentData = resource.getData();
        List<DataUser> uniqueData = resource.getData().stream()
                .distinct()
                .toList();

        assertEquals(currentData.size(), uniqueData.size(),
                "Имена файлов аватаров пользователей не уникальны");

        deleteSpec();
    }

    @Test(
            description = "Проводим успешную авторизацию пользователя в системе",
            dataProvider = "successfulAuth",
            dataProviderClass = MyDataProvider.class
    )
    public void checkSuccessfulAuth(String email, String password, String pathRegister,
                                    int expectedStatusCode, String expectedToken) {
        installSpec(requestSpecification(), responseSpecification(expectedStatusCode));

        Response response =
                given()
                        .body(Auth.builder().email(email).password(password).build())
                .when()
                        .post(pathRegister)
                .then()
                        .extract().response();

        int actualStatusCode = response.getStatusCode();
        String actualToken = response.as(ResponseAuth.class).getToken();

        assertEquals(actualStatusCode, expectedStatusCode,
                "Ожидаемый код состояния: %s. Действительный код состояния: %s"
                        .formatted(actualStatusCode, expectedStatusCode));

        assertEquals(actualToken, expectedToken,
                "Ожидаемый токен %s не совпадает с действительным токеном %s"
                        .formatted(actualToken, expectedToken));

        deleteSpec();
    }

    @Test(
            description = "Проводим неудачную авторизацию пользователя в системе",
            dataProvider = "errorAuth",
            dataProviderClass = MyDataProvider.class
    )
    public void checkErrorAuth(String email, String pathRegister,
                               int expectedStatusCode, String expectedMessage) {
        installSpec(requestSpecification(), responseSpecification(expectedStatusCode));

        Response response =
                given()
                        .body(Auth.builder().email(email).build())
                .when()
                        .post(pathRegister)
                .then()
                        .extract().response();

        int actualStatusCode = response.getStatusCode();
        String actualMessage = response.as(ResponseError.class).getError();

        assertEquals(actualStatusCode, expectedStatusCode,
                "Ожидаемый код состояния: %s. Действительный код состояния: %s"
                        .formatted(actualStatusCode, expectedStatusCode));

        assertEquals(actualMessage, expectedMessage,
                "Ожидаемое сообщение об ошибке %s не совпадает с действительным сообщением об ошибке %s"
                        .formatted(actualMessage, expectedMessage));

        deleteSpec();
    }

    @Test(
            description = "Получаем список ресурсов и убеждаемся, что возвращаемые данные отсортированы по годам",
            dataProvider = "listResources",
            dataProviderClass = MyDataProvider.class
    )
    public void checkDataSortedByYears(String pathListResources, int expectedStatusCode) {
        installSpec(responseSpecification(expectedStatusCode));

        ResourceColor resourceColor =
                given()
                .when()
                        .get(pathListResources)
                .then()
                        .extract().body().as(ResourceColor.class);

        List<DataColor> actualDataColor = resourceColor.getData();
        List<DataColor> sortedDataColor = resourceColor.getData().stream()
                .sorted(comparing(DataColor::getYear)).toList();

        assertEquals(actualDataColor, sortedDataColor,
                "Возвращаемые данные не отсортированы по годам");

        deleteSpec();
    }
}
