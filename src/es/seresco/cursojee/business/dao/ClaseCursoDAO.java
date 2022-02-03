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

import org.apache.log4j.Logger;

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

	final static Logger logger = Logger.getLogger(ClaseDAO.class);

	public List<ClaseCurso> findAll() {
		logger.debug("Entrando en el método findAll");
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		List<ClaseCurso> resultado = new ArrayList<ClaseCurso>();

		Statement statement = null;
		try {
			statement = conexion.createStatement(); // Creación de sentencia en blanco.
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al obetener la conexión",e);
			throw new RuntimeException("Error al obtener la conexión", e);
		}
		ResultSet rs;
		try {
			rs = statement.executeQuery("Select id,id_clase,id_curso,id_tutor from Clases_Curso"); // Ejecución de consulta y asignamos el resultado a un resultset
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al ejecutar la consulta para obtener todos los registros",e);
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
		try {
			while (rs.next()) {
				ClaseCurso claseCurso = new ClaseCurso();
				claseCurso.setId(rs.getInt(COLUMN_ID)); // Leemos una columna del resultset
				claseCurso.getClase().setId(rs.getInt(COLUMN_ID_CLASE)); // Leemos una columna del resultset
				claseCurso.getCurso().setId(rs.getInt(COLUMN_ID_CURSO)); // Leemos una columna del resultset
				claseCurso.getTutor().setId(rs.getInt(COLUMN_ID_TUTOR)); // Leemos una columna del resultset
				resultado.add(claseCurso);
				logger.debug("ClaseCurso "+ claseCurso.getClase().getId()+" "+claseCurso.getCurso().getId()+ " leído correctamente");
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
			rs = statement.executeQuery("Select count(1) as numero from Clases_Curso"); /// Ejecución de consulta y asignamos el resultado a un resultset
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al ejecutar la consulta");
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
		try {
			if (rs.next()) {
				Integer numero = rs.getInt("numero"); // Leemos una columna del resultset
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

	public ClaseCurso findById(Integer id) {
		logger.debug("Entrando en el método findById");
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("Select id,curso,letra from Clases_Curso where id=?"); //Preparación de sql parametrizada
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
				ClaseCurso claseCurso = new ClaseCurso();
				claseCurso.setId(rs.getInt(COLUMN_ID)); // Leemos una columna del resultset
				claseCurso.getClase().setId(rs.getInt(COLUMN_ID_CLASE)); // Leemos una columna del resultset
				claseCurso.getCurso().setId(rs.getInt(COLUMN_ID_CURSO)); // Leemos una columna del resultset
				claseCurso.getTutor().setId(rs.getInt(COLUMN_ID_TUTOR)); // Leemos una columna del resultset
				logger.debug("ClaseCurso "+ claseCurso.getClase().getId()+" "+claseCurso.getCurso().getId()+ " leído correctamente");
				logger.info("Localizado registro con identificador "+id);
				return claseCurso;
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
	public ClaseCurso insert(ClaseCurso claseCurso) {
		logger.debug("Entrando en el método insert");
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("INSERT INTO Clases_Curso(id_clase,id_curso,id_tutor) VALUES(?,?,?)"); //Preparación de sql parametrizada
			statement.setInt(1, claseCurso.getClase().getId());  //Establecemos el valor del primer parámetro de la consulta
			statement.setInt(2, claseCurso.getCurso().getId());  //Establecemos el valor del segundo parámetro de la consulta
			statement.setInt(3, claseCurso.getTutor().getId());  //Establecemos el valor del tercer parámetro de la consulta
		

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
				claseCurso.setId(rs.getInt(1)); //Solo habrá un único registro con el identificador autonumérico
				logger.debug("Obetenido identificador generado desde la base de datos");
				return claseCurso;
			} else {
				return null;
			}
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al obtener la clave primaria autogenerada",e);
			throw new RuntimeException("Ha ocurrido un error al obtener la clave primaria autogenerada", e);
		}
	}

	@Override
	public void update(ClaseCurso claseCurso) {
		logger.debug("Entrando en el método update");
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("UPDATE Clases_Curso SET id_clase=?,id_curso=?,id_tutor WHERE ID=?");  //Preparación de sql parametrizada
			statement.setInt(1, claseCurso.getClase().getId());  //Establecemos el valor del primer parámetro de la consulta
			statement.setInt(2, claseCurso.getCurso().getId());  //Establecemos el valor del segundo parámetro de la consulta
			statement.setInt(3, claseCurso.getTutor().getId());  //Establecemos el valor del tercer parámetro de la consulta
			statement.setInt(4, claseCurso.getId()); //Establecemos el valor del tercer parámetro de la consulta

		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al preparar la consulta",e);
			throw new RuntimeException("Ha ocurrido un error al preparar la consulta", e);
		}

		try {
			statement.executeUpdate(); //Ejecutamos consulta y no tenemos resultset porque no esperamos retorno de datos
			logger.info("Registro con identificador "+claseCurso.getId()+" actualizado correctamente");
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al ejecutar la consulta",e);
			throw new RuntimeException("Ha ocurrido un error al ejecutar la consulta", e);
		}

	}
	
	@Override
	public void delete(ClaseCurso claseCurso) {
		logger.debug("Entrando en el método delete");
		Connection conexion = ConnectionFactory.getConnection(); // Obtener conexión
		PreparedStatement statement = null;
		try {
			statement = conexion.prepareStatement("DELETE FROM Clases_Curso WHERE ID=?"); //Preparación de sql parametrizada
			statement.setInt(1, claseCurso.getId());//Establecemos el valor del primer parámetro de la consulta
			

		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al preparar la consulta",e);
			throw new RuntimeException("Error al preparar la consulta", e);
			
		}

		try {
			statement.executeUpdate(); //Ejecutamos consulta y no tenemos resultset porque no esperamos retorno de datos
			logger.info("Registro con identificador "+claseCurso.getId()+" borrado correctamente");
		} catch (SQLException e) {
			logger.error("Ha ocurrido un error al ejecutar la consulta de borrado del clase con id "+claseCurso.getId(),e);
			throw new RuntimeException("Error al ejecutar la consulta", e);
		}
	}
	

}
