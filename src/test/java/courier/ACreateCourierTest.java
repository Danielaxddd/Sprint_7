package courier;

import basic.BasicUrl;
import constant.CreateCourier;
import org.junit.Test;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import static constant.AdressAndPens.CREATE_COURIER;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;



public class ACreateCourierTest extends BasicUrl {

    @Test
    @DisplayName("Успешное создание курьера")
    @Description("Post-запрос /api/v1/courier")
    public void aCreateNewCourierTest(){
        CreateCourier createCourier = new CreateCourier("lalalalaal", "1234", "dana");
        BasicPostApi(createCourier,CREATE_COURIER).then().assertThat().statusCode(SC_CREATED)
                .body("ok", equalTo(true));
    }

    @DisplayName("Создание курьера без необязательного поля(firstName)")
    @Description("Post-запрос /api/v1/courier")
    @Test
    public void courierCreateWithoutNameTest(){
        CreateCourier createCourier = new CreateCourier("lalalalaall", "1234", "");
        BasicPostApi(createCourier,CREATE_COURIER).then().assertThat().statusCode(SC_CREATED)
                .body("ok", equalTo(true));
    }

    @DisplayName("Создание курьера без обязательного поля(login)")
    @Description("Post-запрос /api/v1/courier")
    @Test
    public void courierCreateWithoutLoginTest(){
        CreateCourier createCourier = new CreateCourier("",  "1234", "dana");
        BasicPostApi(createCourier,CREATE_COURIER).then().assertThat().statusCode(SC_BAD_REQUEST)
        .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
    @DisplayName("Создание курьера без обязательного поля(password)")
    @Description("Post-запрос /api/v1/courier")
    @Test
    public void courierCreateWithoutPassTest(){
        CreateCourier createCourier = new CreateCourier("lalalalaal",  "", "dana");
        BasicPostApi(createCourier,CREATE_COURIER).then().assertThat().statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
    @DisplayName("Создание курьера с логином который уже используется")
    @Description("Post-запрос /api/v1/courier")
    @Test
    public void createSecondCourierLoginTest(){
        CreateCourier createCourier = new CreateCourier("lalalalaal",  "54321", "dana");
        BasicPostApi(createCourier,CREATE_COURIER).then().assertThat().statusCode(SC_CONFLICT)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }
}

