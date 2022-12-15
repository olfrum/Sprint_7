package generator;

import com.github.javafaker.Faker;
import pojo.CreateCourier;
import pojo.LoginCourier;

import java.util.Locale;

public class CourierGenerator {

    public static CreateCourier getNewCourtier(){
        Faker faker = new Faker(new Locale("ru"));
        return new CreateCourier (
            faker.name().username(),
            faker.internet().password(8, 10),
            faker.name().firstName());
    }

    public static CreateCourier getNewCourierWithoutFirstName(){
        Faker faker = new Faker(new Locale("ru"));
        return new CreateCourier (
                faker.name().username(),
                faker.internet().password(8, 10),
                "");
    }

    public static CreateCourier getNewCourierWithoutLogin(){
        Faker faker = new Faker(new Locale("ru"));
        return new CreateCourier (
                "",
                faker.internet().password(8, 10),
                faker.name().firstName());
    }

    public static CreateCourier getNewCourierWithoutPassword(){
        Faker faker = new Faker(new Locale("ru"));
        return new CreateCourier (
                faker.name().username(),
                "",
                faker.name().firstName());
    }

    public static LoginCourier loginNonFirstNameCourier(){
        Faker faker = new Faker(new Locale("ru"));
        return new LoginCourier (
                faker.name().username(),
                faker.internet().password(8, 10));
    }
}
