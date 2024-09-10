package specification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * @author Осюшкин Денис
 * Класс для настройки запросов и ответов для тестов
 */
public class Specification {

    /**
     * @return спецификация запроса
     * @author Осюшкин Денис
     * Метод для создания спецификации запроса
     */
    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    /**
     * @param actualStatusCode ожидаемый код состояния
     * @return спецификация ответа
     * @author Осюшкин Денис
     * Метод для создания спецификации ответа
     */
    public static ResponseSpecification responseSpecification(int actualStatusCode) {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(actualStatusCode)
                .build();
    }

    /**
     * @param requestSpec спецификация запроса
     * @author Осюшкин Денис
     * Метод для установки спецификации запроса
     */
    public static void installSpec(RequestSpecification requestSpec) {
        RestAssured.requestSpecification = requestSpec;
    }

    /**
     * @param responseSpec спецификация ответа
     * @author Осюшкин Денис
     * Метод для установки спецификации ответа
     */
    public static void installSpec(ResponseSpecification responseSpec) {
        RestAssured.responseSpecification = responseSpec;
    }

    /**
     * @param requestSpec спецификация запроса
     * @param responseSpec спецификация ответа
     * @author Осюшкин Денис
     * Метод для установки спецификаций запроса и ответа
     */
    public static void installSpec(RequestSpecification requestSpec, ResponseSpecification responseSpec) {
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    /**
     * @author Осюшкин Денис
     * Метод для удаления спецификаций запроса и ответа
     */
    public static void deleteSpec() {
        RestAssured.requestSpecification = null;
        RestAssured.responseSpecification = null;
    }
}
