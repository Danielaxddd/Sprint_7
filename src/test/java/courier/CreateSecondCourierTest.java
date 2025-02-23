package courier;

import basic.BasicUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static constant.AdressAndPens.createCourier;

public class CreateSecondCourierTest extends BasicUrl {
    // Тест проверяет 1. если создать пользователя с логином, который уже есть, возвращается ошибка, 2. правильный код ответа
    @Test
    public void CreateSecondCourierCheck(){
        File json = new File("src/main/resources/acreateCourier.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(json)
                        .when()
                        .post(createCourier);
        response.then().assertThat().statusCode(409);
    }
}
