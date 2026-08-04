/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import modelo.Cliente;
import modelo.Videojuego;
/**
 *
 * @author usr
 */
public class Prestamo {
    private int id;
    private Cliente cliente;
    private Videojuego videojuego;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private boolean entregado; 
    
    public Prestamo(Cliente cliente, Videojuego videojuego, LocalDate fechaPrestamo, LocalDate fechaDevolucion ){
        this.cliente = cliente;
        this.videojuego = videojuego;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.entregado = false; 
    }
    
    public Prestamo(int id, Cliente cliente, Videojuego videojuego, LocalDate fechaPrestamo, LocalDate fechaDevolucion, boolean entregado){
        this.id = id;
        this.cliente = cliente;
        this.videojuego = videojuego;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.entregado = entregado; 
    }
    
    public int getId(){
        return this.id;
    }
    
    public Cliente getCliente(){
        return this.cliente;
    }
    
    
    public Videojuego getVideojuego(){
        return this.videojuego;
    }
    
    public LocalDate getFechaPrestamo(){
        return this.fechaPrestamo;
    }
    
    public LocalDate getFechaDevolucion(){
        return this.fechaDevolucion;
    }
    
    
    public boolean isEstregado(){
        return this.entregado;
    }
    
    public boolean estaActivo(){
        return !this.entregado;
    }
    
    public void marcarComoEntregado(){
        this.entregado = true;
    }
}
