package es.seresco.cursojee.business.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mysql.cj.jdbc.Driver;

import es.seresco.cursojee.view.test.Principal;


public class ConnectionFactory {

	private static final String URL = "jdbc:mysql://localhost:3306/test";

	private static final String USER = "seresco";

	private static final String PASSWORD = "Seresco01";
	
	final static Logger logger = Logger.getLogger(ConnectionFactory.class);
	

	private ConnectionFactory() {

	}

	public static Connection getConnection() {
		try {
			logger.debug("Se va a realizar una coonexión con la base de datos");
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e) {
			logger.error("Error al registrar el driver de mysql",e);
			throw new RuntimeException("Error al registrar el driver",e);
		}
		try {
			Connection conexion= DriverManager.getConnection(URL, USER, PASSWORD);
			logger.debug("Conexión con la base de datos realizada correctamente.");
			return conexion;
		} catch (SQLException e) {
			logger.error("Error al obtener la conexión con la base de datos",e);
			throw new RuntimeException("Error al obtener la conexión",e);
		}
	}

}
