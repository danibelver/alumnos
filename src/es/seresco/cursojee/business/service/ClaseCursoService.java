package es.seresco.cursojee.business.service;

import java.util.List;

import es.seresco.cursojee.business.dao.ClaseCursoDAO;
import es.seresco.cursojee.business.entities.Clase;
import es.seresco.cursojee.business.entities.ClaseCurso;
import es.seresco.cursojee.business.entities.Curso;
import es.seresco.cursojee.business.entities.Profesor;

public class ClaseCursoService {

	private ClaseCursoDAO claseCursoDAO;
	
	private ProfesorService profesorService;
	
	private ClaseService claseService;
	
	private CursoService cursoService;

	public ClaseCursoService() {
		super();
		claseCursoDAO = new ClaseCursoDAO();
		profesorService = new ProfesorService();
		claseService = new ClaseService();
		cursoService = new CursoService();
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
		
		Clase clase = claseService.findById(claseCurso.getClase().getId());
		claseCurso.setClase(clase);
		
		Curso curso = cursoService.findById(claseCurso.getCurso().getId());
		claseCurso.setCurso(curso);
		
		
		return claseCurso;
	}

}
