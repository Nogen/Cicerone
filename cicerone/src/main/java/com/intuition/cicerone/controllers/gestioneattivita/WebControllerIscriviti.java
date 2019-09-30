package com.intuition.cicerone.controllers.gestioneattivita;

import static com.intuition.cicerone.controllers.WebConstant.HIDE;
import static com.intuition.cicerone.controllers.WebConstant.LINK;
import static com.intuition.cicerone.controllers.WebConstant.LINK_MESSAGE;
import static com.intuition.cicerone.controllers.WebConstant.MESSAGE;
import static com.intuition.cicerone.controllers.WebConstant.NULL_VALUE;
import static com.intuition.cicerone.controllers.WebConstant.TYPE;
import static com.intuition.cicerone.controllers.WebConstant.TYPE_DANGER;
import static com.intuition.cicerone.controllers.WebConstant.TYPE_SUCCESS;
import static com.intuition.cicerone.controllers.WebConstant.USER_ACCOUNT;
import static com.intuition.cicerone.controllers.WebsiteIndice.GPANEL_TROVA_ATTIVITAJSP;
import static com.intuition.cicerone.controllers.WebsiteIndice.ISCRIVITIURL;
import static com.intuition.cicerone.controllers.WebsiteIndice.REDIRECT_LOGGED_HOME;
import static com.intuition.cicerone.controllers.WebsiteIndice.REDIRECT_LOGIN;
import static com.intuition.cicerone.controllers.WebsiteIndice.TROVA_ATTIVITAURL;
import static com.intuition.cicerone.gestioneattivita.ErrorMessage.ERROREATTIVITAISCRITTO;
import static com.intuition.cicerone.gestioneattivita.ErrorMessage.ERROREATTIVITANONESISTENTE;
import static com.intuition.cicerone.gestioneattivita.ErrorMessage.ISCRITTO_CON_SUCESSO;
import static com.intuition.cicerone.gestioneattivita.ErrorMessage.VISUALIZZAATTIVITAMSG;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.controllers.mappers.AccountModelMapMapper;
import com.intuition.cicerone.controllers.mappers.AttivitaModelMapMapper;
import com.intuition.cicerone.controllers.mappers.IMapper;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.richiesta.IRichiesta;
import com.intuition.cicerone.gestioneattivita.richiesta.Richiesta;
import com.intuition.cicerone.persistenza.AttivitaCrud;
import com.intuition.cicerone.persistenza.RichiestaCrud;

@Controller
public class WebControllerIscriviti {
	private static final String GLOBETROTTER = "globetrotter";
	@Autowired
	private AttivitaCrud attivitaCrud;
	@Autowired
	private RichiestaCrud richiestaCrud;

	@GetMapping(ISCRIVITIURL)
	public String processaGet(HttpServletRequest request,
			ModelMap map,
			@RequestParam(value="id", defaultValue = "errore")String idAttivita) {

		HttpSession status = request.getSession();
		if(status.getAttribute(USER_ACCOUNT) == null) {
			return REDIRECT_LOGIN;
		}
		IAccount loggedAccount = (IAccount)status.getAttribute(USER_ACCOUNT);
		if(loggedAccount.getRuolo().equalsIgnoreCase(GLOBETROTTER)) {
			IMapper<ModelMap> mapper = new AccountModelMapMapper(loggedAccount);
			ModelMap mapAccount = mapper.converti();
			Optional<IAttivita> optAttivita = attivitaCrud.trovaAttivita(idAttivita, null);
			if(optAttivita.isPresent()) {
				IAttivita attivita = optAttivita.get();
				List<IAccount> listaPartecipanti = 
						richiestaCrud.trovaRichiestaByAttivita(attivita).stream()
						.filter(r -> r.getStatoRichiesta().toString().equalsIgnoreCase("accettata") || r.getStatoRichiesta().toString().equalsIgnoreCase("in sospeso"))
						.map(IRichiesta::getMittente)
						.collect(Collectors.toList());
				if (!attivita.getCreatore().equals(loggedAccount) && !listaPartecipanti.contains(loggedAccount)) {
					map.addAllAttributes(mapAccount);
					IRichiesta richiesta = new Richiesta.BuilderRichiesta()
							.setAccount(loggedAccount)
							.setAttivita(attivita)
							.generaIdRichiesta()
							.setDataRichiesta(LocalDate.now())
							.build();
					richiestaCrud.salvaRichiesta(richiesta);
					IMapper<ModelMap> mapperAttivita = new AttivitaModelMapMapper(attivita);
					map.addAllAttributes(mapperAttivita.converti());
					map.addAllAttributes(mapAccount);
					map.addAttribute(HIDE, NULL_VALUE);
					map.addAttribute(MESSAGE, ISCRITTO_CON_SUCESSO);
					map.addAttribute(TYPE, TYPE_SUCCESS);
					map.addAttribute(LINK, TROVA_ATTIVITAURL);
					map.addAttribute(LINK_MESSAGE, VISUALIZZAATTIVITAMSG);
					return GPANEL_TROVA_ATTIVITAJSP;
				}
				map.addAllAttributes(mapAccount);
				map.addAttribute(HIDE, NULL_VALUE);
				map.addAttribute(TYPE, TYPE_DANGER);
				map.addAttribute(MESSAGE, ERROREATTIVITAISCRITTO);
				map.addAttribute(LINK, TROVA_ATTIVITAURL);
				map.addAttribute(LINK_MESSAGE, VISUALIZZAATTIVITAMSG);
				return GPANEL_TROVA_ATTIVITAJSP;
			}
			map.addAllAttributes(mapAccount);
			map.addAttribute(HIDE, NULL_VALUE);
			map.addAttribute(TYPE, TYPE_DANGER);
			map.addAttribute(MESSAGE,ERROREATTIVITANONESISTENTE);
			map.addAttribute(LINK, TROVA_ATTIVITAURL);
			map.addAttribute(LINK_MESSAGE, VISUALIZZAATTIVITAMSG);
			return GPANEL_TROVA_ATTIVITAJSP;
		}
		return REDIRECT_LOGGED_HOME;
	}
}