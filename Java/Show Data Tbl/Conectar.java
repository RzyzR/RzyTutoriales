package BaseDatos.MostrarDatosTabla;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Rzy
 */
public class Conectar {
    public static final String URL = "jdbc:mysql://localhost:3306/name_db"; //"jdbc:mysql://localhost:3306/escuela?autoReconnet=true&useSSL=false"
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    
    Connection c = null;
    
    public Conectar() {
    }
    
    public Connection getConection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c =  DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion exitosa");
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: "+e);
        }
        return c;
    }
}
