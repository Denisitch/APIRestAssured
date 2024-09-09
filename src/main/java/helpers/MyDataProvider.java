package helpers;

import org.testng.annotations.DataProvider;

public class MyDataProvider {

    @DataProvider(name = "listUserSecondPage")
    public Object[][] listUserSecondPage() {
        return new Object[][]{{"https://reqres.in/api/users?page=2"}};
    }

    @DataProvider(name = "successfulAuth")
    public Object[][] authSuccessfulData() {
        return new Object[][]{{"eve.holt@reqres.in", "cityslicka", "https://reqres.in/api/register"}};
    }

    @DataProvider(name = "errorAuth")
    public Object[][] authErrorData() {
        return new Object[][]{{"sydney@fife", "https://reqres.in/api/register"}};
    }
}
