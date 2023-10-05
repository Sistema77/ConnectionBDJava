package servicios;

import java.sql.Connection;

public interface ConsultaInterface {
	// No Hay comentarios aqui porque ya estan comentados todos los metodos en la clase
	public void select(Connection conexion);
	
	public void insert(Connection conexion, String titulo, String autor, String isbn, int edicion);
	
	public void delete(Connection conexion, long idLibro);
	
	public void update(Connection conexion, String titulo, String autor, String isbn, int edicion, long id);
}
