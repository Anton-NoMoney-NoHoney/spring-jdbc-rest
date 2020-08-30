package org.shop.rest;


import com.google.gson.Gson;
import org.shop.db.OrderDetailsRepository;
import org.shop.db.OrdersRepository;
import org.shop.db.impl.OrderDetailsRepositoryImpl;
import org.shop.db.impl.OrdersRepositoryImpl;
import org.shop.service.OrdersService;
import org.shop.service.impl.OrderServiceImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@EnableAutoConfiguration
@RestController
@RequestMapping("/")
public class Rest {



    OrdersRepository ordersRepository =new OrdersRepositoryImpl();
    OrderDetailsRepository orderDetailsRepository =new OrderDetailsRepositoryImpl();
    OrdersService service=new OrderServiceImpl(ordersRepository, orderDetailsRepository);

    @GetMapping("/getAll")
    public String findAllRows(){

        return new Gson().toJson(service.findAll());
    }

    @GetMapping("/")
    public String getVoice(){

        return "OK YOU ";
    }

}
