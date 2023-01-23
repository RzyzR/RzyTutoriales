package MVC.controlador;

import MVC.modelo.Modelo;
import MVC.vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Randy
 */
public class Controlador implements ActionListener{
    private Vista vista;
    private Modelo modelo;

    public Controlador() {
    }

    
    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        vista.btnResultado.addActionListener(this);
    }

    public void iniciar(){
        vista.setTitle("MVC para sumar");
        vista.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        modelo.setNum1(Integer.parseInt(vista.txtNum1.getText()));
        modelo.setNum2(Integer.parseInt(vista.txtNum2.getText()));
        
        modelo.sumar();
        
        vista.txtResultado.setText(String.valueOf(modelo.getResult()));
        
    }
}
