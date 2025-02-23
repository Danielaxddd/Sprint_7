package courier;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static constant.AdressAndPens.deleteCourier;

public class DeleteCourierTest {
    @Test
    public void DeleteCourierCheck(){
        ALoginCourierTest.getIdCourier();
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .when()
                        .delete(deleteCourier + ALoginCourierTest.getIdCourier());
        response.then().assertThat().statusCode(200);
    }
}
