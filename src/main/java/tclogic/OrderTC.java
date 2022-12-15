package tclogic;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.CreateOrder;

import static io.restassured.RestAssured.given;

public class OrderTC {
    private static final String BASE_URI = "http://qa-scooter.praktikum-services.ru";
    private static final String ORDER_URI = "/api/v1/orders";
    private static final String CANCEL_ORDER = "/api/v1/orders/cancel";


    @Step("Create Order")
    public static Response createOrder(CreateOrder body) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(body)
                .when()
                .post(ORDER_URI);
    }

    @Step("Get order list")
    public static Response getOrderList() {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .when()
                .get(ORDER_URI);
    }
    @Step("Get track number")
    public static int getTrackNumberOfOrder(CreateOrder body){
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(body)
                .when()
                .post(ORDER_URI)
                .then()
                .extract()
                .path("track");
    }
    @Step("Cancel order")
    public static void cancelOrder(int track) {
        given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(track)
                .when()
                .put(CANCEL_ORDER);
    }
}
