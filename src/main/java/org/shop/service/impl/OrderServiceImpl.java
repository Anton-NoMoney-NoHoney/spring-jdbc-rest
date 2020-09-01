package org.shop.service.impl;

import org.shop.db.OrderDetailsRepository;
import org.shop.db.OrdersRepository;
import org.shop.db.entity.OrderEntity;
import org.shop.dto.OrderDto;
import org.shop.service.OrdersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrderDetailsRepository orderDetailsRepository;


    public OrderServiceImpl(OrdersRepository ordersRepository, OrderDetailsRepository orderDetailsRepository) {
        this.ordersRepository = ordersRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public List<OrderDto> findAll()   {
        return ordersRepository.findAll().stream().map(OrderEntity::toDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto findOrderBy(long id)   {

        OrderEntity entity=ordersRepository.findById(id);
        return entity.toDto();
    }

    @Override
    public void saveOrder(OrderDto orderDto)  {
        OrderEntity entity=OrderEntity.toEntity(orderDto);
        ordersRepository.saveOrderEntity(entity);
    }

    @Override
    public void deleteOrder(long orderId)  {
        Long orderDetailId=ordersRepository.getOrderIdInOrders(orderId);
        ordersRepository.deleteOrderEntity(orderId);
        orderDetailsRepository.deleteOrderDetailEntity(orderDetailId);
    }
}
