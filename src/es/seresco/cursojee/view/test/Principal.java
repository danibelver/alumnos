package es.seresco.cursojee.view.test;

import java.util.List;

import org.apache.log4j.Logger;

import es.seresco.cursojee.business.entities.Alumno;
import es.seresco.cursojee.business.entities.Profesor;
import es.seresco.cursojee.business.service.AlumnoService;
import es.seresco.cursojee.business.service.ProfesorService;

public class Principal {
	
	final static Logger logger = Logger.getLogger(Principal.class);

	public static void main(String[] args) {

		ProfesorService profesorService = new ProfesorService();
		Profesor profesor = new Profesor();
		profesor.setId(14);
		profesorService.delete(profesor);
		logger.debug("Todo OK");

	}

}
