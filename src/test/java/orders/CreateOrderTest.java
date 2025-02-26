package orders;

import basic.BasicUrl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

import static constant.AdressAndPens.CREATE_ORDER;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;

    @RunWith(Parameterized.class)
    public class CreateOrderTest extends BasicUrl {

        private final String name;
        private final String surname;
        private final String address;
        private final String stationMetro;
        private final String phoneNumber;
        private int rentTime = 1;
        private String deliveryDate = "2025-02-28";
        private String color;

        public CreateOrderTest(String name, String surname, String address, String stationMetro, String phoneNumber, int rentTime, String deliveryDate, String color) {
            this.name = name;
            this.surname = surname;
            this.address = address;
            this.stationMetro = stationMetro;
            this.phoneNumber = phoneNumber;
            this.rentTime = rentTime;
            this.deliveryDate = deliveryDate;
            this.color = color;

        }

        @Parameterized.Parameters
        public static Object[][] setFormData() {
            return new Object[][]{
                    {"Ольга", "Ермакова", "Москва ул. флотская д. 3", "Черкизовская", "89163753229", 1, "2025-02-28", "BLACK"},
                    {"Ольга", "Ермакова", "Москва ул. флотская д. 3", "Черкизовская", "89163753229", 1, "2025-02-28", "GREY"},
                    {"Ольга", "Ермакова", "Москва ул. флотская д. 3", "Черкизовская", "89163753229", 1, "2025-02-28", "BLACK GREY"},
                    {"Ольга", "Ермакова", "Москва ул. флотская д. 3", "Черкизовская", "89163753229", 1, "2025-02-28", ""}
            };
        }

        @DisplayName("Успешное создание заказа")
        @Description("Post-запрос /api/v1/orders")
        @Test
        public void aCreateOrderWithColorTest() {
            String track = BasicPostApi(setFormData(), CREATE_ORDER).then().assertThat().statusCode(201)
                    .body("track", notNullValue()).and().extract().path("track").toString();
            BasicPutApi(track).then().assertThat().statusCode(SC_OK);
        }
    }
