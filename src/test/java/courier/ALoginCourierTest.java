package courier;

import basic.BasicUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static constant.AdressAndPens.loginCourier;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

    public class ALoginCourierTest extends BasicUrl {

        public static String idCourier;

        public static String getIdCourier() {
            return idCourier;
        }

        // Тест проверяет 1.Курьер может авторизоваться, 2. для авторизации нужно передать все обязательные поля, 3. успешный запрос возвращает id.
        @Test
        public void CourierCanLogIn(){
            File json = new File("src/main/resources/CourierCanLogOn.json");
            Response response =
                    given()
                            .header("Content-type", "application/json")
                            .body(json)
                            .when()
                            .post(loginCourier);
            response.then().assertThat().body("id", notNullValue())
                    .statusCode(200);
            idCourier = response.body().asString().replace("\"id\":","").replaceAll("[{}]","");
        }

        // Тест проверяет 1. система вернёт ошибку, если неправильно указать логин или пароль, 2. если авторизоваться под несуществующим пользователем, запрос возвращает ошибку
        @Test
        public void RespMistakeLoginWrong(){
            File json = new File("src/main/resources/wrongLogin.json");
            Response response =
                    given()
                            .header("Content-type", "application/json")
                            .body(json)
                            .when()
                            .post(loginCourier);
            response.then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                    .statusCode(404);
        }
        // Тест проверяет 1. если какого-то поля нет, запрос возвращает ошибку
        @Test
        public void RespMistakeWithoutLogin(){
            File json = new File("src/main/resources/logInWithoutLogin.json");
            Response response =
                    given()
                            .header("Content-type", "application/json")
                            .body(json)
                            .when()
                            .post(loginCourier);
            response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                    .statusCode(400);
        }
    }
