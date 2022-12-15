package courierTests;

import generator.CourierGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.CreateCourier;
import pojo.LoginCourier;
import tclogic.CourierTC;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

@DisplayName("Login Courier")
public class LoginCourierTest {
    public static final String ERROR_400 = "Недостаточно данных для входа";
    public static final String ERROR_404 = "Учетная запись не найдена";

    @Test
    @DisplayName("Courier authorization")
    @Description("Result: status code 200")
    public void loginCourierWithAllValidParams() {
        CreateCourier courierData = CourierGenerator.getNewCourtier();
        Response response = CourierTC.createCourier(courierData);

        response.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        Response loginResponse = CourierTC.loginCourier((new LoginCourier(courierData.getLogin(), courierData.getPassword())));

        loginResponse.then()
                .statusCode(200)
                .and()
                .assertThat().body("id", notNullValue());

        CourierTC.deleteCreatedCourier(response, courierData);
    }
    @Test
    @DisplayName("Login with empty password field")
    @Description("Result: status code 400")
    public void loginCourierWithEmptyPasswordField() {
        CreateCourier courierData = CourierGenerator.getNewCourtier();
        Response response = CourierTC.createCourier(courierData);

        response.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        Response loginResponse = CourierTC.loginCourier((new LoginCourier(courierData.getLogin(), "")));

        loginResponse.then()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo(ERROR_400));

        CourierTC.deleteCreatedCourier(response, courierData);
    }

    @Test
    @DisplayName("Login with empty login field")
    @Description("Result: status code 400")
    public void loginCourierWithEmptyLoginField() {
        CreateCourier courierData = CourierGenerator.getNewCourtier();
        Response response = CourierTC.createCourier(courierData);

        response.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        Response loginResponse = CourierTC.loginCourier((new LoginCourier("", courierData.getPassword())));

        loginResponse.then()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo(ERROR_400));

        CourierTC.deleteCreatedCourier(response, courierData);
    }

    @Test
    @DisplayName("Login with nonExist courier")
    @Description("Result: status code 404")
    public void loginWithNonExistCourier() {
        Response response = CourierTC.loginCourier(CourierGenerator.loginNonFirstNameCourier());

        response.then()
                .statusCode(404)
                .and()
                .assertThat().body("message", equalTo(ERROR_404));
    }
}
