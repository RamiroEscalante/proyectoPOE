/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

/**
 *
 * @author usr
 */

import modelo.Videojuego;
import dao.VideojuegoDAO;
import java.util.ArrayList;
import java.util.List;

public class VideojuegoService {
    
    VideojuegoDAO dao; 
    
    public VideojuegoService(){
        dao = new VideojuegoDAO();
    }
    
    
    public String guardar(Videojuego videojuego){
        
        if(videojuego == null ) return "Videiojuego esta en NULL";
        
        if(videojuego.getNombre() == null) return "El nombre es NULL";
        
        String nombre = videojuego.getNombre();
        
        if(nombre.isBlank()) return "El nombre esta Vacio";
       
        
        if(nombre.length() < 3) return "El nombre debe tener almenos 3 caracteres";
        
        videojuego.setNombre(nombre);
        
        if(!dao.guardar(videojuego)) return "Error al guardar";
        
        return "Exito al guardar";
    }
    
    public List<Videojuego> obtener(){
        List<Videojuego> videojuegos = dao.listar();
        
        if(videojuegos.isEmpty()) return null;
        
        return videojuegos;
    }
    
    public String actualizar(Videojuego videojuego){
        if (videojuego == null) return "Videojuego esta e NULL";
        
        if(videojuego.getNombre() == null) return "Nombre esta en NULL";
        
        String nombre = videojuego.getNombre();
        
        if(nombre.isBlank()) return "El nombre esta vacio";
        
        if(nombre.length() < 3) return "El nombre debe tener almenos 3 caracteres";
        
        if(!dao.actualizar(videojuego)) return "Error al actualizar";
        
        return "Exito al actualizar";
        
    }
    
    public String eliminar(Videojuego videojuego){
        
        if(videojuego == null) return "El videojuego esta vacio";
        
        if(videojuego.getId() <= 0) return "Id invalido";
        
        if(!dao.eliminar(videojuego)) return "Error al eleminar videojuego";
        
        return "Exito al eliminar";
    }
}