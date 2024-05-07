package org.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

    public class CourierClient extends Client {

        private static final String COURIER_PATH = "/courier";

        @Step("create courier")
        public ValidatableResponse createCourier(Courier courier) {
            return spec()
                    .body(courier)
                    .when()
                    .post(COURIER_PATH)
                    .then().log().all();
        }
        @Step("login courier")
        public ValidatableResponse loginCourier(CourierCredentials creds) {
            return spec()
                    .body(creds)
                    .when()
                    .post(COURIER_PATH + "/login")
                    .then().log().all();
        }
        @Step("delete courier")
        public ValidatableResponse deleteCourier(int id) {
            return spec()
                    .body(Map.of("id", id))
                    .when()
                    .delete(COURIER_PATH + "/" + id)
                    .then().log().all();
        }
    }

