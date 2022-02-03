package es.seresco.cursojee.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import es.seresco.cursojee.business.entities.Profesor;

public class ProfesorDAO implements IColegioBaseDAO<Profesor> {
	
	final static Logger logger = Logger.getLogger(ProfesorDAO.class);


	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NOMBRE = "nombre";
	public static final String COLUMN_APELLIDO = "apellido";
	

	public List<Profesor> findAll() {
		logger.debug("Entrando en el método findAll");
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		List<Profesor> resultado = new ArrayList<Profesor>();

		Statement statement = null;
		try {
			statement = conexion.createStatement(); // Creación de sentencia en blanco.
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al obetener la conexión",e);
			throw new RuntimeException("Error al obtener la conexión", e);
		}
		ResultSet rs;
		try {
			rs = statement.executeQuery("Select id,nombre,apellido from Profesores"); // Ejecución de consulta y asignamos el resultado a un resultset
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al ejecutar la consulta para obtener todos los registros",e);
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
		try {
			while (rs.next()) {
				Profesor profesor = new Profesor();
				profesor.setId(rs.getInt(COLUMN_ID)); // Leemos una columna del resultset
				profesor.setNombre(rs.getString(COLUMN_NOMBRE)); // Leemos una columna del resultset
				profesor.setApellido(rs.getString(COLUMN_APELLIDO)); // Leemos una columna del resultset
				resultado.add(profesor);
				logger.debug("Profesor "+profesor.getNombre()+" "+profesor.getApellido() +" leído correctamente");
			}
			logger.info("Se han leído los "+resultado.size()+" registros de la tabla correctamente");
			
			return resultado;
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al leer el registro",e);
			throw new RuntimeException("Error al leer el registro", e);
		}
	}

	public Integer count() {
		logger.debug("Entrando en el método count");
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		Statement statement = null;
		try {
			statement = conexion.createStatement(); // Creación de sentencia en blanco.
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al obetener la conexión",e);
			throw new RuntimeException("Error al obtener la conexión", e);
		}
		ResultSet rs;
		try {
			rs = statement.executeQuery("Select count(1) as numeroProfesores from Profesores"); /// Ejecución de consulta y asignamos el resultado a un resultset
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al ejecutar la consulta");
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
		try {
			if (rs.next()) {
				Integer numero = rs.getInt("numeroProfesores"); // Leemos una columna del resultset
				logger.info("Hay "+numero+ " registros en la tabla");
				return numero;
			} else {
				logger.warn("No se esperaba que no devolviera un registro con el número de elementos");
				return 0;
			}
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al leer el registro",e);
			throw new RuntimeException("Error al leer el registro", e);
		}
	}

	public Profesor findById(Integer id) {
		logger.debug("Entrando en el método findById");
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("Select id,nombre,apellido from Profesores where id=?"); //Preparación de sql parametrizada
			statement.setInt(1, id); //Establecemos el valor del primer parámetro de la consulta

		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al preparar la consulta",e);
			throw new RuntimeException("Ha ocurrido un error al preparar la consulta", e);
		}
		ResultSet rs;
		try {
			rs = statement.executeQuery(); // Ejecución de consulta y asignamos el resultado a un resultset
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al ejecutar la consulta");
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
		try {
			if (rs.next()) {
				Profesor profesor = new Profesor();
				profesor.setId(rs.getInt(COLUMN_ID)); // Leemos una columna del resultset
				profesor.setNombre(rs.getString(COLUMN_NOMBRE)); // Leemos una columna del resultset
				profesor.setApellido(rs.getString(COLUMN_APELLIDO)); // Leemos una columna del resultset
				logger.debug("Registro "+profesor.getNombre()+" "+profesor.getApellido() +" leído correctamente.");
				logger.info("Localizado registro con identificador "+id);
				return profesor;
			} else {
				logger.warn("No se ha encontrado el registro con identificador"+ id);
				return null;
			}
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al leer el registro",e);
			throw new RuntimeException("Error al leer el registro", e);
		}
	}

	@Override
	public Profesor insert(Profesor profesor) {
		logger.debug("Entrando en el método insert");
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("INSERT INTO Profesores(nombre,apellido) VALUES(?,?)"); //Preparación de sql parametrizada
			statement.setString(1, profesor.getNombre());  //Establecemos el valor del primer parámetro de la consulta
			statement.setString(2, profesor.getApellido());//Establecemos el valor del segundo parámetro de la consulta

		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al preparar la consulta",e);
			throw new RuntimeException("Ha ocurrido un error al preparar la consulta", e);
		}
		ResultSet rs;
		try {
			statement.executeUpdate(); // Ejecución de consulta
			rs = statement.getGeneratedKeys(); //Leemos la clave autogenerada.
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al ejecutar la consulta", e);
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
		try {
			if (rs.next()) {
				profesor.setId(rs.getInt(1)); //Solo habrá un único registro con el identificador autonumérico
				logger.debug("Obetenido identificador generado desde la base de datos");
				return profesor;
			} else {
				return null;
			}
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al obtener la clave primaria autogenerada",e);
			throw new RuntimeException("Ha ocurrido un error al obtener la clave primaria autogenerada", e);
		}
	}

	@Override
	public void update(Profesor profesor) {
		logger.debug("Entrando en el método update");
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("UPDATE Profesores SET nombre=?,apellido=? WHERE ID=?");  //Preparación de sql parametrizada
			statement.setString(1, profesor.getNombre()); //Establecemos el valor del primer parámetro de la consulta
			statement.setString(2, profesor.getApellido()); //Establecemos el valor del segundo parámetro de la consulta
			statement.setInt(3, profesor.getId()); //Establecemos el valor del tercer parámetro de la consulta

		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al preparar la consulta",e);
			throw new RuntimeException("Ha ocurrido un error al preparar la consulta", e);
		}

		try {
			statement.executeUpdate(); //Ejecutamos consulta y no tenemos resultset porque no esperamos retorno de datos
			logger.info("Registro con identificador "+profesor.getId()+" actualizado correctamente");
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al ejecutar la consulta",e);
			throw new RuntimeException("Ha ocurrido un error al ejecutar la consulta", e);
		}

	}
	
	@Override
	public void delete(Profesor profesor) {
		logger.debug("Entrando en el método delete");
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("DELETE FROM Profesores WHERE ID=?"); //Preparación de sql parametrizada
			statement.setInt(1, profesor.getId());//Establecemos el valor del primer parámetro de la consulta
			

		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al preparar la consulta",e);
			throw new RuntimeException("Error al preparar la consulta", e);
			
		}

		try {
			statement.executeUpdate(); //Ejecutamos consulta y no tenemos resultset porque no esperamos retorno de datos
			logger.info("Registro con identificador "+profesor.getId()+" borrado correctamente");
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al ejecutar la consulta de borrado del profesor con id"+profesor.getId(),e);
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
	}

}
