package es.seresco.cursojee.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import es.seresco.cursojee.business.entities.Clase;

public class ClaseDAO implements IColegioBaseDAO<Clase> {

	public static final String COLUMN_CURSO = "clase";
	public static final String COLUMN_LETRA = "letra";
	public static final String COLUMN_ID = "id";
	
	final static Logger logger = Logger.getLogger(ClaseDAO.class);

	public List<Clase> findAll() {
		logger.debug("Entrando en el método findAll");
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		List<Clase> resultado = new ArrayList<Clase>();

		Statement statement = null;
		try {
			statement = conexion.createStatement(); // Creación de sentencia en blanco.
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al obetener la conexión",e);
			throw new RuntimeException("Error al obtener la conexión", e);
		}
		ResultSet rs;
		try {
			rs = statement.executeQuery("Select id,anio_inicio,anio_fin from Clases"); // Ejecución de consulta y asignamos el resultado a un resultset
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al ejecutar la consulta para obtener todos los registros",e);
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
		try {
			while (rs.next()) {
				Clase clase = new Clase();
				clase.setId(rs.getInt(COLUMN_ID)); // Leemos una columna del resultset
				clase.setCurso(rs.getInt(COLUMN_CURSO)); // Leemos una columna del resultset
				clase.setLetra(rs.getString(COLUMN_LETRA)); // Leemos una columna del resultset
				resultado.add(clase);
				logger.debug("Clase "+clase.getCurso()+" "+clase.getLetra() +" leído correctamente");
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
			rs = statement.executeQuery("Select count(1) as numeroClases from Clases"); /// Ejecución de consulta y asignamos el resultado a un resultset
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al ejecutar la consulta");
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
		try {
			if (rs.next()) {
				Integer numero = rs.getInt("numeroClases"); // Leemos una columna del resultset
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

	public Clase findById(Integer id) {
		logger.debug("Entrando en el método findById");
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("Select id,curso,letra from Clases where id=?"); //Preparación de sql parametrizada
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
				Clase clase = new Clase();
				clase.setId(rs.getInt(COLUMN_ID)); // Leemos una columna del resultset
				clase.setCurso(rs.getInt(COLUMN_CURSO)); // Leemos una columna del resultset
				clase.setLetra(rs.getString(COLUMN_LETRA)); // Leemos una columna del resultset
				logger.debug("Clase "+clase.getCurso()+" "+clase.getLetra() +" leído correctamente");
				logger.info("Localizado registro con identificador "+id);
				return clase;
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
	public Clase insert(Clase clase) {
		logger.debug("Entrando en el método insert");
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("INSERT INTO Clases(curso,letra) VALUES(?,?)"); //Preparación de sql parametrizada
			statement.setInt(1, clase.getCurso());  //Establecemos el valor del primer parámetro de la consulta
			statement.setString(2, clase.getLetra());//Establecemos el valor del segundo parámetro de la consulta

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
				clase.setId(rs.getInt(1)); //Solo habrá un único registro con el identificador autonumérico
				logger.debug("Obetenido identificador generado desde la base de datos");
				return clase;
			} else {
				return null;
			}
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al obtener la clave primaria autogenerada",e);
			throw new RuntimeException("Ha ocurrido un error al obtener la clave primaria autogenerada", e);
		}
	}

	@Override
	public void update(Clase clase) {
		logger.debug("Entrando en el método update");
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("UPDATE Clases SET curso=?,letra=? WHERE ID=?");  //Preparación de sql parametrizada
			statement.setInt(1, clase.getCurso());  //Establecemos el valor del primer parámetro de la consulta
			statement.setString(2, clase.getLetra());//Establecemos el valor del segundo parámetro de la consulta
			statement.setInt(3, clase.getId()); //Establecemos el valor del tercer parámetro de la consulta

		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al preparar la consulta",e);
			throw new RuntimeException("Ha ocurrido un error al preparar la consulta", e);
		}

		try {
			statement.executeUpdate(); //Ejecutamos consulta y no tenemos resultset porque no esperamos retorno de datos
			logger.info("Registro con identificador "+clase.getId()+" actualizado correctamente");
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al ejecutar la consulta",e);
			throw new RuntimeException("Ha ocurrido un error al ejecutar la consulta", e);
		}

	}
	
	@Override
	public void delete(Clase clase) {
		logger.debug("Entrando en el método delete");
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("DELETE FROM Clases WHERE ID=?"); //Preparación de sql parametrizada
			statement.setInt(1, clase.getId());//Establecemos el valor del primer parámetro de la consulta
			

		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al preparar la consulta",e);
			throw new RuntimeException("Error al preparar la consulta", e);
			
		}

		try {
			statement.executeUpdate(); //Ejecutamos consulta y no tenemos resultset porque no esperamos retorno de datos
			logger.info("Registro con identificador "+clase.getId()+" borrado correctamente");
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al ejecutar la consulta de borrado del clase con id"+clase.getId(),e);
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
	}
	

}
