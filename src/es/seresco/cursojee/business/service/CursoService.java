package es.seresco.cursojee.business.service;

import java.util.List;

import es.seresco.cursojee.business.dao.CursoDAO;
import es.seresco.cursojee.business.entities.Curso;

public class CursoService {

	private CursoDAO cursoDAO;

	public CursoService() {
		super();
		cursoDAO = new CursoDAO();
	}

	public List<Curso> findAll() {
		return cursoDAO.findAll();
	}

	public Integer count() {
		return cursoDAO.count();
	}

	public Curso findById(Integer id) {
		return cursoDAO.findById(id);
	}

	public Curso insert(Curso curso) {
		return cursoDAO.insert(curso);
	}

	public void update(Curso curso) {
		cursoDAO.update(curso);
	}

}
