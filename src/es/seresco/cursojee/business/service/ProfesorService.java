package es.seresco.cursojee.business.service;

import java.util.Iterator;
import java.util.List;

import es.seresco.cursojee.business.dao.ProfesorDAO;
import es.seresco.cursojee.business.entities.ClaseCurso;
import es.seresco.cursojee.business.entities.Profesor;

public class ProfesorService {

	private ProfesorDAO profesorDAO;
	
	private ClaseCursoService claseCursoService;

	public ProfesorService() {
		super();
		profesorDAO = new ProfesorDAO();
		claseCursoService = new ClaseCursoService();
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
		
		//Comprobamos si el profesor está en algún curso.
		
		List <ClaseCurso> clasesCurso= claseCursoService.findAll();
		
		for (ClaseCurso claseCurso : clasesCurso) {
			if(profesor.getId().equals(claseCurso.getTutor().getId() )) {
				//claseCursoService.delete(claseCurso);
				throw new RuntimeException("No se puede borrar el profesor porque está asignado como tutor en una clase");
			}
		
	}
		
		
		profesorDAO.delete(profesor);
	}

}
