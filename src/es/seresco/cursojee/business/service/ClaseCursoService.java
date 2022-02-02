package es.seresco.cursojee.business.service;

import java.util.List;

import es.seresco.cursojee.business.dao.ClaseCursoDAO;
import es.seresco.cursojee.business.entities.ClaseCurso;
import es.seresco.cursojee.business.entities.Profesor;

public class ClaseCursoService {

	private ClaseCursoDAO claseCursoDAO;
	
	private ProfesorService profesorService;

	public ClaseCursoService() {
		super();
		claseCursoDAO = new ClaseCursoDAO();
		profesorService = new ProfesorService();
	}

	public List<ClaseCurso> findAll() {
		return claseCursoDAO.findAll();
	}

	public Integer count() {
		return claseCursoDAO.count();
	}

	public ClaseCurso findById(Integer id) {
		ClaseCurso claseCurso= claseCursoDAO.findById(id);
		Profesor profesor=profesorService.findById(claseCurso.getTutor().getId());
		claseCurso.setTutor(profesor);
		/*
		 * TODO: Habría que hacerlo con clase y curso.
		 */
		return claseCurso;
	}

}
