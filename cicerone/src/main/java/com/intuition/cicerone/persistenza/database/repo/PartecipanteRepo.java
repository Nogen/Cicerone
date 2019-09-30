package com.intuition.cicerone.persistenza.database.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intuition.cicerone.persistenza.database.entity.ChiavePartecipanti;
import com.intuition.cicerone.persistenza.database.entity.PartecipantiEntity;

@Repository
public interface PartecipanteRepo extends CrudRepository<PartecipantiEntity, ChiavePartecipanti>{
	List<PartecipantiEntity> findByChiavePartecipantiIdAttivita(String idAttivita);
	List<PartecipantiEntity> findByChiavePartecipantiIdAccount(String idAccount);
	void deleteByChiavePartecipantiIdAttivita(String idAttivita);
	void deleteByChiavePartecipantiIdAccount(String idAccount);
}
