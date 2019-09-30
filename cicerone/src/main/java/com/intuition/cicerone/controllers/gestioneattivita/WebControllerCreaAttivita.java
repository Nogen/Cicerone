package com.intuition.cicerone.controllers.gestioneattivita;
import static com.intuition.cicerone.controllers.WebConstant.HIDDEN;
import static com.intuition.cicerone.controllers.WebConstant.HIDE;
import static com.intuition.cicerone.controllers.WebConstant.LINK;
import static com.intuition.cicerone.controllers.WebConstant.LINK_MESSAGE;
import static com.intuition.cicerone.controllers.WebConstant.MESSAGE;
import static com.intuition.cicerone.controllers.WebConstant.NULL_VALUE;
import static com.intuition.cicerone.controllers.WebConstant.TYPE;
import static com.intuition.cicerone.controllers.WebConstant.TYPE_DANGER;
import static com.intuition.cicerone.controllers.WebConstant.TYPE_SUCCESS;
import static com.intuition.cicerone.controllers.WebConstant.USER_ACCOUNT;
import static com.intuition.cicerone.controllers.WebsiteIndice.ATTIVITA_CREATEURL;
import static com.intuition.cicerone.controllers.WebsiteIndice.CREA_ATTIVITA;
import static com.intuition.cicerone.controllers.WebsiteIndice.CREA_ATTIVITAJSP;
import static com.intuition.cicerone.gestioneattivita.ErrorMessage.ATTIVITA_CREATA_CON_SUCCESSO;
import static com.intuition.cicerone.gestioneattivita.ErrorMessage.VISUALIZZA_LE_ATTIVITA_DA_TE_CREATE;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.controllers.RequestsHandler;
import com.intuition.cicerone.gestioneattivita.attivita.Attivita;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.attivita.IItinerario;
import com.intuition.cicerone.gestioneattivita.attivita.Itinerario;
import com.intuition.cicerone.persistenza.AttivitaCrud;

@Controller
public class WebControllerCreaAttivita {



	@Autowired
	private AttivitaCrud attivitaCrud;

	@GetMapping(CREA_ATTIVITA)
	public String mostraVista(HttpServletRequest request,
			ModelMap map) {
		HttpSession status = request.getSession();
		IAccount loggedAccount = (IAccount)status.getAttribute(USER_ACCOUNT);
		RequestsHandler handler = new RequestsHandler(loggedAccount);
		return handler.setModelMapAccount(map)
				.roleEq("cicerone")
				.ifCorrectReturn(mostraVistaGet(map))
				.build();
	}


	private String mostraVistaGet(ModelMap map) {
		map.addAttribute(HIDE, HIDDEN);
		map.addAttribute(MESSAGE, NULL_VALUE);
		map.addAttribute(TYPE, TYPE_DANGER);
		map.addAttribute(LINK, NULL_VALUE);
		map.addAttribute(LINK_MESSAGE, NULL_VALUE);
		return CREA_ATTIVITAJSP;
	}

	@PostMapping(CREA_ATTIVITA)
	public String processaPost(HttpServletRequest request,
			ModelMap map,
			@RequestParam("descrizione") String descrizioneAttivita,
			@RequestParam("retribuzione") String retribuzione,
			@RequestParam(value = "valoreRetribuzione", required=false) Double valoreRetribuzione,
			@RequestParam("lingue") String lingue,
			@RequestParam("lat") Double latitudine,
			@RequestParam("lon") Double longitudine,
			@RequestParam("luogo") String luogo,
			@RequestParam("data") String dataIncontro,
			@RequestParam("ore") Integer ore,
			@RequestParam("minuti") Integer minuti,
			@RequestParam("titolo") String titolo) {
		HttpSession status = request.getSession();
		IAccount loggedAccount = (IAccount)status.getAttribute(USER_ACCOUNT);
		RequestsHandler handler = new RequestsHandler(loggedAccount);
		return handler
				.setModelMapAccount(map)
				.roleEq("cicerone")
				.ifCorrectReturn(processaPostCreazioneAttivita(map,
						loggedAccount, 
						descrizioneAttivita,
						retribuzione,
						valoreRetribuzione,
						lingue,
						latitudine,
						longitudine,
						luogo,
						dataIncontro,
						ore,
						minuti,
						titolo))
				.build();
	}

	private String processaPostCreazioneAttivita(ModelMap map, IAccount loggedAccount,
			String descrizioneAttivita,
			String retribuzione,
			Double valoreRetribuzione,
			String lingue,
			Double latitudine,
			Double longitudine,
			String luogo,
			String dataIncontro,
			Integer ore,
			Integer minuti,
			String titolo) {
		try {
			Set<String> lingueSet = new HashSet<>(Arrays.asList(lingue.replace("#", "").split(" ")));
			IItinerario itinerario = new Itinerario.BuilderItinerario()
					.generaIdItinerario()
					.setDescrizioneAttivita(descrizioneAttivita)
					.setRetribuzione(retribuzione.equalsIgnoreCase("retribuito") ? Itinerario.Retribuzione.RETRIBUITO : Itinerario.Retribuzione.NONRETRIBUITO
							, valoreRetribuzione == null ? 0 : valoreRetribuzione)
					.setLingua(lingueSet)
					.build();
			itinerario.setCoordinateIncontro(latitudine, longitudine);
			itinerario.setDataIncontro(LocalDate.parse(dataIncontro));
			itinerario.setOraIncontro(LocalTime.of(ore, minuti));
			itinerario.setLuogo(luogo);

			IAttivita attivita = new Attivita.BuilderAttivita()
					.generaIdAttivta()
					.setCreatore(loggedAccount)
					.setItinerario(itinerario)
					.setTitolo(titolo)
					.setDataApertura(LocalDate.now())
					.build();
			attivitaCrud.salvaAttivita(attivita);
			map.addAttribute(MESSAGE, ATTIVITA_CREATA_CON_SUCCESSO);
			map.addAttribute(TYPE, TYPE_SUCCESS);
			map.addAttribute(LINK, ATTIVITA_CREATEURL);
			map.addAttribute(LINK_MESSAGE, VISUALIZZA_LE_ATTIVITA_DA_TE_CREATE);
		} catch (IllegalArgumentException|DateTimeParseException e) {
			map.addAttribute(MESSAGE, e.getMessage());
			map.addAttribute(TYPE, TYPE_DANGER);
			map.addAttribute(LINK, NULL_VALUE);
			map.addAttribute(LINK_MESSAGE, NULL_VALUE);
		}
		map.addAttribute(HIDE, NULL_VALUE);
		return CREA_ATTIVITAJSP;
	}

}



