package es.seresco.cursojee.business.service;

import java.util.List;

import es.seresco.cursojee.business.dao.AlumnoDAO;
import es.seresco.cursojee.business.entities.Alumno;

public class AlumnoService {

	private AlumnoDAO alumnoDAO;

	public AlumnoService() {
		super();
		alumnoDAO = new AlumnoDAO();
	}

	public List<Alumno> findAll() {
		return alumnoDAO.findAll();
	}

	public Integer count() {
		return alumnoDAO.count();
	}

	public Alumno findById(Integer id) {
		return alumnoDAO.findById(id);
	}

}
