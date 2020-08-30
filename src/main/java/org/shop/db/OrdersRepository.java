package org.shop.db;

import org.shop.db.entity.OrderEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Here you need to specify
 * all methods which you think will be useful to complete your task
 */
@Repository
public interface OrdersRepository {

    List<OrderEntity> findAll() ;

    OrderEntity findById(Long id)  ;

    void saveOrderEntity(OrderEntity entity) ;

    void deleteOrderEntity(Long id)  ;

    Long getOrderIdInOrders(Long id)  ;


}
