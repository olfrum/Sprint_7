package tclogic;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.CreateCourier;
import pojo.LoginCourier;

import static io.restassured.RestAssured.given;

public class CourierTC {
    private static final String BASE_URI = "http://qa-scooter.praktikum-services.ru";
    private static final String NEW_COURIER_API = "/api/v1/courier/";
    private static final String LOGIN_COURIER = "/api/v1/courier/login";

    @Step("Create Courier")
    public static Response createCourier(CreateCourier body) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(body)
                .when()
                .post(NEW_COURIER_API);
    }
    @Step("Login Courier")
    public static Response loginCourier(LoginCourier body) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(body)
                .when()
                .post(LOGIN_COURIER);
    }
    @Step("Get CourierID")
    public static int getCourierId(CreateCourier body) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(body)
                .when()
                .post(LOGIN_COURIER)
                .then()
                .extract()
                .path("id");
    }
    @Step("Delete courier from database")
    public Response deleteCourier(int id) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .when()
                .delete(NEW_COURIER_API + id);
    }
    @Step("Delete created courier, status code 200 and 201")
    public static void deleteCreatedCourier(Response response, CreateCourier body){
        CourierTC request = new CourierTC();
        if (response.then().extract().statusCode() == 200 || response.then().extract().statusCode() == 201){
            request.deleteCourier(request.getCourierId(body));
        }
    }



}
