package in.reqres;

import data.DataUser;
import data.Resourse;
import helpers.MyDataProvider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class APITests {

    @Test(
            description = " Получаем список пользователей со второй страницы и убеждаемся, " +
                    "что имена файлов аватаров пользователей уникальны",
            dataProvider = "listUserSecondPage",
            dataProviderClass = MyDataProvider.class
    )
    public void checkUserAvatarFileNamesAreUnique(String listUserSecondPage) {
        Resourse resourse = given()
                .when()
                .get(listUserSecondPage)
                .then()
                .log().all()
                .extract().body().as(Resourse.class);
        List<DataUser> currentData = resourse.getData();
        List<DataUser> uniqueData = resourse.getData().stream().distinct().toList();

        Assert.assertEquals(currentData.size(), uniqueData.size(),
                "Имена файлов аватаров пользователей не уникальны");
    }
}
