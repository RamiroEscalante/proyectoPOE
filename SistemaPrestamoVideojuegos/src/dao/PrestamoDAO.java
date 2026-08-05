/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import dao.VideojuegoDAO;
import dao.ClienteDAO;

import modelo.Prestamo;
import modelo.Cliente;
import modelo.Videojuego;
import modelo.ConsolaVideojuego;
import modelo.EstadoVideojuego;

import conexion.ConexionBD;
import java.time.LocalDate;

import servicio.VideojuegoService;


/**
 *
 * @author usr
 */
public class PrestamoDAO {
    
    Cliente clienteSeleccionado; 
    Videojuego videojuegoSeleccionado;
    VideojuegoDAO videojuegoDAO;
    
    public PrestamoDAO(){
        videojuegoDAO = new VideojuegoDAO();
    }
    
    public boolean guardar(Prestamo prestamo){
        String sql = """
                     INSERT INTO prestamos
                     (id_cliente, id_videojuego, fecha_prestamo, fecha_devolucion, entregado)
                     VALUES (?, ?, ?, ?, ?)
                     """;
        
        try(
                Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
            ){
            
            sentencia.setInt(1, prestamo.getCliente().getId());
            sentencia.setInt(2, prestamo.getVideojuego().getId());
            sentencia.setDate(3, java.sql.Date.valueOf(prestamo.getFechaPrestamo()));
            sentencia.setDate(4, java.sql.Date.valueOf(prestamo.getFechaDevolucion()));
            sentencia.setBoolean(5, prestamo.isEstregado());
            
            int filasModificadas = sentencia.executeUpdate();
            
            prestamo.getVideojuego().marcarComoPrestado();
            
            videojuegoSeleccionado = new Videojuego(prestamo.getVideojuego().getId(), prestamo.getVideojuego().getNombre(),
            prestamo.getVideojuego().getConsola(), prestamo.getVideojuego().getDisponibilidad());
            
            if(!videojuegoDAO.actualizar(videojuegoSeleccionado)) {
                System.out.println("FALLA EN ACTUALIZAR DISPONIBILIDAD VIDEOJUEGO");
            }
            
            videojuegoSeleccionado = null; 
            
            return filasModificadas > 0;
        }
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public List<Prestamo> listar(){
        List<Prestamo> prestamos = new ArrayList<>();
        
        String sql = """
                     SELECT
                     p.id,
                     c.id
                     c.nombre,
                     c.edad,
                     v.id,
                     v.nombre,
                     v.consola,
                     v.disponibilidad,
                     p.fecha_prestamo,
                     p.fecha_devolucion,
                     p.entregado
                     FROM prestamos AS p
                     INNER clientes AS c, videojuegos AS v
                     ON p.id_cliente = c.id AND p.id_videojuego = v.id
                     WHERE p.entregado = false
                     """;
        
        try(
                Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                ResultSet resultado = sentencia.executeQuery();
            ){
            
            while(resultado.next()){
                Prestamo prestamo = new Prestamo(
                resultado.getInt("p.id"),
                clienteSeleccionado = new Cliente(resultado.getInt("c.id"), resultado.getString("c.nombre"), resultado.getInt("c.edad")),
                videojuegoSeleccionado = new Videojuego(resultado.getInt("v.id"), resultado.getString("v.nombre"),
                ConsolaVideojuego.valueOf(resultado.getString("v.consola")), EstadoVideojuego.valueOf(resultado.getString("v.disponibilidad"))),
                resultado.getDate("p.fecha_prestamo").toLocalDate(),
                resultado.getDate("p.fecha_devolucion").toLocalDate(),
                resultado.getBoolean("p.entregado")
                );
                
               prestamos.add(prestamo);
               clienteSeleccionado = null;
               videojuegoSeleccionado = null;
            }
        }
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return prestamos;
    }
    
    public int contarPrestamosActivos(int idCliente){
        String sql = """
                     SELECT 
                     COUNT(*)
                     FROM prestamos AS p
                     Where p.id_cliente = ?
                     """;
        
        int total = 0;
        
        try(
                Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                ResultSet resultado = sentencia.executeQuery();
            ){
            
            sentencia.setInt(1, idCliente);
            
            if(resultado.next()){
                total = resultado.getInt(1);
            }
        }
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return total;
    }
    
    public boolean marcarComoEntregado(Prestamo prestamo){
        String sql = """
                     UPDATE prestamos
                     SET entregado = ?
                     WHERE id = ?
                     """;
        
        try(
                Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
            ){
            
            sentencia.setBoolean(1, true);
            sentencia.setInt(2, prestamo.getId());
            
            videojuegoSeleccionado = new Videojuego(
                    prestamo.getVideojuego().getId(),
                    prestamo.getVideojuego().getNombre(),
                    prestamo.getVideojuego().getConsola(), 
                    prestamo.getVideojuego().getDisponibilidad()
            );
            
            videojuegoSeleccionado.marcarComoDisponible();
            
            if(!videojuegoDAO.actualizar(videojuegoSeleccionado)){
                System.out.println("Error: seccion videojuego dao");
                return false;
            }
            
            int filasSeleccionadas = sentencia.executeUpdate();
            
            return filasSeleccionadas > 0; 
        }
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
