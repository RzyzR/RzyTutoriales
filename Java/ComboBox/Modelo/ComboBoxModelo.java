package ComboBox.modelo;

import ComboBox.CagarComboToJTable.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Randy
 */
public class ComboBoxModelo {
    
    
    Conectar c;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    private Connection establecerConexion(){
        c = new Conectar();
        con = c.getConection();
        return con;
    }
    
     public void cargarCombo(JComboBox box){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        try {
            establecerConexion();
            ps = con.prepareStatement("select * from pais");
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                model.addElement(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.err.println("Error: "+e.getMessage());
        }
        box.setModel(model);
    }
}
