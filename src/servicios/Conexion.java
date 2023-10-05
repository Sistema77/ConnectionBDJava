package servicios;

import java.sql.*;

public class Conexion implements  ConexionInterface {
	// Constructor
	
	public Conexion() {}
	
	// Metodos
	
	public Connection generarConexion() {
		// Inicializacion
		
		Connection conexion = null;
		String url = "jdbc:postgresql://localhost:5432/gestorBibliotecaPersonal";
	    String usuario = "postgres";
	    String contrasena = "Admin";
	    
	    // Algoritmo
	    
	    try {
	    	Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Conexión exitosa a la base de datos.");
            
            return conexion;
            
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            conexion = null;
            
            return conexion;
        } catch (ClassNotFoundException e) {
        	System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        	conexion = null;
            
            return conexion;
		} 
	}
	public void cerrarConexion(Connection conexion) {
		if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión Cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
	}
}
