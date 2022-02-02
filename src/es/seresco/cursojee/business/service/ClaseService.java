package es.seresco.cursojee.business.service;

import java.util.List;

import es.seresco.cursojee.business.dao.ClaseDAO;
import es.seresco.cursojee.business.entities.Clase;

public class ClaseService {

	private ClaseDAO claseDAO;

	public ClaseService() {
		super();
		claseDAO = new ClaseDAO();
	}

	public List<Clase> findAll() {
		return claseDAO.findAll();
	}

	public Integer count() {
		return claseDAO.count();
	}

	public Clase findById(Integer id) {
		return claseDAO.findById(id);
	}

	public Clase insert(Clase clase) {
		return claseDAO.insert(clase);
	}

	public void update(Clase clase) {
		claseDAO.update(clase);
	}

}
