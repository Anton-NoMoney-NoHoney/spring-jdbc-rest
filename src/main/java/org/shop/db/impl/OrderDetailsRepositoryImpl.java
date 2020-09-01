package org.shop.db.impl;

import org.shop.db.OrderDetailsRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class OrderDetailsRepositoryImpl implements OrderDetailsRepository {

    private  String DB_URL = "jdbc:mysql://localhost:3306/skillsup?serverTimezone=UTC";
    private  String USER = "root";
    private  String PASS = "killall";

    Connection conn = null;
    Statement stmt = null;

    public OrderDetailsRepositoryImpl()  {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteOrderDetailEntity(Long id)  {
        try {
            stmt.executeUpdate("DELETE FROM order_details t  WHERE t.id="+id+";");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
