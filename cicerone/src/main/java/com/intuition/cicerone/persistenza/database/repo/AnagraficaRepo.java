package com.intuition.cicerone.persistenza.database.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intuition.cicerone.persistenza.database.entity.AnagraficaEntity;

@Repository
public interface AnagraficaRepo extends CrudRepository<AnagraficaEntity, String>{
	boolean existsByNumeroDiTelefono(String numeroDiTelefono);

}
