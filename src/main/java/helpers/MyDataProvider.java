package helpers;

import org.testng.annotations.DataProvider;

public class MyDataProvider {

    @DataProvider(name = "listUserSecondPage")
    public Object[][] listUserSecondPage() {
        return new Object[][]{{"/users?page=2", 200}};
    }

    @DataProvider(name = "successfulAuth")
    public Object[][] authSuccessfulData() {
        return new Object[][]{{"eve.holt@reqres.in", "cityslicka", "/register", 200, "QpwL5tke4Pnpja7X4"}};
    }

    @DataProvider(name = "errorAuth")
    public Object[][] authErrorData() {
        return new Object[][]{{"sydney@fife", "/register", 400, "Missing password"}};
    }

    @DataProvider(name = "listResources")
    public Object[][] listResources() {
        return new Object[][]{{"/unknown", 200}};
    }
}
