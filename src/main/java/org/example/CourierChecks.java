package org.example;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.net.HttpURLConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class CourierChecks {

    @Step("check courier created successfully")
    public void createdSuccessfully(ValidatableResponse createResponse) {
        boolean created = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("ok");
        assertTrue(created);
    }
    @Step("check courier double created fail")
    public void createdFailed(ValidatableResponse createFailedResponse) {
        String message = createFailedResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CONFLICT)
                .extract()
                .path("message");
        assertEquals(message, "Этот логин уже используется. Попробуйте другой.");
    }

    @Step("check created without login")
    public void createdFailedWithoutLogin(ValidatableResponse createWithoutLogin) {
        String message = createWithoutLogin
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .extract()
                .path("message");
        assertEquals(message, "Недостаточно данных для создания учетной записи");
    }
    @Step("check login without password")
    public void loginFailedWithoutPassword(ValidatableResponse loginWithoutPassword) {
        String message = loginWithoutPassword
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .extract()
                .path("message");
        assertEquals(message, "Недостаточно данных для входа");
    }
    @Step("check login without password")
    public void loginWithUnknownCourier(ValidatableResponse loginUnknownCourier) {
        String message = loginUnknownCourier
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
                .extract()
                .path("message");
        assertEquals(message, "Учетная запись не найдена");
    }
    @Step("check return id")
    public int loggedInSuccessfully(ValidatableResponse loginResponse) {
        return loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("id");
    }

    public void deletedSuccessfully(ValidatableResponse response) {

    }
}
