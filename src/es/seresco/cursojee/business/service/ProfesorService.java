package es.seresco.cursojee.business.service;

import java.util.List;

import es.seresco.cursojee.business.dao.ProfesorDAO;
import es.seresco.cursojee.business.entities.Profesor;

public class ProfesorService {

	private ProfesorDAO profesorDAO;

	public ProfesorService() {
		super();
		profesorDAO = new ProfesorDAO();
	}

	public List<Profesor> findAll() {
		return profesorDAO.findAll();
	}

	public Integer count() {
		return profesorDAO.count();
	}

	public Profesor findById(Integer id) {
		return profesorDAO.findById(id);
	}
	
	public Profesor insert(Profesor profesor) {
		return profesorDAO.insert(profesor);
	}
	
	public void update(Profesor profesor) {
		profesorDAO.update(profesor);
	}
	
	public void delete(Profesor profesor) {
		profesorDAO.delete(profesor);
	}

}
