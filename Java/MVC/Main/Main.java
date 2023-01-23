package MVC.main;

import MVC.controlador.Controlador;
import MVC.modelo.Modelo;
import MVC.vista.Vista;

/**
 *
 * @author Randy
 */
public class Main {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);
        
        controlador.iniciar();
        vista.setVisible(true); 
    }
}
