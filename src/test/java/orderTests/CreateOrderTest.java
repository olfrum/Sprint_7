package orderTests;

import generator.OrderGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pojo.CreateOrder;
import tclogic.CourierTC;
import tclogic.OrderTC;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(Parameterized.class)
@DisplayName("Create Order")
public class CreateOrderTest {

    private final List<String> colorsOfOrder;
    private final int expectedStatus;

    public CreateOrderTest(List<String> colorsOfOrder, int expectedStatus) {
        this.colorsOfOrder = colorsOfOrder;
        this.expectedStatus = expectedStatus;
    }

    @Parameterized.Parameters(name = "color: {0}, return {1}")
    public static Object[][] getData() {
        return new Object[][]{
                {List.of("BLACK"), 201},
                {List.of("GREY"), 201},
                {Arrays.asList("BLACK", "GREY"), 201},
                {Collections.emptyList(), 201}
        };
    }

    @Test
    @DisplayName("Create parametrized order")
    @Description("Result: status code 201")
    public void createOrderWithParam() {
        CreateOrder request = OrderGenerator.getNewOrder(colorsOfOrder);
        Response response = OrderTC.createOrder(request);

        response.then()
                .statusCode(expectedStatus)
                .and()
                .assertThat().body("track", notNullValue());
        OrderTC.cancelOrder(OrderTC.getTrackNumberOfOrder(request));
    }
}
