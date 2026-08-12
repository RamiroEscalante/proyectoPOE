/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import servicio.PrestamoService;
import modelo.Prestamo;
import java.util.List;

/**
 *
 * @author usr
 */
public class PrestamoController {
    private PrestamoService servicio;
    
    public PrestamoController(){
        servicio = new PrestamoService(); 
    }
    
   public String guardar(Prestamo prestamo){
       return servicio.guardar(prestamo);
   }
   
   public List<Prestamo> obtner(){
       return servicio.obtener();
   }
   
   public String entregar(Prestamo prestamo){
       return servicio.entregar(prestamo);
   }
}
