package es.seresco.cursojee.business.service;

public class ServiceFactory {
	
	
	private ClaseCursoService claseCursoService;
	private AlumnoService alumnoService;
	private ProfesorService profesorService;
	private CursoService cursoService;
	private ClaseService claseService;
	
	private static ServiceFactory serviceFactory;
	
	
	private ServiceFactory() {
		super();
		
	}
	
	
	public static ServiceFactory getInstance() {
		
		if(serviceFactory == null) {
			serviceFactory = new ServiceFactory();
			serviceFactory.setClaseCursoService(new ClaseCursoService());
			serviceFactory.setAlumnoService(new AlumnoService());
			serviceFactory.setClaseService(new ClaseService());
			serviceFactory.setProfesorService(new ProfesorService());
			serviceFactory.setCursoService(new CursoService());
		}
		return serviceFactory;
		
	}


	public ClaseCursoService getClaseCursoService() {
		return claseCursoService;
	}


	private void setClaseCursoService(ClaseCursoService claseCursoService) {
		this.claseCursoService = claseCursoService;
	}


	public AlumnoService getAlumnoService() {
		return alumnoService;
	}


	private void setAlumnoService(AlumnoService alumnoService) {
		this.alumnoService = alumnoService;
	}


	public ProfesorService getProfesorService() {
		return profesorService;
	}


	private void setProfesorService(ProfesorService profesorService) {
		this.profesorService = profesorService;
	}


	public CursoService getCursoService() {
		return cursoService;
	}


	private void setCursoService(CursoService cursoService) {
		this.cursoService = cursoService;
	}


	public ClaseService getClaseService() {
		return claseService;
	}


	private void setClaseService(ClaseService claseService) {
		this.claseService = claseService;
	}
	
	
	
	
	
	
	

}
