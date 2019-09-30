package com.intuition.cicerone.persistenza;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.gestioneattivita.attivita.Attivita;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.attivita.IItinerario;
import com.intuition.cicerone.gestioneattivita.attivita.Itinerario;
import com.intuition.cicerone.gestioneattivita.attivita.StatoAttivitaFactory;
import com.intuition.cicerone.persistenza.database.entity.AttivitaEntity;
import com.intuition.cicerone.persistenza.database.entity.ChiaveLinguaAttivita;
import com.intuition.cicerone.persistenza.database.entity.ChiavePartecipanti;
import com.intuition.cicerone.persistenza.database.entity.ItinerarioEntity;
import com.intuition.cicerone.persistenza.database.entity.LinguaAttivitaEntity;
import com.intuition.cicerone.persistenza.database.entity.PartecipantiEntity;
import com.intuition.cicerone.persistenza.database.repo.AttivitaRepo;
import com.intuition.cicerone.persistenza.database.repo.ItinerarioRepo;
import com.intuition.cicerone.persistenza.database.repo.LinguaAttivitaRepo;
import com.intuition.cicerone.persistenza.database.repo.PartecipanteRepo;

@Service
public class AttivitaCrudImpl implements AttivitaCrud {
	private AttivitaRepo attivitaRepo;
	private ItinerarioRepo itiRepo;
	private LinguaAttivitaRepo lingueRepo;
	private PartecipanteRepo partecipantiRepo;
	private AccountCrud accountCrud;

	@Autowired
	public AttivitaCrudImpl(AttivitaRepo attivitaRepo, ItinerarioRepo itiRepo, LinguaAttivitaRepo lingueRepo, AccountCrud accountCrud, PartecipanteRepo partecipantiRepo) {
		super();
		this.attivitaRepo = attivitaRepo;
		this.itiRepo = itiRepo;
		this.lingueRepo = lingueRepo;
		this.accountCrud = accountCrud;
		this.partecipantiRepo = partecipantiRepo;
	}

	@Override
	@Transactional
	public boolean salvaAttivita(IAttivita attivita) {
		IItinerario i = attivita.getItinerario();
		ItinerarioEntity ie = new ItinerarioEntity();
		ie.setDataIncontro(i.getDataIncontro());
		ie.setDescrizioneAttivita(i.getDescrizioneAttivita());
		ie.setIdItinerario(i.getIdItinerario());
		ie.setNomeLuogo(i.getLuogo());
		ie.setOraIncontro(i.getOraIncontro());
		ie.setRetribuzione(i.getRetribuzione().getValore() == 1);
		ie.setValoreRetribuzione(i.getValoreRetribuzione());
		ie.setxIncontro(i.getxIncontro());
		ie.setyIncontro(i.getyIncontro());
		itiRepo.save(ie);

		AttivitaEntity a = new AttivitaEntity();
		a.setDataApertura(attivita.getDataApertura());
		a.setDataModifica(attivita.getDataModifica() == null ? attivita.getDataApertura() : attivita.getDataModifica());
		a.setIdAttivita(attivita.getIdAttivita());
		a.setIdCreatore(attivita.getCreatore().getIdAccount());
		a.setIdItinerario(i.getIdItinerario());
		a.setStatoAttivita(attivita.getStatoAttivita().toString());
		a.setTitoloAttivita(attivita.getTitolo());
		attivitaRepo.save(a);


		Set<IAccount> partecipanti = attivita.getPartecipanti();
		if(partecipanti != null && !partecipanti.isEmpty()) {
			for (IAccount acc : partecipanti) {
				ChiavePartecipanti k = new ChiavePartecipanti();
				k.setIdAccount(acc.getIdAccount());
				k.setIdAttivita(attivita.getIdAttivita());
				PartecipantiEntity p = new PartecipantiEntity();
				p.setChiavePartecipanti(k);
				partecipantiRepo.save(p); 
			}
		}


		Set<String> lingua = i.getLingua();
		if (lingua != null && !lingua.isEmpty()) {
			for (String s : lingua) {
				ChiaveLinguaAttivita k = new ChiaveLinguaAttivita();
				k.setIdItinerario(i.getIdItinerario());
				k.setLingua(s);
				LinguaAttivitaEntity l = new LinguaAttivitaEntity();
				l.setChiaveLinguaAttivita(k);
				lingueRepo.save(l);
			}
		}
		return true;
	}

	@Override
	public Set<String> trovaLingue(String idItinerario) {
		List<LinguaAttivitaEntity> lingueEntity = lingueRepo.findByChiaveLinguaAttivitaIdItinerario(idItinerario);
		return lingueEntity.stream()
				.map(LinguaAttivitaEntity::getChiaveLinguaAttivita)
				.map(ChiaveLinguaAttivita::getLingua)
				.collect(Collectors.toSet());
	}


	@Override
	public Set<IAccount> trovaPartecipanti(String idAttivita) {
		List<PartecipantiEntity> partecipantiEntity = partecipantiRepo.findByChiavePartecipantiIdAttivita(idAttivita);
		List<String> partecipantiId = partecipantiEntity.stream()
				.map(PartecipantiEntity::getChiavePartecipanti)
				.map(ChiavePartecipanti::getIdAccount)
				.collect(Collectors.toList());
		Set<IAccount> partecipanti = new HashSet<>();
		for (String id : partecipantiId) {
			Optional<IAccount> optpartecipanti = accountCrud.trovaAccountById(id);
			if (optpartecipanti.isPresent()) {
				partecipanti.add(optpartecipanti.get());
			}
		}
		return partecipanti;
	}


