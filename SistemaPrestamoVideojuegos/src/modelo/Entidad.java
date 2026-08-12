/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author usr
 */
public abstract class Entidad {
    protected int id;
    
    public int getId(){
        return this.id;
    }
    
    public abstract String getDescripcion();
    
}
