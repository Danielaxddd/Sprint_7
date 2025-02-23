package orders;

import basic.BasicUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static constant.AdressAndPens.createOrder;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetListOrderTest extends BasicUrl {

    @Test
    public void GetListOrderCheck(){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .when()
                        .get(createOrder);
        response.then().assertThat().body(notNullValue())
                .statusCode(200);
    }
}