package com.intuition.cicerone.persistenza.database.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intuition.cicerone.persistenza.database.entity.ChiaveLinguaAccount;
import com.intuition.cicerone.persistenza.database.entity.LinguaAccountEntity;

@Repository
public interface LinguaAccountRepo extends CrudRepository<LinguaAccountEntity, ChiaveLinguaAccount> {
	List<LinguaAccountEntity> findByChiaveCompostaIdAnagrafica(String idAnagrafica);
	void deleteByChiaveCompostaIdAnagrafica(String idAnagrafica);
}
