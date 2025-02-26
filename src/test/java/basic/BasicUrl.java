package basic;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;

import static constant.AdressAndPens.*;
import static io.restassured.RestAssured.given;

public class BasicUrl {
    @Before
    public void setUp() {
        RestAssured.baseURI = MAIN_URL;
    }
    public Response BasicPostApi (Object a, String api){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(a)
                        .when()
                        .post(api);
        return response;
    }

    public Response BasicDeleteApi (String id){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .when()
                        .delete(DELETE_COURIER + id);
        return response;
    }
    public Response BasicGetApi (){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .when()
                        .get(CREATE_ORDER);
        return response;
    }
    public Response BasicPutApi (Object a){
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .put(CANCEL_ORDER + "?track=" + a);
        return response;
    }
}
