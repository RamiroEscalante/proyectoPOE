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

import modelo.Prestamo;
import modelo.Cliente;
import modelo.Videojuego;
import modelo.ConsolaVideojuego;
import modelo.EstadoVideojuego;

import conexion.ConexionBD;


/**
 *
 * @author usr
 */
public class PrestamoDAO {
    
    
    public boolean guardar(Prestamo prestamo){
        String sqlPrestamo = """
                     INSERT INTO prestamos
                     (id_cliente, id_videojuego, fecha_prestamo, fecha_devolucion, entregado)
                     VALUES (?, ?, ?, ?, ?)
                     """;
        
        String sqlVideojuego = """
                                UPDATE videojuegos
                                SET disponibilidad = ?
                                WHERE id = ?
                                AND disponibilidad = ?
                                """;
        
        Connection conexion = null;
        
        try
        {
            conexion = ConexionBD.obtenerConexion();
            conexion.setAutoCommit(false);
            
            try(
                    PreparedStatement sentenciaPrestamo = conexion.prepareStatement(sqlPrestamo);
                    PreparedStatement sentenciaVideojuego = conexion.prepareStatement(sqlVideojuego);
                ){
                
                sentenciaPrestamo.setInt(1, prestamo.getCliente().getId());
                sentenciaPrestamo.setInt(2, prestamo.getVideojuego().getId());
                sentenciaPrestamo.setDate(3, java.sql.Date.valueOf(prestamo.getFechaPrestamo()));
                sentenciaPrestamo.setDate(4, java.sql.Date.valueOf(prestamo.getFechaDevolucion()));
                sentenciaPrestamo.setBoolean(5, prestamo.isEntregado());
                
                int prestamoInsertado = sentenciaPrestamo.executeUpdate();
                
                sentenciaVideojuego.setString(1, EstadoVideojuego.PRESTADO.name());
                sentenciaVideojuego.setInt(2, prestamo.getVideojuego().getId());
                sentenciaVideojuego.setString(3, EstadoVideojuego.DISPONIBLE.name());
                
                int videojuegoActualizado = sentenciaVideojuego.executeUpdate();
                
                if(prestamoInsertado == 1 && videojuegoActualizado == 1){
                    conexion.commit();
                    return true; 
                }
                
                conexion.rollback();
                return false;
            }
        }catch (SQLException e){
            if(conexion != null){
                try{
                    conexion.rollback();
                }
                catch(SQLException rollbackError){
                    System.out.println("Error rollback: " + rollbackError.getMessage());
                }
            }
            
            System.out.println("Error al registrar prestamo " + e.getMessage());
            return false; 
        }
        finally{
            if(conexion != null){
                try{
                    conexion.setAutoCommit(true);
                    conexion.close();
                }
                catch(SQLException e){
                    System.out.println("Error al cerrar conexion: " +e.getMessage());
                }
            }
        }
                                
    }
    
    public List<Prestamo> listar(){
        List<Prestamo> prestamos = new ArrayList<>();
        
        String sql = """
                     SELECT 
                        p.id AS prestamo_id,
                     
                        c.id AS cliente_id,
                        c.nombre AS cliente_nombre,
                        c.edad AS cliente_edad,
                     
                        v.id AS videojuego_id,
                        v.nombre AS videojuego_nombre,
                        v.consola AS videojuego_consola,
                        v.disponibilidad AS videojuego_disponibilidad,
                        
                        p.fecha_prestamo,
                        p.fecha_devolucion,
                        p.entregado
                     
                     FROM prestamos AS p
                     INNER JOIN clientes AS c
                     ON p.id_cliente = c.id
                     INNER JOIN videojuegos AS v
                     ON p.id_videojuego = v.id
                     
                     WHERE p.entregado = FALSE
                     """;
        
        try(
                Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                ResultSet resultado = sentencia.executeQuery();
            ){
            
            while(resultado.next()){
                
                Cliente cliente = new Cliente(resultado.getInt("cliente_id"), resultado.getString("cliente_nombre"), resultado.getInt("cliente_edad"));
                Videojuego videojuego = new Videojuego(resultado.getInt("videojuego_id"), resultado.getString("videojuego_nombre"), 
                    ConsolaVideojuego.valueOf(resultado.getString("videojuego_consola")), EstadoVideojuego.valueOf(resultado.getString("videojuego_disponibilidad")));
                Prestamo prestamo = new Prestamo(
                resultado.getInt("prestamo_id"),
                cliente,
                videojuego,
                resultado.getDate("fecha_prestamo").toLocalDate(),
                resultado.getDate("fecha_devolucion").toLocalDate(),
                resultado.getBoolean("p.entregado")
                );
                
               prestamos.add(prestamo);
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
                     FROM prestamos 
                     Where id_cliente = ?
                     AND entregado = FALSE
                     """;
        
        try(
                Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
            ){
            
            sentencia.setInt(1, idCliente);
            
            try(ResultSet resultado = sentencia.executeQuery()){
                if(resultado.next()){
                    return resultado.getInt(1);
                }
            }
        }
        catch(SQLException e){
                System.out.println("Error al contar preestamos: " + e.getMessage());
        }
        
        return 0;
    }
    
    public boolean marcarComoEntregado(Prestamo prestamo){
        String sqlPrestamo  = """
                              UPDATE prestamos
                              SET entregado = ?
                              WHERE id = ?
                              AND entregado = FALSE
                              """;
        
        String sqlVideojuego = """
                               UPDATE videojuegos
                               SET disponibilidad = ?
                               WHERE id = ?
                               AND disponibilidad = ?
                               """;
        
        Connection conexion = null;
        
        try{
            conexion = ConexionBD.obtenerConexion();
            conexion.setAutoCommit(false);
            
            try(PreparedStatement sentenciaPrestamo = conexion.prepareStatement(sqlPrestamo);
                PreparedStatement sentenciaVideojuego = conexion.prepareStatement(sqlVideojuego))
            {
                sentenciaPrestamo.setBoolean(1, true);
                sentenciaPrestamo.setInt(2, prestamo.getId());

                int prestamoEntregado = sentenciaPrestamo.executeUpdate();

                sentenciaVideojuego.setString(1, EstadoVideojuego.DISPONIBLE.name());
                sentenciaVideojuego.setInt(2, prestamo.getVideojuego().getId());
                sentenciaVideojuego.setString(3, EstadoVideojuego.PRESTADO.name());

                int videojeugoActualizado = sentenciaVideojuego.executeUpdate();

                if(prestamoEntregado == 1 && videojeugoActualizado == 1){
                    conexion.commit();
                    return true;
                }

                conexion.rollback();
                return false;
            }
        }
        catch(SQLException e){
           if(conexion != null){
               try{
                   conexion.rollback();
               }catch(SQLException rollbackError){
                   System.out.println("error en rollback: " + rollbackError.getMessage());
               }
           }
            System.out.println("Error al actualizar prestamo: " + e.getMessage());
            return false;
        }
        finally{
            if(conexion != null){
                try{
                    conexion.setAutoCommit(true);
                    conexion.close();
                }catch(SQLException e){
                    System.out.println("Error al cerrar conexion: " + e.getMessage());
                }
            }
        }
    }
}
