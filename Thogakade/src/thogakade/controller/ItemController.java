/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thogakade.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import thogakade.db.DBConnection;
import thogakade.model.Item;

/**
 *
 * @author Nirodha
 */
public class ItemController {
    public static boolean addItem(Item item) throws ClassNotFoundException, SQLException{
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(
                "INSERT INTO item values(?,?,?,?)");
        pstm.setObject(1, item.getCode());
        pstm.setObject(2, item.getDescription());
        pstm.setObject(3, item.getUnitPrice());
        pstm.setObject(4, item.getQtyOnHand());                
        return pstm.executeUpdate() > 0;
    }
    
    public static Item searchItemByCode(String itemCode) throws ClassNotFoundException, SQLException{
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(
                "SELECT * FROM item WHERE code = ?");
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
    
    public static boolean deleteItem(String itemCode) throws ClassNotFoundException, SQLException{
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(
                "DELETE FROM item WHERE code = ?");
        pstm.setObject(1, itemCode);
        return pstm.executeUpdate() > 0;
    }
    
    public static boolean updateItem(Item item) throws ClassNotFoundException, SQLException{
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(
                "UPDATE item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
        pstm.setObject(1, item.getDescription());
        pstm.setObject(2, item.getUnitPrice());
        pstm.setObject(3, item.getQtyOnHand());
        pstm.setObject(4, item.getCode());
        return pstm.executeUpdate() > 0;
    }
}
