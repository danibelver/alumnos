package es.seresco.cursojee.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.seresco.cursojee.business.entities.Profesor;

public class ProfesorDAO implements IColegioBaseDAO<Profesor> {

	public static final String COLUMN_NOMBRE="nombre";
	public static final String COLUMN_APELLIDO ="apellido";
	public static final String COLUMN_ID="id";

	public List<Profesor> findAll() {
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		List<Profesor> resultado = new ArrayList<Profesor>();

		Statement statement = null;
		try {
			statement = conexion.createStatement(); // Creación de sentencia en blanco.
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la conexión", e);
		}
		ResultSet rs;
		try {
			rs = statement.executeQuery("Select id,nombre,apellido from Profesors"); // Ejecución de consulta y asignamos
																					// el resultado a un resultset
		} catch (SQLException e) {
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
		try {
			while (rs.next()) {
				Profesor profesor = new Profesor();
				profesor.setId(rs.getInt(COLUMN_ID)); // Leemos una columna del resultset
				profesor.setNombre(rs.getString(COLUMN_NOMBRE));
				profesor.setApellido(rs.getString(COLUMN_APELLIDO));
				resultado.add(profesor);
			}
			
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException("Error al leer el registro", e);
		}
	}

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
			rs = statement.executeQuery("Select count(1) as numeroProfesors from Profesors"); // Ejecución de consulta y
																							// asignamos el resultado a
																							// un resultset
		} catch (SQLException e) {
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
		try {
			if (rs.next()) {
				Integer numero = rs.getInt("numeroProfesors"); // Leemos una columna del resultset
				return numero;
			} else {
				return 0;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al leer el registro", e);
		}
	}
	
	public Profesor findById(Integer id) {
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("Select id,nombre,apellido from Profesors where id=?"); // Creación de sentencia en blanco.
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
				Profesor profesor = new Profesor();
				profesor.setId(rs.getInt(COLUMN_ID)); // Leemos una columna del resultset
				profesor.setNombre(rs.getString(COLUMN_NOMBRE));
				profesor.setApellido(rs.getString(COLUMN_APELLIDO));
				return profesor;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al leer el registro", e);
		}
	}

	
}
