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
public class Estado {

    private int idEstado;
    private String nombreEstado;

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

   

    @Override
    public String toString() {
        return nombreEstado;
    }

    public Vector<Estado> mostrarEstados(Integer idPais) {
        PreparedStatement ps;
        ResultSet rs;

        Vector<Estado> vectorPaises = new Vector<Estado>();
        Estado pais = null;

        try {
            Conectar c = new Conectar();
            Connection connection = c.getConection();

            ps = connection.prepareStatement("select * from estado where idPais="+idPais);
            rs = ps.executeQuery();

            pais = new Estado();
            pais.setIdEstado(0);
            pais.setNombreEstado("Seleccione el estado");
            vectorPaises.add(pais);

            while (rs.next()) {
                pais = new Estado();
                pais.setIdEstado(rs.getInt("idEstado"));
                pais.setNombreEstado(rs.getString("nombre"));
                vectorPaises.add(pais);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return vectorPaises;
    }
}
