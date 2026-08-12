/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author usr
 */
public class Cliente extends Entidad {
    
    private String nombre;
    private int edad;
    
    public Cliente(String nombre, int edad){
        this.nombre = nombre;
        this.edad = edad;
    }
    
    public Cliente(int id, String nombre, int edad){
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setNombre(String nuevo_nombre){
        this.nombre = nuevo_nombre;
    }
    
    public int getEdad(){
        return this.edad;
    }
    
    public void setEdad(int nueva_edad){
        this.edad = nueva_edad;
    }
    
    @Override
    public String getDescripcion(){
        return nombre + " - " + edad;
    }
    
    @Override
    public String toString(){
        return getDescripcion();
    }
}
