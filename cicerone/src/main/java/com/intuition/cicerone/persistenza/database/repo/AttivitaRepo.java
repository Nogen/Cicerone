package com.intuition.cicerone.persistenza.database.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intuition.cicerone.persistenza.database.entity.AttivitaEntity;

@Repository
public interface AttivitaRepo extends CrudRepository<AttivitaEntity, String>{
	List<AttivitaEntity> findByStatoAttivita(String statoAttivita);
	List<AttivitaEntity> findByIdCreatore(String idCreatore);
	Page<AttivitaEntity> findByIdCreatore(String idCreatore, Pageable page);
	Page<AttivitaEntity> findAll(Pageable page);

}
