package orderTests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import tclogic.OrderTC;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

@DisplayName("Order List")
public class GetOrderListTest {
    @Test
    @DisplayName("Get Order List")
    @Description("Result: status code 200")
    public void getListOfOrder() {
        Response listOfOrder = OrderTC.getOrderList();
        listOfOrder.then()
                .statusCode(200)
                .and()
                .body("orders", hasSize(greaterThan(0)));
    }
}
