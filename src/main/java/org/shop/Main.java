package org.shop;

import org.shop.db.OrderDetailsRepository;
import org.shop.db.OrdersRepository;
import org.shop.db.impl.OrderDetailsRepositoryImpl;
import org.shop.db.impl.OrdersRepositoryImpl;
import org.shop.dto.OrderDetailDto;
import org.shop.dto.OrderDto;
import org.shop.service.OrdersService;
import org.shop.service.impl.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {



    public static void main(String[] args) throws SQLException, ClassNotFoundException {

//        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(SpringContext.class);


        OrdersRepository ordersRepository =new OrdersRepositoryImpl();
        OrderDetailsRepository orderDetailsRepository =new OrderDetailsRepositoryImpl();
        OrdersService service=new OrderServiceImpl(ordersRepository, orderDetailsRepository);

       List<OrderDto> list=service.findAll();
       OrderDto ki =service.findOrderBy(5);

        System.out.println(list);


        List<OrderDetailDto> jhg=new ArrayList<OrderDetailDto>();
        jhg.add(new OrderDetailDto("name",77.02));
        OrderDto dto=new OrderDto("nuuu","kill",jhg);


        service.saveOrder(dto);
        service.deleteOrder(2);

    }

}
