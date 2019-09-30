package com.intuition.cicerone.persistenza;

import java.util.Optional;

import com.intuition.cicerone.autenticazione.IAccount;

public interface AccountCrud {

	void salvaAccount(IAccount a);

	Boolean esisteAccountById(String id);
	
	Boolean esisteByTelefono(String nTelefono);

	Boolean esisteAccountByEmail(String email);

	void cancellaAccount(IAccount a);

	Optional<IAccount> trovaAccountByEmail(String email);

	Optional<IAccount> trovaAccountById(String id);

}