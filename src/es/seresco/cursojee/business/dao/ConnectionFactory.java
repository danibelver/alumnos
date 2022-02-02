package es.seresco.cursojee.business.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;


public class ConnectionFactory {

	private static final String URL = "jdbc:mysql://localhost:3306/test";

	private static final String USER = "seresco";

	private static final String PASSWORD = "Seresco01";

	private ConnectionFactory() {

	}

	public static Connection getConnection() {
		try {
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e) {
			throw new RuntimeException("Error al registrar el driver",e);
		}
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la conexión",e);
		}
	}

}
