package org.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

public class OrderClient extends Client {

    private static final String ORDER_PATH = "/orders";

    @Step("create order")
    public ValidatableResponse orderCreate(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then().log().all();
    }
    @Step("create order")
    public ValidatableResponse getOrderList() {
        return spec()
                .when()
                .get(ORDER_PATH)
                .then().log().all();
    }
}

