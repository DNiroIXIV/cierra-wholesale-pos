package thogakade;

import java.sql.*;

public class ThogaKade {

    public static void main(String[] args) {
        try {
            String SQL = "insert into customer values('C111', 'Niroth', 'Panadura', 60000)";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/thogakade", "root", "12345");
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver software not found");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }    
}
