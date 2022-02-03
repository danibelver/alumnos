package es.seresco.cursojee.view.test;

import org.apache.log4j.Logger;

import es.seresco.cursojee.business.service.ClaseService;

public class Principal {
	
	final static Logger logger = Logger.getLogger(Principal.class);

	public static void main(String[] args) {

		ClaseService claseService=new ClaseService();
		claseService.findAll();
		logger.debug("Todo OK");

	}

}
