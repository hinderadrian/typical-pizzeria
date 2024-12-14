package com.project.pizza.persistence.repository;

import com.project.pizza.persistence.entity.OrderEntity;
import com.project.pizza.persistence.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {

    List<OrderEntity> findAllByDateAfter(LocalDateTime date);

    List<OrderEntity> findAllByMethodIn(List<String> methods);

    @Query(nativeQuery = true, value = "SELECT * FROM pizza_order WHERE id_customer = :id ORDER BY total ASC")
    List<OrderEntity> findCustomerOrders(@Param("id") String customerId);

    @Query(value = """
            SELECT  po.id_order                AS orderId,
                    cu.name                    AS customerName,
                    po.date                    AS orderDate,
                    po.total                   AS orderTotal,
                    GROUP_CONCAT(pi.name)      AS pizzaNames
            FROM pizza_order po
                 INNER JOIN customer cu ON po.id_customer = cu.id_customer
                 INNER JOIN order_item oi ON po.id_order = oi.id_order
                 INNER JOIN pizza pi ON oi.id_pizza = pi.id_pizza
            WHERE po.id_order = :orderId
            GROUP BY po.id_order,
                     cu.name,
                     po.date,
                     po.total""", nativeQuery = true)
    OrderSummary getOrderSummary(Integer orderId);

    @Procedure(value = "take_random_pizza_order", outputParameterName = "order_taken")
    boolean saveRandomOrder(@Param("id_customer") String idCustomer, @Param("method") String method);

}
