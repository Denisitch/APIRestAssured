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

/**
 * @author Осюшкин Денис
 * Класс API тестов для проверки результатов запросов на сайте reqres.in
 */
public class APITests extends BaseTest {

    /**
     * @param pathSecondPage относительный путь
     * @param expectedStatusCode ожидаемый код состояния
     * @author Осюшкин Денис
     * Тест для проверки уникальности имен аватаров пользователей
     */
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

    /**
     * @param email электронная почта
     * @param password пароль
     * @param pathRegister относительный путь
     * @param expectedStatusCode ожидаемый код состояния
     * @param expectedToken ожидаемый токен
     * @author Осюшкин Денис
     * Тест для проверки успешной авторизации
     */
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

    /**
     * @param email электронная почта
     * @param pathRegister относительный путь
     * @param expectedStatusCode ожидаемый код состояния
     * @param expectedMessage ожидаемое сообщение об ошибке
     * @author Осюшкин Денис
     * Тест для проверки неудачной авторизации
     */
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

    /**
     * @param pathListResources относительный путь
     * @param expectedStatusCode ожидаемый код состояния
     * @author Осюшкин Денис
     * Тест для проверки сортировки данных
     */
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
