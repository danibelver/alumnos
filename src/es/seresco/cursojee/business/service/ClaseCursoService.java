package es.seresco.cursojee.business.service;

import java.util.List;

import es.seresco.cursojee.business.dao.ClaseCursoDAO;
import es.seresco.cursojee.business.entities.Clase;
import es.seresco.cursojee.business.entities.ClaseCurso;
import es.seresco.cursojee.business.entities.Curso;
import es.seresco.cursojee.business.entities.Profesor;

public class ClaseCursoService {

	private ClaseCursoDAO claseCursoDAO;


	public ClaseCursoService() {
		super();
		claseCursoDAO = new ClaseCursoDAO();
	}

	public List<ClaseCurso> findAll() {
		return claseCursoDAO.findAll();
	}

	public Integer count() {
		return claseCursoDAO.count();
	}

	public ClaseCurso findById(Integer id) {
		
		ClaseService claseService= new ClaseService();
		CursoService cursoService = new CursoService();
		ProfesorService profesorService = new ProfesorService();
		ClaseCurso claseCurso = claseCursoDAO.findById(id);

		Profesor profesor = profesorService.findById(claseCurso.getTutor().getId());
		claseCurso.setTutor(profesor);

		Clase clase = claseService.findById(claseCurso.getClase().getId());
		claseCurso.setClase(clase);

		Curso curso = cursoService.findById(claseCurso.getCurso().getId());
		claseCurso.setCurso(curso);

		return claseCurso;
	}

	public ClaseCurso insert(ClaseCurso claseCurso) {
		return claseCursoDAO.insert(claseCurso);
	}

	public void update(ClaseCurso claseCurso) {
		claseCursoDAO.update(claseCurso);
	}

	public void delete(ClaseCurso claseCurso) {
		claseCursoDAO.delete(claseCurso);
	}

	public ClaseCursoDAO getClaseCursoDAO() {
		return claseCursoDAO;
	}

	public void setClaseCursoDAO(ClaseCursoDAO claseCursoDAO) {
		this.claseCursoDAO = claseCursoDAO;
	}


	

}
