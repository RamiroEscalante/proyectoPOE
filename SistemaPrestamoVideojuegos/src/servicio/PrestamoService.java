/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

/**
 *
 * @author usr
 */

import dao.PrestamoDAO;
import java.util.List;
import modelo.Prestamo;

public class PrestamoService {
    PrestamoDAO dao;

    public PrestamoService() {
        dao = new PrestamoDAO();
    }
    
    
    public String guardar(Prestamo prestamo){
        
        if(prestamo == null) return "No existe el prestamo";
        
        if(prestamo.getVideojuego() == null) return "No exite ningun videojuego";
        
        if(prestamo.getCliente() == null) return "No existe ningun cliente";
        
        if(!prestamo.getVideojuego().estaDisponible()) return "EL videojuego no esta disponible";
        
        if(dao.contarPrestamosActivos(prestamo.getCliente().getId()) == 3) return "Usuario cuenta con el maximo de prestamos posibles";
        
        if(!dao.guardar(prestamo)) return "Error al guardar prestamo";
        
        return "Exiito al guardar prestamo";
    }
    
    public List<Prestamo> obtner(){
        
        List<Prestamo> prestamos = dao.listar();
        
        return prestamos;
    }
   
    public String entregar(Prestamo prestamo){
        if(prestamo == null) return "No existe el prestamo";
        
        if(prestamo.getCliente() == null) return "No exixte el cliente";
        
        if(prestamo.getVideojuego() == null) return "No exixte el videojuego";
        
        if(prestamo.getId() <= 0) return "Id del prestamo invalido";
        
        if(!dao.marcarComoEntregado(prestamo)) return "Error al marcar como etregado"; 
        
        return "Exito al marcar como entregado";
    }
}
