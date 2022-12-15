package generator;

import com.github.javafaker.Faker;
import pojo.CreateOrder;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class OrderGenerator {
    public static CreateOrder getNewOrder(List<String> color) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CreateOrder createOrder = new CreateOrder();
        Faker faker = new Faker(new Locale("ru"));
        createOrder.setFirstName(faker.name().firstName());
        createOrder.setLastName(faker.name().lastName());
        createOrder.setAddress(faker.address().streetAddress());
        createOrder.setMetroStation(faker.address().firstName());
        createOrder.setPhone(faker.phoneNumber().phoneNumber());
        createOrder.setRentTime(faker.number().numberBetween(1, 300));
        createOrder.setDeliveryDate(simpleDateFormat.format(faker.date().future(faker.random().nextInt(1,20), TimeUnit.DAYS)).toString());
        createOrder.setComment(faker.lordOfTheRings().location());
        createOrder.setColor(color);
        return createOrder;
    }
}
