/**
 * 
 */
package es.seresco.cursojee.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import es.seresco.cursojee.business.entities.Clase;
import es.seresco.cursojee.business.entities.ClaseCurso;

/**
 * @author seresco
 *
 */
public class ClaseCursoDAO implements IColegioBaseDAO<ClaseCurso> {
	
	
	private static final String COLUMN_ID="id";
	
	private static final String COLUMN_ID_CLASE="id_clase";
	
	private static final String COLUMN_ID_CURSO="id_curso";
	
	private static final String COLUMN_ID_TUTOR="id_tutor";

	

	@Override
	public ClaseCurso findById(Integer id) {
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("Select id,id_curso,id_clase,id_tutor from Clases_Curso where id=?"); // Creación de sentencia en blanco.
			statement.setInt(1, id);

		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la conexión", e);
		}
		ResultSet rs;
		try {
			rs = statement.executeQuery(); // Ejecución de consulta y
																							// asignamos el resultado a
																							// un resultset
		} catch (SQLException e) {
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
		try {
			if (rs.next()) {
				ClaseCurso claseCurso = new ClaseCurso();
				claseCurso.setId(rs.getInt(COLUMN_ID)); // Leemos una columna del resultset
				claseCurso.getCurso().setId(rs.getInt(COLUMN_ID_CURSO));
				claseCurso.getClase().setId(rs.getInt(COLUMN_ID_CLASE));
				claseCurso.getTutor().setId(rs.getInt(COLUMN_ID_TUTOR));
				return claseCurso;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al leer el registro", e);
		}
	}

	@Override
	public Integer count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClaseCurso> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClaseCurso insert(ClaseCurso objetoAInsertar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ClaseCurso objetoAInsertar) {
		// TODO Auto-generated method stub
		
	}

}
