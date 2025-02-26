package courier;

import basic.BasicUrl;
import constant.CourierCanLogOn;
import org.junit.Test;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import static constant.AdressAndPens.LOGIN_COURIER;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

    public class LoginCourierTest extends BasicUrl {

        public static String idCourier;

        @DisplayName("Курьер успешно авторизуется")
        @Description("Post-запрос /api/v1/courier/login")
        @Test
        public void courierCanLogInTest(){
            CourierCanLogOn courierCanLogOn = new CourierCanLogOn("lalalalaal", "1234");
            BasicPostApi(courierCanLogOn,LOGIN_COURIER).then().assertThat().statusCode(200)
                    .body("id", notNullValue());
            idCourier = BasicPostApi(courierCanLogOn,LOGIN_COURIER).asString().replace("\"id\":","").replaceAll("[{}]","");
            BasicDeleteApi(idCourier).then().assertThat().statusCode(SC_OK);

        }
        @DisplayName("Курьер успешно авторизуется без имени")
        @Description("Post-запрос /api/v1/courier/login")
        @Test
        public void courierCanLogInNotFirstNameTest(){
            CourierCanLogOn courierCanLogOnNotFirstName = new CourierCanLogOn("lalalalaall", "1234");
            BasicPostApi(courierCanLogOnNotFirstName,LOGIN_COURIER).then().assertThat().statusCode(200)
                    .body("id", notNullValue());
            idCourier = BasicPostApi(courierCanLogOnNotFirstName,LOGIN_COURIER).asString().replace("\"id\":","").replaceAll("[{}]","");
            BasicDeleteApi(idCourier).then().assertThat().statusCode(SC_OK);
        }

        @DisplayName("Система вернет ошибку при авторизации с неправильным логином")
        @Description("Post-запрос /api/v1/courier/login")
        @Test
        public void respLoginWrongTest(){
            CourierCanLogOn courierCanLogOn = new CourierCanLogOn("pumpumpum", "1234");
            BasicPostApi(courierCanLogOn,LOGIN_COURIER).then().assertThat().statusCode(SC_NOT_FOUND)
                    .body("message", equalTo("Учетная запись не найдена"));
        }

        @DisplayName("Система вернет ошибку при авторизации с неправильным паролем")
        @Description("Post-запрос /api/v1/courier/login")
        @Test
        public void respPassWrongTest(){
            CourierCanLogOn courierCanLogOn = new CourierCanLogOn("lalalalaal", "54321");
            BasicPostApi(courierCanLogOn,LOGIN_COURIER).then().assertThat().statusCode(SC_NOT_FOUND)
                    .body("message", equalTo("Учетная запись не найдена"));
        }

        @DisplayName("Система вернет ошибку при авторизации с пустым логином")
        @Description("Post-запрос /api/v1/courier/login")
        @Test
        public void respMistakeWithoutLoginTest(){
            CourierCanLogOn courierCanLogOn = new CourierCanLogOn("", "1234");
            BasicPostApi(courierCanLogOn,LOGIN_COURIER).then().assertThat() .statusCode(SC_BAD_REQUEST)
                    .body("message", equalTo("Недостаточно данных для входа"));
        }

        @DisplayName("Система вернет ошибку при авторизации с пустым паролем")
        @Description("Post-запрос /api/v1/courier/login")
        @Test
        public void respMistakeWithoutPassTest(){
            CourierCanLogOn courierCanLogOn = new CourierCanLogOn("lalalalaal", "");
            BasicPostApi(courierCanLogOn,LOGIN_COURIER).then().assertThat() .statusCode(SC_BAD_REQUEST)
                    .body("message", equalTo("Недостаточно данных для входа"));
        }
    }
