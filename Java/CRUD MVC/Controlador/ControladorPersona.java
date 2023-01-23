package CRUD_MVC.Controlador;

import CRUD_MVC.Modelo.ConsultasPersona;
import CRUD_MVC.Modelo.Persona;
import CRUD_MVC.Vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Rzy
 */
public class ControladorPersona implements ActionListener {

    private Vista vista;
    private Persona persona;
    private ConsultasPersona modelo;

    public ControladorPersona(Vista vista, Persona persona, ConsultasPersona modelo) {
        this.vista = vista;
        this.persona = persona;
        this.modelo = modelo;
        vista.btnGuardar.addActionListener(this);
        vista.btnBuscar.addActionListener(this);
        vista.btnActualizar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("CRUD MVC");
        vista.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            capturarCampos();
            if (modelo.guardar(persona)) {
                JOptionPane.showMessageDialog(null, "Guardado con exito");
                clearForm();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar");
            }
        } else if (e.getSource() == vista.btnBuscar) {
            capturarClave();
            
            if(modelo.buscar(persona)){
                vista.txtIdPersona.setText(String.valueOf(persona.getIdPersona()));
                vista.txtClave.setText(persona.getClave());
                vista.txtNombre.setText(persona.getNombre());
                vista.txtDomicilio.setText(persona.getDomicilio());
                vista.txtCelular.setText(persona.getCelular());
                vista.txtCorreoElectronico.setText(persona.getCorreo_electronico());
                vista.txtFechaNacimiento.setText(String.valueOf(persona.getFecha_nacimiento()));
                vista.cmbGenero.setSelectedItem(persona.getGenero());
               
            }else{
                JOptionPane.showMessageDialog(null, "NO existe ninguna persona con esa clave");
            }
            
        } else if (e.getSource() == vista.btnActualizar) {
            persona.setIdPersona(Integer.parseInt(vista.txtIdPersona.getText()));
            capturarCampos();
            
            if(modelo.actualizar(persona)){
                JOptionPane.showMessageDialog(null, "Modificado");
            }else{
                JOptionPane.showMessageDialog(null, "Error de modificacion");
            }
        } else if (e.getSource() == vista.btnEliminar) {
            persona.setIdPersona(Integer.parseInt(vista.txtIdPersona.getText()));
            if (modelo.eliminar(persona)) {
                JOptionPane.showMessageDialog(null, "Eiminado");
                clearForm();
            }else{
                JOptionPane.showMessageDialog(null, "Error de eliminacion");
            }
        }
    }

    public void clearForm() {
        vista.txtClave.setText("");
        vista.txtNombre.setText("");
        vista.txtDomicilio.setText("");
        vista.txtCelular.setText("");
        vista.txtCorreoElectronico.setText("");
        vista.txtFechaNacimiento.setText("");
        vista.cmbGenero.setSelectedItem("---");
    }

    private void capturarCampos() {
        persona.setClave(vista.txtClave.getText());
        persona.setNombre(vista.txtNombre.getText());
        persona.setDomicilio(vista.txtDomicilio.getText());
        persona.setCelular(vista.txtCelular.getText());
        persona.setCorreo_electronico(vista.txtCorreoElectronico.getText());
        persona.setFecha_nacimiento(Date.valueOf(vista.txtFechaNacimiento.getText()));
        persona.setGenero(vista.cmbGenero.getSelectedItem().toString());
    }

    
    private void capturarClave() {
        persona.setClave(vista.txtBuscar.getText());
    }
}
