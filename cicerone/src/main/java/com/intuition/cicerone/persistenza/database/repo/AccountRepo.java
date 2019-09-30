package com.intuition.cicerone.persistenza.database.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intuition.cicerone.persistenza.database.entity.AccountEntity;


@Repository
public interface AccountRepo extends CrudRepository<AccountEntity, String>{
	List<AccountEntity> findByEmail(String email);

}
