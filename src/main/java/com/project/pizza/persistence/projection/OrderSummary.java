package com.project.pizza.persistence.projection;

import java.time.LocalDate;

public interface OrderSummary {

    Integer getOrderId();
    String getCustomerName();
    LocalDate getOrderDate();
    Double getOrderTotal();
    String getPizzaNames();
}
