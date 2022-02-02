package es.seresco.cursojee.business.dao;

import java.util.List;

import es.seresco.cursojee.business.entities.ColegioBaseEntity;

public interface IColegioBaseDAO <T extends ColegioBaseEntity> {
	
	List <T> findAll();
	
	T findById(Integer id);
	
	Integer count();
	
	T insert(T objetoAInsertar);

}
