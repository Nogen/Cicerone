package com.intuition.cicerone.persistenza.database.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intuition.cicerone.persistenza.database.entity.ChiaveLinguaAttivita;
import com.intuition.cicerone.persistenza.database.entity.LinguaAttivitaEntity;

@Repository
public interface LinguaAttivitaRepo extends CrudRepository<LinguaAttivitaEntity, ChiaveLinguaAttivita> {
	List<LinguaAttivitaEntity> findByChiaveLinguaAttivitaIdItinerario(String idItinerario);
	List<LinguaAttivitaEntity> findByChiaveLinguaAttivitaLingua(String lingua);
	void deleteByChiaveLinguaAttivitaIdItinerario(String idItinerario);

}
