package eleonora.project.domain;

import eleonora.project.application.ApplicationException;
import eleonora.project.domain.model.Transazione;
import eleonora.project.domain.model.request.BonificoRequest;
import eleonora.project.domain.model.request.ListaTransazioniRequest;
import eleonora.project.rest.FabrickApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class BusinessLogic {

    @Autowired
    private FabrickApi fabrickApi;

    public String retrieveSaldo(Long accountId) {
        try {

            log.trace("init retrieve Saldo");

            return fabrickApi.getLetturaSaldo(accountId);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    public String retrieveTransazioni(ListaTransazioniRequest request) {
        try {

            log.trace("init retrieve Transazioni");

            return fabrickApi.getListaTransazioni(request);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    public String createBonifico(BonificoRequest request) {
        try {

            log.trace("init create Bonifico");

            return fabrickApi.doBonifico(request);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }
}
