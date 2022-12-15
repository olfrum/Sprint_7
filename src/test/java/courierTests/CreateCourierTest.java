package courierTests;


import generator.CourierGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.CreateCourier;
import tclogic.CourierTC;

import static org.hamcrest.core.IsEqual.equalTo;

@DisplayName("Create Courier")
public class CreateCourierTest {

    public static final String ERROR_409 = "Этот логин уже используется";
    public static final String ERROR_400 = "Недостаточно данных для создания учетной записи";

    @Test
    @DisplayName("Create new courier with correct data")
    @Description("Result : status code 201")
    public void createCourierWithCorrectValueData(){
        CreateCourier courierData = CourierGenerator.getNewCourtier();
        Response response = CourierTC.createCourier(courierData);
        response.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        CourierTC.deleteCreatedCourier(response, courierData);
    }

    @Test
    @DisplayName("Create of copy courier")
    @Description("Result: status code 409")
    public void createTwoEqualCourier() {
        CreateCourier courierData = CourierGenerator.getNewCourtier();
        Response response = CourierTC.createCourier(courierData);

        response.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        Response errorResponse = CourierTC.createCourier(courierData);

        errorResponse.then()
                .statusCode(409)
                .and()
                .assertThat().body("message", equalTo(ERROR_409));

        CourierTC.deleteCreatedCourier(response, courierData);
    }

    @Test
    @DisplayName("Create new courier with correct name, without 'login'")
    @Description("Result: status code 400")
    public void createCourierWithoutLogin() {
        CreateCourier courierData = CourierGenerator.getNewCourierWithoutLogin();
        Response response = CourierTC.createCourier(courierData);

        response.then()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo(ERROR_400));

        CourierTC.deleteCreatedCourier(response, courierData);
    }

    @Test
    @DisplayName("Create new courier with correct name, without 'password'")
    @Description("Result: status code 400")
    public void createCourierWithoutPassword() {
        CreateCourier courierData = CourierGenerator.getNewCourierWithoutPassword();
        Response response = CourierTC.createCourier(courierData);

        response.then()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo(ERROR_400));

        CourierTC.deleteCreatedCourier(response, courierData);
    }

    @Test
    @DisplayName("Create new courier with correct name, without 'firstName'")
    @Description("Result: status code 201")
    public void createCourierWithoutFirstName() {
        CreateCourier courierData = CourierGenerator.getNewCourierWithoutFirstName();
        Response response = CourierTC.createCourier(courierData);

        response.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        CourierTC.deleteCreatedCourier(response, courierData);
    }
}
