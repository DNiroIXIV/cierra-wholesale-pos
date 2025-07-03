/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package thogakade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Nirodha
 */
public class Thogakade {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //String SQL = "INSERT INTO customer values('C011', 'Kamal', 'Colombo', 75000.0)";
            //String SQL = "UPDATE customer SET salary = salary*1.1";
            //String SQL = "DELETE FROM customer WHERE id = 'C011'";
            String SQL = "SELECT * FROM customer";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/thogakade", "root", "12345");
            Statement stm = connection.createStatement();        
            ResultSet rst = stm.executeQuery(SQL);
            
//            rst.next();
//            String id = rst.getString("id");
//            String name = rst.getString("name");
//            String address = rst.getString("address");
//            double salary = rst.getDouble("salary");
//            System.out.println(id+"\t"+name+"\t"+address+"\t"+salary);

            while (rst.next()) {                
                String id = rst.getString("id");
                String name = rst.getString("name");
                String address = rst.getString("address");
                double salary = rst.getDouble("salary");
                System.out.println(id+"\t"+name+"\t"+address+"\t"+salary);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver software not found");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }    
}
