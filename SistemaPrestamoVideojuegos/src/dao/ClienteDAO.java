/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import java.sql.SQLException;
import conexion.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author usr
 */
public class ClienteDAO {
    
    public boolean guardar(Cliente cliente){
        String sql = """
                     INSERT INTO clientes
                     (nombre, edad)
                     VALUES (?,?)
                     """;
        
        try(
                Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
            ){
            
            sentencia.setString(1, cliente.getNombre().trim());
            sentencia.setInt(2, cliente.getEdad());
            
            int filasModificadas = sentencia.executeUpdate();
            
            return filasModificadas > 0;
        }
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public List<Cliente> listar(){
        List<Cliente> clientes = new ArrayList<>();
        
        String sql = """
                     SELECT id, nombre, edad
                     FROM clientes
                     """;
        
        try(
                Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                ResultSet resultado = sentencia.executeQuery();
            )
        {
            
            while(resultado.next()){
                Cliente cliente = new Cliente(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getInt("edad")
                );
                
                clientes.add(cliente);
            }
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return clientes; 
    }
    
    public boolean actualizar(Cliente cliente){
        String sql = """
                     UPDATE clientes 
                     SET nombre = ?, edad = ? 
                     WHERE id = ?
                     """;
        
        try(
                Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
            ){
            
            sentencia.setString(1, cliente.getNombre().trim());
            sentencia.setInt(2, cliente.getEdad());
            sentencia.setInt(3, cliente.getId());
            
            int filasModificadas = sentencia.executeUpdate();
            
            return filasModificadas > 0;
        }
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminar(Cliente cliente){
        String sql = """
                     DELETE FROM clientes
                     WHERE id = ?
                     """;
        
        try(
                Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
            ){
            
            sentencia.setInt(1, cliente.getId());
            
            int filasModificadas = sentencia.executeUpdate();
            
            return filasModificadas > 0;
        }
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
