package courier;

import basic.BasicUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.io.File;

import static constant.AdressAndPens.createCourier;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class ACreateCourierTest extends BasicUrl {
    //Тест проверяет: 1. Курьера можно создать, 2. Чтобы создать курьера, нужно передать все обяз. поля, 3. Успешный запрос возвращает ok: true.
    @Test
    public void ACreateNewCourierCheck(){
        File json = new File("src/main/resources/acreateCourier.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(json)
                        .when()
                        .post(createCourier);
        response.then().assertThat().body("ok", equalTo(true))
                .statusCode(201);
    }
    // Тест проверяет если нет одного из полей, запрос возращает ошибку, правильный код ответа
    @Test
    public void CourierMistakeWithoutFieldCheck(){
        File json = new File("src/main/resources/notOneRequiredField.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(json)
                        .when()
                        .post(createCourier);
        response.then().assertThat().statusCode(400);
    }
}
