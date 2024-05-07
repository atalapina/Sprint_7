package org.example;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import java.net.HttpURLConnection;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class OrderChecks {

    @Step("check create order")
    public int checkCreateOrder(ValidatableResponse orderCreate) {
        return orderCreate
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("track");
    }
    @Step("check order list")
    public OrderList checkGetOrderList(ValidatableResponse orderList) {
        OrderList orders = orderList
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .as(OrderList.class);
        return orders;

    }

}
