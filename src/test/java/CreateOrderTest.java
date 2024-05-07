import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.example.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertNotEquals;
@RunWith(Parameterized.class)

public class CreateOrderTest {
    private final OrderClient client = new OrderClient();
    private final OrderChecks check = new OrderChecks();
    private Order order;
    public CreateOrderTest(Order order) {
        this.order = order;
    }

    @Parameterized.Parameters
    public static Object[][] orderData() {
        return new Object[][]{
                {new Order("Иван", "Перов", "Москва, Пушкина", "4", "+79632583369", 2, "2024-04-22", "не звонить",
                        new String[]{"BLACK"})},
                {new Order("Марфа", "Ветеров", "Москва, Пушкина", "2", "+79632583370", 2, "2024-04-22", "не звонить",
                        new String[]{"GRAY"})},
                {new Order("Марфа", "Ветеров", "Москва, Пушкина", "2", "+79632583371", 2, "2024-04-29", "не звонить",
                        new String[]{"GRAY","BLACK"})},
                {new Order("Марфа", "Ветеров", "Москва, Пушкина", "2", "+79632583370", 2, "2024-04-22", "не звонить",
                        new String[]{""})},
        };
    }
    @Test
    public void createNewOrder(){
        ValidatableResponse orderCreate = client.orderCreate(order);
        check.checkCreateOrder(orderCreate);

    }

}
