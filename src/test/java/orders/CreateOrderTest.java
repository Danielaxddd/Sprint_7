package orders;

import basic.BasicUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static io.restassured.RestAssured.given;
import static constant.AdressAndPens.createOrder;
import static org.hamcrest.CoreMatchers.notNullValue;

    @RunWith(Parameterized.class)
    public class CreateOrderTest extends BasicUrl {

        private final String name;
        private final String surname;
        private final String address;
        private final String stationMetro;
        private final String phoneNumber;
        private int  rentTime = 1;
        private String  deliveryDate = "2025-02-28";
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

        // Тест проверяет что можно указать один из цветов — BLACK или GREY;
//можно указать оба цвета;
//можно совсем не указывать цвет;
//тело ответа содержит track.
        @Test
        public void CreateOrderCheckWithColor(){
            Response response =
                    given()
                            .header("Content-type", "application/json")
                            .body(setFormData())
                            .when()
                            .post(createOrder);
            response.then().assertThat().body("track", notNullValue())
                    .statusCode(201);
        }
    }
