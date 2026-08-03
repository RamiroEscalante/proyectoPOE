/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

/**
 *
 * @author usr
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = 
            "jdbc:mysql://localhost:3306/sistema_videojuegos";
    
    private static final String USUARIO = "root";
    
    private static final String CONTRASENA = "";
    
    public static Connection obtenerConexion() throws SQLException{
        
        return DriverManager.getConnection(
                URL,
                USUARIO,
                CONTRASENA
        );
    }
    
   /* public static void main(String[] args) {

    try (Connection conexion = obtenerConexion()) {

        System.out.println("Conexión exitosa.");

    } catch (SQLException e) {

        System.out.println(e.getMessage());

    }

}*/
}
