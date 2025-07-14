/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cierra.lk.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import cierra.lk.db.DBConnection;
import cierra.lk.model.Item;
import cierra.lk.model.OrderDetail;

/**
 *
 * @author Nirodha
 */
public class ItemController {
    public static boolean addItem(Item item) 
            throws ClassNotFoundException, SQLException{
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO item values(?,?,?,?)");
        pstm.setObject(1, item.getCode());
        pstm.setObject(2, item.getDescription());
        pstm.setObject(3, item.getUnitPrice());
        pstm.setObject(4, item.getQtyOnHand());                
        return pstm.executeUpdate() > 0;
    }
    
    public static Item searchItemByCode(String itemCode) 
            throws ClassNotFoundException, SQLException{
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("SELECT * FROM item WHERE code = ?");
        pstm.setObject(1, itemCode);
        ResultSet res = pstm.executeQuery();
        if(res.next()){
            return new Item(
                    res.getString("code"), 
                    res.getString("description"),
                    res.getDouble("unitPrice"),
                    res.getInt("qtyOnHand"));
        }
        return null;
    }
    
    public static boolean deleteItem(String itemCode) 
            throws ClassNotFoundException, SQLException{
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("DELETE FROM item WHERE code = ?");
        pstm.setObject(1, itemCode);
        return pstm.executeUpdate() > 0;
    }
    
    public static boolean updateItem(Item item) 
            throws ClassNotFoundException, SQLException{
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("UPDATE item SET description=?, "
                        + "unitPrice=?, qtyOnHand=? WHERE code=?");
        pstm.setObject(1, item.getDescription());
        pstm.setObject(2, item.getUnitPrice());
        pstm.setObject(3, item.getQtyOnHand());
        pstm.setObject(4, item.getCode());
        return pstm.executeUpdate() > 0;
    }
    
    public static ArrayList<Item> getAllItems() 
            throws ClassNotFoundException, SQLException{
        ResultSet res = DBConnection.getInstance().getConnection().
                createStatement().executeQuery("SELECT * FROM item");
        ArrayList<Item> itemList = new ArrayList<>();
        while(res.next()){
            itemList.add(new Item(
                    res.getString("code"),
                    res.getString("description"),
                    res.getDouble("unitPrice"),
                    res.getInt("qtyOnhand")));
        }
        return itemList;
    }
    
    public static ArrayList<String> getAllItemCodes() 
            throws ClassNotFoundException, SQLException{
        ResultSet rst = DBConnection.getInstance().getConnection()
                .createStatement().executeQuery("SELECT code FROM item");
        ArrayList<String> itemCodeList = new ArrayList<>();
        while(rst.next()){
            itemCodeList.add(rst.getString("code"));
        }
        return itemCodeList;
    }
    
    public static boolean updateStock(ArrayList<OrderDetail> orderDetailsList) throws ClassNotFoundException, SQLException{
        for (OrderDetail orderDetail : orderDetailsList) {
            if(!updateStock(orderDetail)){
                return false;
            }           
        }
        return true;
    }
    
    public static boolean updateStock(OrderDetail orderDetail) throws ClassNotFoundException, SQLException{
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(
                "UPDATE item SET qtyOnHand = qtyOnHand - ? WHERE code = ?");
        pstm.setObject(1, orderDetail.getQty());
        pstm.setObject(2, orderDetail.getItemCode());
        return pstm.executeUpdate() > 0;
    }
}
