/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author usr
 */
public class Videojuego extends Entidad{
    
    private String nombre;
    private ConsolaVideojuego consola;
    private EstadoVideojuego disponibilidad;
    
    //nuevo juego
    public Videojuego(String nombre, ConsolaVideojuego consola){
        this.nombre = nombre;
        this.consola = consola; 
        this.disponibilidad = EstadoVideojuego.DISPONIBLE;
    }
    
    //videojuego ya existente 
    public Videojuego(int id, String nombre, ConsolaVideojuego consola, EstadoVideojuego disponibilidad){
        this.id = id;
        this.nombre = nombre;
        this.consola = consola; 
        this.disponibilidad = disponibilidad;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setNombre(String nuevo_nombre){
        this.nombre = nuevo_nombre;
    }
    
    public ConsolaVideojuego getConsola(){
        return this.consola;
    }
    
    public void setConsola(ConsolaVideojuego nuevo_nombre_consola){
        this.consola = nuevo_nombre_consola;
    }
    
    public EstadoVideojuego getDisponibilidad(){
        return this.disponibilidad;
    }
    
    public boolean estaDisponible(){
        return disponibilidad == EstadoVideojuego.DISPONIBLE;
    }
    
    public void marcarComoPrestado(){
        this.disponibilidad = EstadoVideojuego.PRESTADO;
    }
    
    public void marcarComoDisponible(){
        this.disponibilidad = EstadoVideojuego.DISPONIBLE;
    }
    
    public void marcarComoDañado(){
        this.disponibilidad = EstadoVideojuego.DAÑADO;
    }
    
    @Override
    public String getDescripcion(){
        return nombre + " - " + consola;
    }
    
    @Override
    public String toString(){
        return getDescripcion();
    }
}
