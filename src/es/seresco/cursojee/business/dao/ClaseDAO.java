package es.seresco.cursojee.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.seresco.cursojee.business.entities.Clase;

public class ClaseDAO implements IColegioBaseDAO<Clase> {

	public static final String COLUMN_CURSO = "curso";
	public static final String COLUMN_LETRA = "letra";
	public static final String COLUMN_ID = "id";

	@Override
	public List<Clase> findAll() {
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		List<Clase> resultado = new ArrayList<Clase>();

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
				Clase clase = new Clase();
				clase.setId(rs.getInt(COLUMN_ID)); // Leemos una columna del resultset
				clase.setCurso(rs.getInt(COLUMN_CURSO));
				clase.setLetra(rs.getString(COLUMN_LETRA));
				resultado.add(clase);
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
			rs = statement.executeQuery("Select count(1) as numeroClases from Clases"); // Ejecución de consulta y
																						// asignamos el resultado a
																						// un resultset
		} catch (SQLException e) {
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
		try {
			if (rs.next()) {
				Integer numero = rs.getInt("numeroClases"); // Leemos una columna del resultset
				return numero;
			} else {
				return 0;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al leer el registro", e);
		}
	}

	@Override
	public Clase findById(Integer id) {
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("Select id,curso,letra from Clases where id=?"); // Creación de
																									// sentencia en
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
				Clase clase = new Clase();
				clase.setId(rs.getInt(COLUMN_ID)); // Leemos una columna del resultset
				clase.setCurso(rs.getInt(COLUMN_CURSO));
				clase.setLetra(rs.getString(COLUMN_LETRA));
				return clase;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al leer el registro", e);
		}

	}

	@Override
	public Clase insert(Clase clase) {
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("INSERT INTO Clases(curso,letra) VALUES(?,?)"); // Creación de
																									// sentencia en
																									// blanco.
			statement.setInt(1, clase.getCurso());
			statement.setString(2, clase.getLetra());

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
				clase.setId(rs.getInt(1));
				return clase;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al leer el registro", e);
		}
	}

	@Override
	public void update(Clase clase) {
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("UPDATE Clases SET curso=?,letra=? WHERE ID=?"); // Creación de
																									// sentencia en
																									// blanco.
			statement.setInt(1, clase.getCurso());
			statement.setString(2, clase.getLetra());
			statement.setInt(3, clase.getId());

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
	public void delete(Clase clase) {
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("DELETE FROM Clases WHERE ID=?"); // Creación de
																									// sentencia en
																									// blanco.
			statement.setInt(1, clase.getId());

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
