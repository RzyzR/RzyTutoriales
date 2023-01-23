package CRUD_MVC.Main;

import CRUD_MVC.Controlador.ControladorPersona;
import CRUD_MVC.Modelo.ConsultasPersona;
import CRUD_MVC.Modelo.Persona;
import CRUD_MVC.Vista.Vista;

/**
 *
 * @author Rzy
 */
public class Main {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Persona persona = new Persona();
        ConsultasPersona consultasPersona = new ConsultasPersona();
        ControladorPersona controladorPersona = new ControladorPersona(vista, persona, consultasPersona);
        controladorPersona.iniciar();
        vista.setVisible(true);
    }
}
