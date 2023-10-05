package servicios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dtos.Libros;

public class Consulta {
	long idLibro;
	String titulo;
	String autor;
	String isbn;
	int edicion;
	public void select(Connection conexion) {
		try(Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM gbp_alm_cat_libros")){ // Consulta y la verificacion de la Query

			while(rs.next()) { 

				idLibro = Long.parseLong(rs.getString("id_libro"));
				titulo = rs.getString("titulo");
				autor = rs.getString("autor");
				isbn = rs.getString("isbn");
				edicion = Integer.parseInt(rs.getString("edicion"));		
												//Creo el Objeto Libro para Hacer el DTOS de una vez y que el objeto Libro se vaya limpiando 
												//con el recolector de vasura de Java y reutilizar la variable y asi ocupar menos espacio de memoria 
				Libros libro = new Libros(idLibro, titulo, autor, isbn, edicion);
				System.out.println(libro.toString());
			}
		}catch(SQLException e) {
			System.out.println("[ERROR: Consulta-select]");
		}
	}
	
	public void insert(Connection conexion, String titulo, String autor, String isbn, int edicion) { 
	    String consultaSQL = "INSERT INTO public.gbp_alm_cat_libros(titulo, autor, isbn, edicion) VALUES (?, ?, ?, ?)"; // Consulta 
	    
	    try (PreparedStatement pstmt = conexion.prepareStatement(consultaSQL)) { //verificacion de Datos de la Query
	        pstmt.setString(1, titulo);
	        pstmt.setString(2, autor);
	        pstmt.setString(3, isbn);
	        pstmt.setInt(4, edicion);

	        int filasAfectadas = pstmt.executeUpdate();
	        //conexion.commit(); ESTA VERSION DE JAVA YA TIENE EL AutoCommit activado por defecto y produce errores intentar 
	        //					hacer un commit a mano
	        
	        if (filasAfectadas > 0) {
	            System.out.println("[INFO]: Inserción exitosa");
	        } else {
	            System.out.println("[ERROR]: No se pudo insertar el registro");
	        }
	    } catch (SQLException e) {
	        System.out.println("[ERROR]: Error al ejecutar la consulta de inserción: " + e.getMessage());
	    }
	}
	
	public void delete(Connection conexion, long idLibro) {
	    String consultaSQL = "DELETE FROM public.gbp_alm_cat_libros WHERE id_libro = ?";
	    
	    try (PreparedStatement pstmt = conexion.prepareStatement(consultaSQL)) {
	        pstmt.setLong(1, idLibro);
	        
	        //conexion.commit(); ESTA VERSION DE JAVA YA TIENE EL AutoCommit activado por defecto y produce errores intentar 
	        //					hacer un commit a mano
	        
	        int filasAfectadas = pstmt.executeUpdate();
	        
	        if (filasAfectadas > 0) {
	            System.out.println("[INFO]: Eliminación exitosa");
	        } else {
	            System.out.println("[INFO]: No se encontró ningún registro con el ID proporcionado");
	        }
	    } catch (SQLException e) {
	        System.out.println("[ERROR]: Error al ejecutar la consulta de eliminación: " + e.getMessage());
	    }
	}
	
	public void update(Connection conexion, String titulo, String autor, String isbn, int edicion, long id) {
	    String consultaSQL = "UPDATE public.gbp_alm_cat_libros SET titulo=?, autor=?, isbn=?, edicion=? WHERE id_libro = ?";

	    try (PreparedStatement pstmt = conexion.prepareStatement(consultaSQL)) {
	        pstmt.setString(1, titulo);
	        pstmt.setString(2, autor);
	        pstmt.setString(3, isbn);
	        pstmt.setInt(4, edicion);
	        pstmt.setLong(5, id);

	        int filasAfectadas = pstmt.executeUpdate();

	        System.out.println(filasAfectadas);
	        if (filasAfectadas > 0) {
	            System.out.println("[INFO]: Actualización exitosa");
	        } else {
	            System.out.println("[ERROR]: No se pudo Actualizar el registro");
	        }
	    } catch (SQLException e) {
	        System.out.println("[ERROR]: Error al ejecutar la consulta de Actualización: " + e.getMessage());
	    }
	}



}
