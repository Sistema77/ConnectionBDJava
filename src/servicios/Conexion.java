package servicios;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class Conexion implements  ConexionInterface {
	// Constructor
	
	public Conexion() {}
	
	// Metodos
	
	public Connection generarConexion() {
		// Inicializacion
		String[] parametrosConexion = configuracionConexion();
		Connection conexion = null;
	    
	    // Algoritmo
	    
	    try {
	    	Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(parametrosConexion[0] ,parametrosConexion[1],parametrosConexion[2]);
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
	
	private String[] configuracionConexion() {
		
		String user="", pass="", port="", host="", db="", url="";
		String[] stringConfiguracion = {"","",""};
		
		Properties propiedadesConexion = new Properties();
		
		try {
			propiedadesConexion.load(new FileInputStream(new File("C:\\Users\\Puesto20\\eclipse-workspace\\ConnectionBDJAVA\\src\\Util\\conexion_postgresql.properties")));
			user = propiedadesConexion.getProperty("user");
			pass = propiedadesConexion.getProperty("pass");
			port = propiedadesConexion.getProperty("port");
			host = propiedadesConexion.getProperty("host");
			db = propiedadesConexion.getProperty("db");
			url = "jdbc:postgresql://" + host + ":" + port + "/" + db;
			stringConfiguracion[0] = url;
			stringConfiguracion[1] = user;
			stringConfiguracion[2] = pass;
		} catch (Exception e) {
			System.err.println("[ERROR-Conexion-configuracionConexion] - Error al acceder al fichero propiedades de conexion.");
			stringConfiguracion[0] = "";
			stringConfiguracion[1] = "";
			stringConfiguracion[2] = "";
		}
		return stringConfiguracion;
	}
}
