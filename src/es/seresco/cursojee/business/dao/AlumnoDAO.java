package es.seresco.cursojee.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.seresco.cursojee.business.entities.Alumno;

public class AlumnoDAO implements IColegioBaseDAO<Alumno> {

	public static final String COLUMN_NOMBRE = "nombre";
	public static final String COLUMN_APELLIDO = "apellido";
	public static final String COLUMN_ID = "id";

	public List<Alumno> findAll() {
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		List<Alumno> resultado = new ArrayList<Alumno>();

		Statement statement = null;
		try {
			statement = conexion.createStatement(); // Creación de sentencia en blanco.
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la conexión", e);
		}
		ResultSet rs;
		try {
			rs = statement.executeQuery("Select id,nombre,apellido from Alumnos"); // Ejecución de consulta y asignamos
																					// el resultado a un resultset
		} catch (SQLException e) {
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
		try {
			while (rs.next()) {
				Alumno alumno = new Alumno();
				alumno.setId(rs.getInt(COLUMN_ID)); // Leemos una columna del resultset
				alumno.setNombre(rs.getString(COLUMN_NOMBRE));
				alumno.setApellido(rs.getString(COLUMN_APELLIDO));
				resultado.add(alumno);
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
			rs = statement.executeQuery("Select count(1) as numeroAlumnos from Alumnos"); // Ejecución de consulta y
																							// asignamos el resultado a
																							// un resultset
		} catch (SQLException e) {
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
		try {
			if (rs.next()) {
				Integer numero = rs.getInt("numeroAlumnos"); // Leemos una columna del resultset
				return numero;
			} else {
				return 0;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al leer el registro", e);
		}
	}

	public Alumno findById(Integer id) {
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("Select id,nombre,apellido from Alumnos where id=?"); // Creación de
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
				Alumno alumno = new Alumno();
				alumno.setId(rs.getInt(COLUMN_ID)); // Leemos una columna del resultset
				alumno.setNombre(rs.getString(COLUMN_NOMBRE));
				alumno.setApellido(rs.getString(COLUMN_APELLIDO));
				return alumno;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al leer el registro", e);
		}
	}

	@Override
	public Alumno insert(Alumno alumno) {
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("INSERT INTO Alumnos(nombre,apellido) VALUES(?,?)"); // Creación de
																										// sentencia en
																										// blanco.
			statement.setString(1, alumno.getNombre());
			statement.setString(2, alumno.getApellido());

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
				alumno.setId(rs.getInt(1));
				return alumno;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al leer el registro", e);
		}
	}

	@Override
	public void update(Alumno alumno) {
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("UPDATE Alumnos SET nombre=?,apellido=? WHERE ID=?"); // Creación de
																										// sentencia en
																										// blanco.
			statement.setString(1, alumno.getNombre());
			statement.setString(2, alumno.getApellido());
			statement.setInt(3, alumno.getId());

		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la conexión", e);
		}

		try {
			statement.executeUpdate(); // Ejecución de consulta y // asignamos el resultado a // un resultset
		} catch (SQLException e) {
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}

	}
	
	@Override
	public void delete(Alumno alumno) {
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("DELETE FROM Alumnos WHERE ID=?"); // Creación de
																									// sentencia en
																									// blanco.
			statement.setInt(1, alumno.getId());

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
