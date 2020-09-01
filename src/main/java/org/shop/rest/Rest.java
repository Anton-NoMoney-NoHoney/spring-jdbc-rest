package org.shop.rest;


import org.shop.dto.OrderDto;
import org.shop.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
public class Rest {

    @Autowired
    private OrdersService service;


    @GetMapping(value = "/getAll",produces =  "application/json")
    public List<OrderDto> findAllRows(){

        return service.findAll();
    }

    @GetMapping(value = "/get/{id}",produces =  "application/json")
    public OrderDto findOrderBy(@PathVariable(value = "id")Integer id){

        return service.findOrderBy(id);
    }

    @DeleteMapping(value = "/delete/{id}", produces =  "application/json")
    public ResponseEntity deleteById(@PathVariable(value = "id")Integer id){
         service.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("delete comlite ");
    }

    @PostMapping(value = "/save", produces =  "application/json")
    public ResponseEntity addNewOrder(@RequestBody  OrderDto orderDto){
        service.saveOrder( orderDto);
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("save complite ");
    }

    @GetMapping("/")
    public String getVoice(){
        return "<br><a href=http://localhost:8080/spring_jdbc_workshop_war_exploded/getAll>getAll</a>" +
                "<br><a href=http://localhost:8080/spring_jdbc_workshop_war_exploded/get/1>get/Id( by default 1 )</a><br>" +
                "Delete - http://localhost:8080/spring_jdbc_workshop_war_exploded/delete/{id}<br>" +
                " Post - http://localhost:8080/spring_jdbc_workshop_war_exploded/save <br>" +
                "{<br>  \"name\":\"post\",<br>  \"client\":\"post\",<br>  \"orderDetails\":[{\"name\":\"tsop\",\"price\":999.0},{\"name\":\"tsop_1\",\"price\":222.0},{\"name\":\"name\",\"price\":333.0}]<br>}";
    }

}
