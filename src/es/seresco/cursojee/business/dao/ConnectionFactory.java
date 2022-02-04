package es.seresco.cursojee.business.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.mysql.cj.jdbc.Driver;

public class ConnectionFactory {

	private static final String URL = "bbdd.url";

	private static final String USER = "bbdd.user";

	private static final String PASSWORD = "bbdd.password";

	final static Logger logger = Logger.getLogger(ConnectionFactory.class);

	private static final String CONFIGURATION_FILE = "configuration.properties";
	

	private ConnectionFactory() {

	}

	public static Connection getConnection() {

		Properties properties = new Properties();

		try {
			properties.load(new FileInputStream(new File(CONFIGURATION_FILE)));

			String urlConexion = (String) properties.get(URL);
			String user = (String) properties.get(USER);
			String password = (String) properties.get(PASSWORD);
		} catch (FileNotFoundException e) {
			logger.error("No se encuentra el archivo", e);
		} catch (IOException e) {
			// logger.error("Error al registrar el driver de mysql", e);
		}

		try {
			logger.debug("Se va a realizar una coonexión con la base de datos");
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e) {
			logger.error("Error al registrar el driver de mysql", e);
			throw new RuntimeException("Error al registrar el driver", e);
		}
		try {
			Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
			logger.debug("Conexión con la base de datos realizada correctamente.");
			return conexion;
		} catch (SQLException e) {
			logger.error("Error al obtener la conexión con la base de datos", e);
			throw new RuntimeException("Error al obtener la conexión", e);
		}
	}

}
