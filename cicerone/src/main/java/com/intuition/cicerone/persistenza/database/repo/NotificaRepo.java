package com.intuition.cicerone.persistenza.database.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intuition.cicerone.persistenza.database.entity.NotificaEntity;

@Repository
public interface NotificaRepo extends CrudRepository<NotificaEntity, String>{

}
