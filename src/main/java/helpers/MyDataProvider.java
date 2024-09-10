package helpers;

import org.testng.annotations.DataProvider;

/**
 * @author Осюшкин Денис
 * Класс для параметризации тестовых даных
 */
public class MyDataProvider {

    /**
     * @return массив параметров
     * @author Осюшкин Денис
     * Метод, предоставляющий данные для теста на проверку уникальности имен аватаров пользователей
     */
    @DataProvider(name = "listUserSecondPage")
    public Object[][] listUserSecondPage() {
        return new Object[][]{{"/users?page=2", 200}};
    }

    /**
     * @return массив параметров
     * @author Осюшкин Денис
     * Метод, предоставляющий данные для теста на проверку успешной авторизации
     */
    @DataProvider(name = "successfulAuth")
    public Object[][] authSuccessfulData() {
        return new Object[][]{{"eve.holt@reqres.in", "cityslicka", "/register", 200, "QpwL5tke4Pnpja7X4"}};
    }

    /**
     * @return массив параметров
     * @author Осюшкин Денис
     * Метод, предоставляющий данные для теста на проверку неудачной авторизации
     */
    @DataProvider(name = "errorAuth")
    public Object[][] authErrorData() {
        return new Object[][]{{"sydney@fife", "/register", 400, "Missing password"}};
    }

    /**
     * @return массив параметров
     * @author Осюшкин Денис
     * Метод, предоставляющий данные для теста на проверку сортировки данных
     */
    @DataProvider(name = "listResources")
    public Object[][] listResources() {
        return new Object[][]{{"/unknown", 200}};
    }

    /**
     * @return массив параметров
     * @author Осюшкин Денис
     * Метод, предоставляющий данные для теста на проверку количества тегов в XML
     */
    @DataProvider(name = "countTags")
    public Object[][] countTags() {
        return new Object[][]{{"https://gateway.autodns.com/", 200, 15}};
    }
}
