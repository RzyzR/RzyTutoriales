package CRUD_MVC.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rzy
 */
public class ConsultasPersona extends Conectar {

    PreparedStatement ps;
    ResultSet rs;
    String query = "";

    public boolean guardar(Persona persona) {
        Connection con = getConection();

        try {
            ps = con.prepareStatement("INSERT INTO persona"
                    + "(clave, nombre, domicilio, celular, correo_electronico, fecha_nacimiento, genero)"
                    + "VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, persona.getClave());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getDomicilio());
            ps.setString(4, persona.getCelular());
            ps.setString(5, persona.getCorreo_electronico());
            ps.setDate(6, persona.getFecha_nacimiento());
            ps.setString(7, persona.getGenero());

            int result = ps.executeUpdate();

            if (result != 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public boolean buscar(Persona persona) {
        Connection con = getConection();

        try {
            ps = con.prepareStatement("SELECT * FROM persona WHERE clave=?");
            ps.setString(1, persona.getClave());
            rs = ps.executeQuery();

            if (rs.next()) {
                persona.setIdPersona((rs.getInt("idPersona")));
                persona.setClave(rs.getString("clave"));
                persona.setNombre(rs.getString("nombre"));
                persona.setDomicilio(rs.getString("domicilio"));
                persona.setCelular(rs.getString("celular"));
                persona.setCorreo_electronico(rs.getString("correo_electronico"));
                persona.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                persona.setGenero(rs.getString("genero"));
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    //UPDATE persona 
    //SET `clave` = '011', `nombre` = 'Pao', `domicilio` = 'Loja, Sur', 
    //`celular` = '1104195111', `correo_electronico` = 'pao@gmail.com', 
    //`fecha_nacimiento` = '2000-10-11', `genero` = 'Femenina' 
    //WHERE (`idPersona` = '9');
    public boolean actualizar(Persona persona) {
        Connection con = getConection();

        try {
            ps = con.prepareStatement("UPDATE persona"
                    + " SET clave=?, nombre=?, domicilio=?,"
                    + "celular=?, correo_electronico=?,"
                    + "fecha_nacimiento=?, genero=?"
                    + "WHERE idPersona=?");
            ps.setString(1, persona.getClave());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getDomicilio());
            ps.setString(4, persona.getCelular());
            ps.setString(5, persona.getCorreo_electronico());
            ps.setDate(6, persona.getFecha_nacimiento());
            ps.setString(7, persona.getGenero());
            ps.setInt(8, persona.getIdPersona());

            int result = ps.executeUpdate();

            if (result != 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
    
     public boolean eliminar(Persona persona) {
        Connection con = getConection();

        try {
            ps = con.prepareStatement("DELETE FROM persona WHERE idPersona=?");
            ps.setInt(1, persona.getIdPersona());
            int result = ps.executeUpdate();

            if (result != 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
