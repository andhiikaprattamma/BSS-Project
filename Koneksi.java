package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static Connection mysqlconfig;

    public static Connection configDB() {
        try {
            // URL database ke bs_station
            String url = "jdbc:mysql://localhost:3306/bs_station?useSSL=false&serverTimezone=UTC"; 
            String user = "root"; 
            String pass = ""; 
            
            // Driver MySQL
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            mysqlconfig = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.err.println("Koneksi gagal: " + e.getMessage());
            // Mengembalikan null jika koneksi gagal
            return null;
        }
        return mysqlconfig;
    }    
}