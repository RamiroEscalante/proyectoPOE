/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;
import dao.ClienteDAO;
import modelo.Cliente;
import java.util.List;
/**
 *
 * @author usr
 */
public class ClienteService {
    
    
    ClienteDAO dao;
    
    public ClienteService(){
        dao = new ClienteDAO();
    }
    
    public String guardar(Cliente cliente){
        if(cliente == null) return "El cliente esta vacio";
        
        
        if(cliente.getNombre() == null) return "El nombre del cliente esta vacio";
        
        String nombre = cliente.getNombre().trim();
        
        if(cliente.getEdad() < 12 || cliente.getEdad() > 70) return "La edad es invalidad para el sistma";
        
        if(nombre.isBlank()) return "El nombre esta vacio";
        
        if(nombre.length() < 3) return "EL nombre debe contener almenos 3 caracteres";
        
        cliente.setNombre(nombre);
        
        if(!dao.guardar(cliente)) return "Error al guardar cliente";
        
        return "Exito al guardar cliente";
    }
    
    public List<Cliente> obtener(){
        List<Cliente> clientes = dao.listar();
        return clientes; 
    }
    
    public String actualizar(Cliente cliente){
        if(cliente == null) return "El cliente esta vacio";
        
        if(cliente.getNombre() == null) return "No existe el nombre";
        
        String nombre = cliente.getNombre().trim();
        
        if(nombre.isBlank()) return "El nombre esta vacio";
        
        if(nombre.length() < 3) return "El nombre debe tener almenos 3 caracteres";
        
        if(cliente.getId() <= 0) return "Id invalido";
        
        if(cliente.getEdad() < 12 || cliente.getEdad() > 70) return "Edad invalida";
        
        cliente.setNombre(nombre);
        
        if(!dao.actualizar(cliente)) return "Error al actualizar cliente";
        
        return "Exito al actualizar cliente";
    }
    
    public String eliminar(Cliente cliente){
        if(cliente == null) return "El cliente esta vacio";
        
        if(cliente.getId() <= 0) return "No tiene un id valido";
        
        if(!dao.eliminar(cliente)) return "Error al eliminar cliente " + cliente.getNombre();
        
        return "Exito al eliminar cliente " + cliente.getNombre();
    }
    
}
