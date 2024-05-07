import io.restassured.response.ValidatableResponse;
import org.example.Courier;
import org.example.CourierChecks;
import org.example.CourierClient;
import org.example.CourierCredentials;
import org.junit.Test;


public class CreateCourierTest {

    private final CourierClient client = new CourierClient();
    private final CourierChecks check = new CourierChecks();
    private Courier courier;

    private void randomCourier(){
        courier = Courier.random();
    }
    @Test
    public void createNewCourier() {
        randomCourier();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdSuccessfully(createResponse);

        deleteCourier();
    }

    @Test
    public void createDoubleCourier() {
        randomCourier();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdSuccessfully(createResponse);

        ValidatableResponse createFailedResponse = client.createCourier(courier);
        check.createdFailed(createFailedResponse);

        deleteCourier();
    }
    @Test
    public void createCourierWithoutLogin() {
        randomCourier();
        courier.setLogin("");
        ValidatableResponse createdFailedWithoutLogin = client.createCourier(courier);
        check.createdFailedWithoutLogin(createdFailedWithoutLogin);

    }

    private void deleteCourier() {
        var creds = CourierCredentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        var courierId = check.loggedInSuccessfully(loginResponse);

        if (courierId != 0) {
            ValidatableResponse response = client.deleteCourier(courierId);
            check.deletedSuccessfully(response);
        }
    }
 }
