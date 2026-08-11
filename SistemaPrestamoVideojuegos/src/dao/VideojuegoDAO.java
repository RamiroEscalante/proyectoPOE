/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author usr
 */

import modelo.Videojuego;
import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.ConsolaVideojuego;
import modelo.EstadoVideojuego;

public class VideojuegoDAO {
    
    
    public boolean guardar(Videojuego videojuego){
        String sql = """
                     INSERT INTO videojuegos
                     (nombre, consola, disponibilidad)
                     values(?,?,?)
                     """;
        try(    
                Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
        ){
            sentencia.setString(1, videojuego.getNombre().trim());
            sentencia.setString(2, videojuego.getConsola().name());
            sentencia.setString(3, videojuego.getDisponibilidad().name());
            
            int filasAfectadas = sentencia.executeUpdate();
            
            return filasAfectadas > 0;
        }catch(SQLException e){
            System.out.println("Error al guardar videojuego: " + e.getMessage());
            return false;
        }
    }
    
    public List<Videojuego> listar(){
        
        List<Videojuego> videojuegos = new ArrayList<>();
        
        String sql = """
                     SELECT id, nombre, consola, disponibilidad
                     FROM videojuegos
                     WHERE disponibilidad = 'DISPONIBLE'
                     """;
        try(
                Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                ResultSet resultado = sentencia.executeQuery();
            ){
            
            while(resultado.next()){
                Videojuego videojuego = new Videojuego(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        ConsolaVideojuego.valueOf(resultado.getString("consola")),
                        EstadoVideojuego.valueOf(resultado.getString("disponibilidad"))
                );
                
                videojuegos.add(videojuego);
            }
            
        }
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return videojuegos;
    }
    
    public boolean actualizar(Videojuego videojuego){
        String sql = """
                     UPDATE videojuegos
                     SET nombre = ?, consola = ?, disponibilidad = ?
                     WHERE id = ?
                     """;
        
        try(
                Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(sql)
            ){
            
            sentencia.setString(1, videojuego.getNombre().trim());
            sentencia.setString(2, videojuego.getConsola().name());
            sentencia.setString(3, videojuego.getDisponibilidad().name());
            sentencia.setInt(4, videojuego.getId());
            
            int filasMoficadas = sentencia.executeUpdate();
            
            return filasMoficadas > 0; 
                    
        }
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        
    }
    
    
    public boolean eliminar(Videojuego videojuego){
        
        String sql = """
                     DELETE FROM videojuegos 
                     WHERE id = ? 
                     """;
        
        try(
                Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
            ){
            
            sentencia.setInt(1, videojuego.getId());
           
            
            int filaModificada = sentencia.executeUpdate();
            
            return filaModificada > 0;
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
