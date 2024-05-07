import com.google.gson.Gson;
import io.restassured.response.ValidatableResponse;
import org.example.*;
import org.junit.Test;


public class GetOrderListTest {
    private final OrderClient client = new OrderClient();
    private final OrderChecks check = new OrderChecks();
    Gson gson = new Gson();

    @Test
    public void getOrderList(){

        ValidatableResponse getOrderList = client.getOrderList();
        check.checkGetOrderList(getOrderList);
    }
}
