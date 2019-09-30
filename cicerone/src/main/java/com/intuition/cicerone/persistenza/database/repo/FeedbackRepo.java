package com.intuition.cicerone.persistenza.database.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intuition.cicerone.persistenza.database.entity.FeedbackEntity;

@Repository
public interface FeedbackRepo extends CrudRepository<FeedbackEntity, String>{
	Optional<FeedbackEntity> findByIdAttivitaAndIdAccount(String idAttivita, String idAccount);
	List<FeedbackEntity> findByIdAttivita(String idAttivita);
	List<FeedbackEntity> findByIdAccount(String idAccount);
	void deleteByIdAttivita(String idAttivita);

}
