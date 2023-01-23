package ComboBox.modelo;

import ComboBox.CagarComboToJTable.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Randy
 */
public class Ciudad {

    private int idCiudad;
    private String nombreCiudad;

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    
   

    @Override
    public String toString() {
        return nombreCiudad;
    }

    public Vector<Ciudad> mostrarCiudades(Integer idEstado) {
        PreparedStatement ps;
        ResultSet rs;

        Vector<Ciudad> vectorPaises = new Vector<Ciudad>();
        Ciudad pais = null;

        try {
            Conectar c = new Conectar();
            Connection connection = c.getConection();

            ps = connection.prepareStatement("select * from ciudad where idEstado="+idEstado);
            rs = ps.executeQuery();

            pais = new Ciudad();
            pais.setIdCiudad(0);
            pais.setNombreCiudad("Seleccione el estado");
            vectorPaises.add(pais);

            while (rs.next()) {
                pais = new Ciudad();
                pais.setIdCiudad(rs.getInt("idEstado"));
                pais.setNombreCiudad(rs.getString("nombre"));
                vectorPaises.add(pais);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return vectorPaises;
    }
}
