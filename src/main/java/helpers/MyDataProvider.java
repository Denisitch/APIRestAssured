package helpers;

import org.testng.annotations.DataProvider;

public class MyDataProvider {

    @DataProvider(name = "listUserSecondPage")
    public Object[] listUserSecondPage() {
        return new Object[]{"https://reqres.in/api/users?page=2"};
    }
}
