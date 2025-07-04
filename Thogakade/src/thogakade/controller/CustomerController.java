/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thogakade.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import thogakade.db.DBConnection;
import thogakade.model.Customer;

/**
 *
 * @author Nirodha
 */
public class CustomerController {
    public static boolean addCustomer(Customer customer) throws ClassNotFoundException, SQLException{
//        String SQL = "INSERT INTO customer values('"+
//                customer.getId()+"','"+
//                customer.getName()+"','"+
//                customer.getAddress()+"',"+
//                customer.getSalary()+")";

        String SQL = "INSERT INTO customer values(?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
//        Statement stm = connection.createStatement();
        PreparedStatement stm = connection.prepareStatement(SQL);
        stm.setObject(1, customer.getId());
        stm.setObject(2, customer.getName());
        stm.setObject(3, customer.getAddress());
        stm.setObject(4, customer.getSalary());
        int res = stm.executeUpdate();
        return res > 0;
    }
    
    public static Customer searchCustomer(String id) throws ClassNotFoundException, SQLException{
        String SQL = "SELECT * FROM customer WHERE id = '"+id+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet res = stm.executeQuery(SQL);
        if(res.next()){
            return new Customer(
                    res.getString("id"), 
                    res.getString("name"), 
                    res.getString("address"), 
                    res.getDouble("salary"));
        }
        return null;
    }
    
    public static boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException{
        String SQL = "DELETE FROM customer WHERE id = '"+id+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        int res = stm.executeUpdate(SQL);
        return res > 0;
    }
    
    public static boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException{
//        String SQL = "UPDATE customer SET name = '"+
//                customer.getName()+"', address = '"+
//                customer.getAddress()+"', salary = "+
//                customer.getSalary()+"WHERE id = '"+
//                customer.getId()+"'";
        String SQL = "UPDATE customer SET name=?, address=?, salary=? WHERE id=?";
        Connection connection = DBConnection.getInstance().getConnection();
//        Statement stm = connection.createStatement();
        PreparedStatement stm = connection.prepareStatement(SQL);
        stm.setObject(1, customer.getName());
        stm.setObject(2, customer.getAddress());
        stm.setObject(3, customer.getSalary());
        stm.setObject(4, customer.getId());
        int res = stm.executeUpdate();
        return res > 0;                
    }
    
    public static ArrayList<Customer> getAllCustomers() throws ClassNotFoundException, SQLException{
        ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery(
                "SELECT * FROM customer");
        ArrayList<Customer> customerList = new ArrayList<>();
        while (rst.next()) {           
            customerList.add(new Customer(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address"),
                    rst.getDouble("salary")
            ));
        }
        return customerList;
    }
}
