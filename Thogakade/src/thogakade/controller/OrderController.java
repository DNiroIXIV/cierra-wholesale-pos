/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thogakade.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import thogakade.db.DBConnection;
import thogakade.model.Order;

/**
 *
 * @author Nirodha
 */
public class OrderController {
    public static String getLastOrderId() throws ClassNotFoundException, SQLException{
        ResultSet rst = DBConnection.getInstance().getConnection().createStatement()
                .executeQuery("SELECT id FROM orders ORDER BY id DESC LIMIT 1");
        return rst.next() ? rst.getString("id") : null;        
    }
    
    public static boolean addNewOrder(Order order) throws ClassNotFoundException, SQLException{
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE orders SET id=?, date=?, customerId=?");
        pstm.setObject(1, order.getId());
        pstm.setObject(2, order.getDate());
        pstm.setObject(3, order.getCustomerId());        
        return pstm.executeUpdate() > 0;        
    }
}
