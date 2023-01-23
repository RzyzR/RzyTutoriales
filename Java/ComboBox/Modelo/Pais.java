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
public class Pais {

    private int idPais;
    private String nombrePais;

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    @Override
    public String toString() {
        return nombrePais;
    }

    public Vector<Pais> mostrarPaises() {
        PreparedStatement ps;
        ResultSet rs;

        Vector<Pais> vectorPaises = new Vector<Pais>();
        Pais pais = null;

        try {
            Conectar c = new Conectar();
            Connection connection = c.getConection();

            ps = connection.prepareStatement("select * from pais");
            rs = ps.executeQuery();

            pais = new Pais();
            pais.setIdPais(0);
            pais.setNombrePais("Seleccione el país");
            vectorPaises.add(pais);

            while (rs.next()) {
                pais = new Pais();
                pais.setIdPais(rs.getInt("idPais"));
                pais.setNombrePais(rs.getString("nombre"));
                vectorPaises.add(pais);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return vectorPaises;
    }
}
