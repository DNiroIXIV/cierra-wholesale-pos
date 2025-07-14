/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cierra.lk.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import cierra.lk.db.DBConnection;
import cierra.lk.model.Order;
import java.sql.Connection;

/**
 *
 * @author Nirodha
 */
public class OrderController {

    public static String getLastOrderId()
            throws ClassNotFoundException, SQLException {
        ResultSet rst = DBConnection.getInstance().getConnection()
                .createStatement()
                .executeQuery("SELECT id FROM orders ORDER BY id DESC LIMIT 1");
        return rst.next() ? rst.getString("id") : null;
    }

    public static boolean addNewOrder(Order order)
            throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement pstm = connection.prepareStatement(
                    "INSERT INTO orders VALUES(?,?,?)");
            pstm.setObject(1, order.getId());
            pstm.setObject(2, order.getDate());
            pstm.setObject(3, order.getCustomerId());
            boolean isOrderAdded = pstm.executeUpdate() > 0;
            if (isOrderAdded) {
                boolean orderDetailsAdded = OrderDetailController.addOrderDetail(
                        order.getOrderDetailList());
                if (orderDetailsAdded) {
                    boolean isStockUpdated = ItemController.updateStock(order.getOrderDetailList());
                    if (isStockUpdated) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        }finally{
            connection.setAutoCommit(true);
        }
    }
}
