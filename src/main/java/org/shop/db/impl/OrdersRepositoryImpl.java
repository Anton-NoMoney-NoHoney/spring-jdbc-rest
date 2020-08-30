package org.shop.db.impl;

import org.shop.db.OrdersRepository;
import org.shop.db.entity.OrderDetailEntity;
import org.shop.db.entity.OrderEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrdersRepositoryImpl  implements OrdersRepository {


    private  String DB_URL = "jdbc:mysql://localhost:3306/skillsup?serverTimezone=UTC";


    private  String USER = "root";
    private  String PASS = "killall";

    Connection conn = null;
    Statement stmt = null;


    public OrdersRepositoryImpl()  {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }


    @Override
    public List<OrderEntity> findAll() {

        String sql=" select t.id,t.name,t.client,od.id,od.name,od.price from orders t join order_details od on od.id = t.order_id ";
        ResultSet rs = null;
        List<OrderEntity> orderEntities=new ArrayList<>();
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()){

                OrderEntity entity= new OrderEntity();
                entity.setId((rs.getLong("t.id")));
                entity.setName(rs.getString("t.name"));
                entity.setClient(rs.getString("t.client"));
                OrderDetailEntity order =new OrderDetailEntity(rs.getLong("od.id"),
                        rs.getString("od.name"),
                        Double.parseDouble(rs.getString("price")));
                List<OrderDetailEntity> list=new ArrayList<>();
                list.add(order);
                entity.setOrderDetailEntities(list) ;
                orderEntities.add(entity);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return orderEntities;
    }

    @Override
    public OrderEntity findById(Long id) {

        String sql=" select t.id,t.name,t.client,od.id,od.name,od.price from orders t join order_details od on od.id = t.order_id " +
                " where t.id='"+id+"'";
        ResultSet rs = null;
        OrderEntity entity= new OrderEntity();
        try {
            rs = stmt.executeQuery(sql);

            while (rs.next()){
                entity.setId(Long.parseLong(rs.getString("t.id")));
                entity.setName(rs.getString("t.name"));
                entity.setClient(rs.getString("t.client"));
                OrderDetailEntity order =new OrderDetailEntity(rs.getLong("od.id"),
                        rs.getString("od.name"),
                        Double.parseDouble(rs.getString("price")));
                List<OrderDetailEntity> list=new ArrayList<>();
                list.add(order);
                entity.setOrderDetailEntities(list) ;
            }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        return entity;
    }

    @Override
    public void saveOrderEntity(OrderEntity entity){

        for (OrderDetailEntity o : entity.getOrderDetailEntities()) {
            try {
                stmt.executeUpdate("insert into order_details(name, price) " +
                        "VALUES('" + o.getName() + "', " + o.getPrice() + ")");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                stmt.executeUpdate("insert into orders(name, client,order_id) " +
                        "VALUES ('" + entity.getName() + "', '" + entity.getClient() + "'," +
                        "(select max(id)  from order_details  where name='"+ o.getName()+"' and price="+o.getPrice()+"))");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void deleteOrderEntity(Long id)  {
        try {
            stmt.executeUpdate("DELETE FROM orders t  WHERE t.id="+id+";");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Long getOrderIdInOrders(Long id)  {
        Long ids=0l;
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(" select t.order_id from orders t where t.id="+id+"");

        while (rs.next()){ ids=rs.getLong("t.order_id");}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ids;
    }

}
