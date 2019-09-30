package com.intuition.cicerone.persistenza.database.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intuition.cicerone.persistenza.database.entity.RichiestaEntity;

@Repository
public interface RichiestaRepo extends CrudRepository<RichiestaEntity, String>{
	List<RichiestaEntity> findByIdRichiedente(String idRichiedente);
	List<RichiestaEntity> findByIdAttivita(String idAttivita);
	void deleteByIdAttivita(String idAttivita);
}
