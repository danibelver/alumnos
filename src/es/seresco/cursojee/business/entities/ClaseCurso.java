package es.seresco.cursojee.business.entities;

public class ClaseCurso extends ColegioBaseEntity {
	
	private Integer id;
	
	private Clase clase;
	
	private Curso curso;
	
	private Profesor tutor;
	public ClaseCurso() {
		super();
		clase = new Clase();
		tutor = new Profesor();
		curso = new Curso();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Clase getClase() {
		return clase;
	}
	public void setClase(Clase clase) {
		this.clase = clase;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Profesor getTutor() {
		return tutor;
	}
	public void setTutor(Profesor tutor) {
		this.tutor = tutor;
	}
	
	
	
	

}
