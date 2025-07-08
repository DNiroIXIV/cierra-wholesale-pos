/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thogakade.controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import thogakade.db.DBConnection;
import thogakade.model.OrderDetail;

/**
 *
 * @author Nirodha
 */
public class OrderDetailController {
    public static boolean addOrderDetail(ArrayList<OrderDetail> orderDetailList) throws ClassNotFoundException, SQLException{
        for (OrderDetail orderDetail : orderDetailList) {
            if(!addOrderDetail(orderDetail)){
                return false;
            }
        }
        return true;
    }
    
    public static boolean addOrderDetail(OrderDetail orderDetail) throws ClassNotFoundException, SQLException{
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO orderdetail values(?,?,?,?)");
        pstm.setObject(1, orderDetail.getOrderId());
        pstm.setObject(2, orderDetail.getItemCode());
        pstm.setObject(3, orderDetail.getQty());
        pstm.setObject(4, orderDetail.getUnitPrice());
        return pstm.executeUpdate() > 0;                
    }
}
