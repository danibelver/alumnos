package es.seresco.cursojee.business.entities;

public class Clase extends ColegioBaseEntity{
	private Integer id;
	private Integer curso;
	private String letra;

	public Clase() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

}
