/**
 * 
 */
package es.seresco.cursojee.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.seresco.cursojee.business.entities.ClaseCurso;

/**
 * @author seresco
 *
 */
public class ClaseCursoDAO implements IColegioBaseDAO<ClaseCurso> {

	private static final String COLUMN_ID = "id";

	private static final String COLUMN_ID_CLASE = "id_clase";

	private static final String COLUMN_ID_CURSO = "id_curso";

	private static final String COLUMN_ID_TUTOR = "id_tutor";

	@Override
	public ClaseCurso findById(Integer id) {
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("Select id,id_curso,id_clase,id_tutor from Clases_Curso where id=?"); // Creación
																														// de
																														// sentencia
																														// en
																														// blanco.
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
	public List<ClaseCurso> findAll() {
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		List<ClaseCurso> resultado = new ArrayList<ClaseCurso>();

		Statement statement = null;
		try {
			statement = conexion.createStatement(); // Creación de sentencia en blanco.
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la conexión", e);
		}
		ResultSet rs;
		try {
			rs = statement.executeQuery("Select id,cursoLetra from Clases"); // Ejecución de consulta y asignamos
																				// el resultado a un resultset
		} catch (SQLException e) {
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
		try {
			while (rs.next()) {
				ClaseCurso claseCurso = new ClaseCurso();
				claseCurso.setId(rs.getInt(COLUMN_ID)); // Leemos una columna del resultset
				claseCurso.getCurso().setId(rs.getInt(COLUMN_ID_CURSO));
				claseCurso.getClase().setId(rs.getInt(COLUMN_ID_CLASE));
				claseCurso.getTutor().setId(rs.getInt(COLUMN_ID_TUTOR));
				resultado.add(claseCurso);
			}

			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException("Error al leer el registro", e);
		}
	}

	@Override
	public Integer count() {

		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		Statement statement = null;
		try {
			statement = conexion.createStatement(); // Creación de sentencia en blanco.
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la conexión", e);
		}
		ResultSet rs;
		try {
			rs = statement.executeQuery("Select count(1) as numero from Clases_Curso"); // Ejecución de consulta y
																						// asignamos el resultado a
																						// un resultset
		} catch (SQLException e) {
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
		try {
			if (rs.next()) {
				Integer numero = rs.getInt("numero"); // Leemos una columna del resultset
				return numero;
			} else {
				return 0;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al leer el registro", e);
		}
	}

	@Override
	public ClaseCurso insert(ClaseCurso claseCurso) {
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("INSERT INTO Clases_Curso(id_clase,id_curso,id_tutor) VALUES(?,?,?)"); // Creación
																															// de
			// sentencia en

			statement.setInt(1, claseCurso.getClase().getId());
			statement.setInt(2, claseCurso.getCurso().getId());
			statement.setInt(3, claseCurso.getTutor().getId());

		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la conexión", e);
		}
		ResultSet rs;
		try {
			statement.executeUpdate(); // Ejecución de consulta y
			rs = statement.getGeneratedKeys();

			// asignamos el resultado a
			// un resultset
		} catch (SQLException e) {
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
		try {
			if (rs.next()) {
				claseCurso.setId(rs.getInt(1));
				return claseCurso;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al leer el registro", e);
		}
	}

	@Override
	public void update(ClaseCurso claseCurso) {
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion
					.prepareStatement("UPDATE Clases_Curso SET id_clase=?,id_curso=?,id_tutor=? WHERE ID=?"); // Creación
																												// de
			// sentencia en
			// blanco.
			statement.setInt(1, claseCurso.getClase().getId());
			statement.setInt(2, claseCurso.getCurso().getId());
			statement.setInt(3, claseCurso.getTutor().getId());
			statement.setInt(4, claseCurso.getId());

		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la conexión", e);
		}

		try {
			statement.executeUpdate(); // Ejecución de consulta y
										// un resultset
		} catch (SQLException e) {
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
	}

	@Override
	public void delete(ClaseCurso claseCurso) {
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("DELETE FROM Clases_Curso WHERE ID=?"); // Creación de
																						// sentencia en
																						// blanco.
			statement.setInt(1, claseCurso.getId());

		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la conexión", e);
		}

		try {
			statement.executeUpdate(); // Ejecución de consulta y
										// un resultset
		} catch (SQLException e) {
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
	}

}