	@Override
	public Optional<IItinerario> trovaItinerario(String idItinerario) {
		Optional<IItinerario> result = Optional.empty();
		Optional<ItinerarioEntity> optionalItinerario = itiRepo.findById(idItinerario);
		if (optionalItinerario.isPresent()) {
			ItinerarioEntity ie = optionalItinerario.get();
			Set<String> lingue = trovaLingue(ie.getIdItinerario());
			IItinerario i = new Itinerario.BuilderItinerario()
					.setIdItinerario(ie.getIdItinerario())
					.setDescrizioneAttivita(ie.getDescrizioneAttivita())
					.setRetribuzione(ie.getRetribuzione() ? Itinerario.Retribuzione.RETRIBUITO : Itinerario.Retribuzione.NONRETRIBUITO, ie.getValoreRetribuzione())
					.setLingua(lingue)
					.build();
			i.setCoordinateIncontro(ie.getxIncontro(), ie.getyIncontro());
			i.setDataIncontro(ie.getDataIncontro());
			i.setOraIncontro(ie.getOraIncontro());
			i.setLuogo(ie.getNomeLuogo());
			result = Optional.of(i);
		}
		return result;
	}

	@Override
	public Optional<IAttivita> trovaAttivita(String idAttivita, Set<IAccount> partecipanti) {
		Optional<IAttivita> result = Optional.empty();
		Optional<AttivitaEntity> optionalAttivitaEntity = attivitaRepo.findById(idAttivita);
		if (optionalAttivitaEntity.isPresent()) {
			AttivitaEntity ae = optionalAttivitaEntity.get();
			Optional<IItinerario> optionalItinerario = trovaItinerario(ae.getIdItinerario());
			if (optionalItinerario.isPresent()) {
				IItinerario i = optionalItinerario.get();
				Optional<IAccount> optionalCreatore = accountCrud.trovaAccountById(ae.getIdCreatore());
				if (optionalCreatore.isPresent()) {
					IAccount creatore = optionalCreatore.get();
					IAttivita attivita = new Attivita.BuilderAttivita()
							.setCreatore(creatore)
							.setDataApertura(ae.getDataApertura())
							.setIdAttivita(ae.getIdAttivita())
							.setItinerario(i)
							.setTitolo(ae.getTitoloAttivita())
							.build();
					attivita.setDataModifica(ae.getDataModifica());
					StatoAttivitaFactory factory = new StatoAttivitaFactory(attivita);
					factory.crea(ae.getStatoAttivita());
					attivita.setStatoAttivita(factory.crea(ae.getStatoAttivita()));
					if (partecipanti != null) {
						attivita.setPartecipanti(partecipanti);
					}
					result = Optional.of(attivita);
				}
			}
		}
		return result;
	}
	
	@Override
	public List<IAttivita> trovaAttivitaByIdCreatore(String idCreatore) {
		List<AttivitaEntity> listaAttivita = attivitaRepo.findByIdCreatore(idCreatore);
		return convertiListaAttivita(listaAttivita);
	}
	
	@Override
	public List<IAttivita> trovaAttivitaByStato(String stato) {
		List<AttivitaEntity> listaAttivita = attivitaRepo.findByStatoAttivita(stato);
		return convertiListaAttivita(listaAttivita);
	}
	
	private List<IAttivita> convertiListaAttivita(List<AttivitaEntity> listaAttivita) {
		List<IAttivita> result = new ArrayList<>();
		Optional<IAttivita> optAttivita;
		for (AttivitaEntity ae : listaAttivita) {
			optAttivita = trovaAttivita(ae.getIdAttivita(), null);
			if (optAttivita.isPresent()) {
				result.add(optAttivita.get());
			}
		}
		return result;
	}

	@Override
	@Transactional
	public boolean eliminaAttivita(IAttivita a) {
		attivitaRepo.deleteById(a.getIdAttivita());
		itiRepo.deleteById(a.getItinerario().getIdItinerario());
		lingueRepo.deleteByChiaveLinguaAttivitaIdItinerario(a.getItinerario().getIdItinerario());
		partecipantiRepo.deleteByChiavePartecipantiIdAttivita(a.getIdAttivita());
		return true;
	}

	@Override
	@Transactional
	public boolean eliminaAttivitaById(String idAttivita) {
		Optional<IItinerario> it = trovaItinerario(idAttivita);
		if (it.isPresent()) {
			attivitaRepo.deleteById(idAttivita);
			IItinerario itinerario = it.get();
			itiRepo.deleteById(itinerario.getIdItinerario());
			lingueRepo.deleteByChiaveLinguaAttivitaIdItinerario(itinerario.getIdItinerario());
			partecipantiRepo.deleteByChiavePartecipantiIdAttivita(idAttivita);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean eliminaPartecipante(String idAccount) {
		partecipantiRepo.deleteByChiavePartecipantiIdAccount(idAccount);
		return true;
	}

}
