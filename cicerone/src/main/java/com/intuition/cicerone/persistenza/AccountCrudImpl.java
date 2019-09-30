package com.intuition.cicerone.persistenza;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intuition.cicerone.autenticazione.Account;
import com.intuition.cicerone.autenticazione.Anagrafica;
import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.autenticazione.IAnagrafica;
import com.intuition.cicerone.persistenza.database.entity.AccountEntity;
import com.intuition.cicerone.persistenza.database.entity.AnagraficaEntity;
import com.intuition.cicerone.persistenza.database.entity.ChiaveLinguaAccount;
import com.intuition.cicerone.persistenza.database.entity.LinguaAccountEntity;
import com.intuition.cicerone.persistenza.database.repo.AccountRepo;
import com.intuition.cicerone.persistenza.database.repo.AnagraficaRepo;
import com.intuition.cicerone.persistenza.database.repo.LinguaAccountRepo;

@Service
public class AccountCrudImpl implements AccountCrud {
	private AccountRepo accountRepo;
	private AnagraficaRepo anagraficaRepo;
	private LinguaAccountRepo linguaRepo;
	
	@Autowired
	public AccountCrudImpl(AccountRepo accountRepo, AnagraficaRepo anagraficaRepo, LinguaAccountRepo linguaRepo) {
		super();
		this.accountRepo = accountRepo;
		this.anagraficaRepo = anagraficaRepo;
		this.linguaRepo = linguaRepo;
	}
	
	@Override
	@Transactional
	public void salvaAccount(IAccount a) {
		AnagraficaEntity anagraficadb = new AnagraficaEntity();
		AccountEntity accountdb = new AccountEntity();
		LinguaAccountEntity linguaaccountdb;
		IAnagrafica anagrafica = a.getAnagrafica();
		anagraficadb.setDataDiNascita(anagrafica.getDataDiNascita());
		anagraficadb.setIdAnagrafica(anagrafica.getIdAnagrafica());
		anagraficadb.setNome(anagrafica.getNome());
		anagraficadb.setCognome(anagrafica.getCognome());
		anagraficadb.setSesso((anagrafica.getSesso().getValore() == 1));
		anagraficadb.setCitta(anagrafica.getPaeseCitta());
		anagraficadb.setNumeroDiTelefono(anagrafica.getNumeroDiTelefono());

		accountdb.setIdAccount(a.getIdAccount());
		accountdb.setEmail(a.getEmail());
		accountdb.setIdAnagrafica(anagrafica.getIdAnagrafica());
		accountdb.setPassword(a.getPassword());
		accountdb.setRuolo(a.getRuolo() == null ? "Globetrotter" : a.getRuolo());
		accountdb.setNumeroNotifiche(Math.toIntExact(a.getContatoreNotifiche()));
		accountRepo.save(accountdb);
		anagraficaRepo.save(anagraficadb);
		if (anagrafica.getLingueParlate() != null) {
			linguaRepo.deleteByChiaveCompostaIdAnagrafica(anagrafica.getIdAnagrafica());
			for (String lingua : anagrafica.getLingueParlate()) {
				linguaaccountdb = new LinguaAccountEntity();
				ChiaveLinguaAccount key = new ChiaveLinguaAccount();
				key.setIdAnagrafica(anagrafica.getIdAnagrafica());
				key.setLingua(lingua);
				linguaaccountdb.setChiaveComposta(key);
				linguaRepo.save(linguaaccountdb);
			}
		}
	}

	@Override
	public Boolean esisteAccountById(String id) {
		return accountRepo.findById(id).isPresent();
	}
	
	public Boolean esisteByTelefono(String nTelefono) {
		return anagraficaRepo.existsByNumeroDiTelefono(nTelefono);
	}

	@Override
	public Boolean esisteAccountByEmail(String email) {
		return !accountRepo.findByEmail(email).isEmpty();
	}

	@Override
	public void cancellaAccount(IAccount a) {
		accountRepo.deleteById(a.getIdAccount());
		IAnagrafica  anagrafica = a.getAnagrafica();
		anagraficaRepo.deleteById(anagrafica.getIdAnagrafica());
		List<LinguaAccountEntity> lingue = linguaRepo.findByChiaveCompostaIdAnagrafica(anagrafica.getIdAnagrafica());
		linguaRepo.deleteAll(lingue);
	}

	@Override
	public Optional<IAccount> trovaAccountByEmail(String email) {
		Optional<IAccount> result = Optional.empty();
		List<AccountEntity> accountentity = accountRepo.findByEmail(email);

		if (!accountentity.isEmpty()) {
			AccountEntity account = accountentity.get(0);
			result = this.toIAccount(account);
		}
		return result;
	}

	@Override
	public Optional<IAccount> trovaAccountById(String id) {
		Optional<IAccount> result = Optional.empty();
		Optional<AccountEntity> entityAccount = accountRepo.findById(id);
		if (entityAccount.isPresent()) {
			result = this.toIAccount(entityAccount.get());
		}
		return result;
	}
	
	private Optional<IAccount> toIAccount(AccountEntity account) {
		Optional<AnagraficaEntity> optanagrafica = anagraficaRepo.findById(account.getIdAnagrafica());
		if ( optanagrafica.isPresent()) {
			AnagraficaEntity anagrafica = optanagrafica.get();
			List<LinguaAccountEntity> lingue = linguaRepo.findByChiaveCompostaIdAnagrafica(anagrafica.getIdAnagrafica());
			Set<String> lingueAccount = lingue.stream()
					.map(LinguaAccountEntity::getChiaveComposta)
					.map(ChiaveLinguaAccount::getLingua)
					.collect(Collectors.toSet());
			
			IAnagrafica a = new Anagrafica.BuilderAnagrafica().setNome(anagrafica.getNome())
					.setCognome(anagrafica.getCognome()).setSesso(anagrafica.getSesso() ? Anagrafica.Sesso.DONNA : Anagrafica.Sesso.UOMO)
					.setNumeroDiTelefono(anagrafica.getNumeroDiTelefono()).setPaeseCitta(anagrafica.getCitta())
					.setDataDiNascita(anagrafica.getDataDiNascita()).setLingueParlate(lingueAccount)
					.setIdAnagrafica(anagrafica.getIdAnagrafica())
					.build();

			IAccount acco = new Account.BuilderAccount()
					.setIdAccount(account.getIdAccount())
					.setAnagrafica(a)
					.setEmail(account.getEmail())
					.setPassword(account.getPassword())
					.setContatoreNotifica(Integer.toUnsignedLong(account.getNumeroNotifiche()))
					.setRuolo(account.getRuolo())
					.build();
			return Optional.of(acco);
		}
		return Optional.empty();
	}




}
