package ComboBox.modelo;

import ComboBox.CagarComboToJTable.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Randy
 */
public class CargarTablaFromCombo {
    
    Pais pais = new Pais();
    Conectar c;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    private Connection establecerConexion(){
        c = new Conectar();
        con = c.getConection();
        return con;
    }
    
    public void cargarCombo(JTable tabla){
        DefaultTableModel model = new DefaultTableModel();
        try {
            establecerConexion();
            ps = con.prepareStatement("select * from estado where idPais="+pais.getIdPais());
            rs = ps.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int cantidadColumnas = rsmd.getColumnCount();
            
            int anchos[] = {50, 150};
            
            for (int i = 0; i < cantidadColumnas; i++) {
                tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) {                
                Object fila[] = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = rs.getObject(i+1);
                }
                model.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error: "+e.getMessage());
        }
        tabla.setModel(model);
    }
}
