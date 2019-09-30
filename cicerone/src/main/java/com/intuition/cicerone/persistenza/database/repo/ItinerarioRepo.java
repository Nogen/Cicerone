package com.intuition.cicerone.persistenza.database.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intuition.cicerone.persistenza.database.entity.ItinerarioEntity;

@Repository
public interface ItinerarioRepo extends CrudRepository<ItinerarioEntity, String>{

}
