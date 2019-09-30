package com.intuition.cicerone.persistenza.database.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intuition.cicerone.persistenza.database.entity.ChiaveFoto;
import com.intuition.cicerone.persistenza.database.entity.FotoItinerarioEntity;

@Repository
public interface FotoItinerarioRepo extends CrudRepository<FotoItinerarioEntity, ChiaveFoto>{
	void deleteByChiaveFotoIdItinerario(String idItinerario);
	List<FotoItinerarioEntity> findByChiaveFotoIdItinerario(String idItinerario);
}
