package in.reqres;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

/**
 * @author Осюшкин Денис
 * Базовый класс для тестов
 */
public class BaseTest {

    /**
     * @author Осюшкин Денис
     * Метод, выполняющийся перед началом каждого теста
     */
    @BeforeSuite
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }
}
