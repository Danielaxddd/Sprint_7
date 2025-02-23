package basic;

import io.restassured.RestAssured;
import org.junit.Before;

import static constant.AdressAndPens.mainUrl;

public class BasicUrl {
    @Before
    public void setUp() {
        RestAssured.baseURI = mainUrl;
    }
}
