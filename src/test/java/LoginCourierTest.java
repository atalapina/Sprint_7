
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.example.Courier;
import org.example.CourierChecks;
import org.example.CourierClient;
import org.example.CourierCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;

public class LoginCourierTest {


    private final CourierClient client = new CourierClient();
    private final CourierChecks check = new CourierChecks();
    int courierId;

    private Courier courier;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @After
    public void deleteCourier() {
        if (courierId != 0) {
            ValidatableResponse response = client.deleteCourier(courierId);
            check.deletedSuccessfully(response);
        }
    }

    @Test
    public void loginCourierCheckStatus() {
        var courier = Courier.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdSuccessfully(createResponse);

        var creds = CourierCredentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedInSuccessfully(loginResponse);

        assertNotEquals(0, courierId);

    }

    @Test
    public void loginCourierWithoutPassword() {
        var courier = Courier.random();
        CourierCredentials creds = CourierCredentials.from(courier);
        creds.setPassword("");
        ValidatableResponse loginWithoutPassword = client.loginCourier(creds);
        check.loginFailedWithoutPassword(loginWithoutPassword);
    }
    @Test
    public void loginUnknownCourier() {

        CourierCredentials creds = new CourierCredentials("rtyiopf", "P@ssw0rd123");

        ValidatableResponse loginUnknownCourier = client.loginCourier(creds);
        check.loginWithUnknownCourier(loginUnknownCourier);
    }
}