package es.seresco.cursojee.view.test;

import org.apache.log4j.Logger;

import es.seresco.cursojee.business.service.AlumnoService;
import es.seresco.cursojee.business.service.ClaseService;

public class Principal {
	
	final static Logger logger = Logger.getLogger(Principal.class);

	public static void main(String[] args) {

		AlumnoService alumnoService=new AlumnoService();
		alumnoService.findById(2);
		logger.debug("Todo OK");

	}

}
