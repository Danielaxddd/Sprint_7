package orders;

import basic.BasicUrl;
import org.junit.Test;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetListOrderTest extends BasicUrl {

    @DisplayName("Получение непустого списка заказов")
    @Description("Get-запрос /api/v1/orders")
    @Test
    public void ListOrderTest(){
        BasicGetApi().then().assertThat().statusCode(SC_OK)
                .body(notNullValue());
    }
}