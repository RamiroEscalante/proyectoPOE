/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import modelo.ConsolaVideojuego;
import modelo.Videojuego;
import vista.VentanaPrincipal;
import vista.VentanaVideojuegos;
import dao.VideojuegoDAO;
import java.util.ArrayList;
import java.util.List;
import vista.VentanaCliente;
/**
 *
 * @author usr
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Videojuego mariokart = new Videojuego(1, "MarioKart 8 deluxe", ConsolaVideojuego.NINTENDO_SWITCH);
//        
//        System.out.println(mariokart.getNombre() + " Esta: " + mariokart.getDisponibilidad() + " y es de " + mariokart.getConsola());
          
          VentanaPrincipal principal = new VentanaPrincipal();
          principal.setVisible(true);

           //VentanaVideojuegos videojuegos = new VentanaVideojuegos();
          // videojuegos.setVisible(true);
          
          
          //sVentanaCliente clientes = new VentanaCliente();
          //clientes.setVisible(true);
           
           
    }
}
