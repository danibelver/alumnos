package es.seresco.cursojee.view.test;

import java.util.Iterator;
import java.util.List;

import es.seresco.cursojee.business.entities.Alumno;
import es.seresco.cursojee.business.service.AlumnoService;

public class Principal {

	public static void main(String[] args) {

		AlumnoService alumnoService = new AlumnoService();
		List<Alumno> listaAlumnos = alumnoService.findAll();
		System.out.println("Número de alumnos: " + listaAlumnos.size());

		Alumno alumno = alumnoService.findById(1);
		System.out.println("-" + alumno.getNombre() + " " + alumno.getApellido());

	}

}
