/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.List;
import servicio.ClienteService;
import modelo.Cliente;

/**
 *
 * @author usr
 */
public class ClienteController {
    
    private ClienteService servicio;
    
    public ClienteController(){
        servicio = new ClienteService();
    }
    
    public String guardar(Cliente cliente){
        return servicio.guardar(cliente);
    }
    
    public List<Cliente> obtener(){
        return servicio.obtener();
    }
    
    public String actualizar(Cliente cliente){
        return servicio.actualizar(cliente);
    }
    
    public String eliminar(Cliente cliente){
        return servicio.eliminar(cliente);
    }
}
