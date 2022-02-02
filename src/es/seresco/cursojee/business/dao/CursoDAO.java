package es.seresco.cursojee.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.seresco.cursojee.business.entities.Curso;

public class CursoDAO {
	
	public static final String COLUMN_ANIO_INICIO="anio_inicio";
	public static final String COLUMN_ANIO_FIN ="anio_fin";
	public static final String COLUMN_ID="id";

	public List<Curso> findAll() {
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		List<Curso> resultado = new ArrayList<Curso>();

		Statement statement = null;
		try {
			statement = conexion.createStatement(); // Creación de sentencia en blanco.
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la conexión", e);
		}
		ResultSet rs;
		try {
			rs = statement.executeQuery("Select id,anio_inicio,anio_fin from Cursos"); // Ejecución de consulta y asignamos
																					// el resultado a un resultset
		} catch (SQLException e) {
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
		try {
			while (rs.next()) {
				Curso curso = new Curso();
				curso.setId(rs.getInt(COLUMN_ID)); // Leemos una columna del resultset
				curso.setAnioInicio(rs.getInt(COLUMN_ANIO_INICIO));
				curso.setAnioFin(rs.getInt(COLUMN_ANIO_FIN));
				resultado.add(curso);
			}
			
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException("Error al leer el registro", e);
		}
	}

	public Integer countCursos() {

		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		Statement statement = null;
		try {
			statement = conexion.createStatement(); // Creación de sentencia en blanco.
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la conexión", e);
		}
		ResultSet rs;
		try {
			rs = statement.executeQuery("Select count(1) as numeroCursos from Cursos"); // Ejecución de consulta y
																							// asignamos el resultado a
																							// un resultset
		} catch (SQLException e) {
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
		try {
			if (rs.next()) {
				Integer numero = rs.getInt("numeroCursos"); // Leemos una columna del resultset
				return numero;
			} else {
				return 0;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al leer el registro", e);
		}
	}
	
	public Curso findById(Integer id) {
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("Select id,anio_inicio,anio_fin from Cursos where id=?"); // Creación de sentencia en blanco.
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
				Curso curso = new Curso();
				curso.setId(rs.getInt(COLUMN_ID)); // Leemos una columna del resultset
				curso.setAnioInicio(rs.getInt(COLUMN_ANIO_INICIO));
				curso.setAnioFin(rs.getInt(COLUMN_ANIO_FIN));
				return curso;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al leer el registro", e);
		}
	}

}
