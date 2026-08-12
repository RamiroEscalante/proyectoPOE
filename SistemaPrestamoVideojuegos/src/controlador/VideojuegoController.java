/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.List;
import modelo.Videojuego;
import servicio.VideojuegoService;

/**
 *
 * @author usr
 */
public class VideojuegoController {
    
    private VideojuegoService servicio;
    
    public VideojuegoController(){
        servicio = new VideojuegoService();
    }
    
    public String guardar(Videojuego videojuego){
        return servicio.guardar(videojuego);
    }
    
    public List<Videojuego> obtener(){
        return servicio.obtener();
    }
    
    public String actualizar(Videojuego videojuego){
        return servicio.actualizar(videojuego);
    }
    
    public String eliminar(Videojuego videojuego){
        return servicio.eliminar(videojuego);
    }
    
}
