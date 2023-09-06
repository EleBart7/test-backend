package eleonora.project.domain;

import eleonora.project.domain.model.request.BonificoRequest;
import eleonora.project.domain.model.request.LetturaSaldoRequest;
import eleonora.project.domain.model.request.ListaTransazioniRequest;
import eleonora.project.rest.FabrickApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BusinessLogic {

    @Autowired
    private FabrickApi fabrickApi;

    public String retrieveSaldo(LetturaSaldoRequest request) {
        try {

            log.trace("init retrieve Saldo");

            return fabrickApi.getLetturaSaldo(request);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    public String retrieveTransazioni(ListaTransazioniRequest request) {
        try {

            log.trace("init retrieve Transazioni");

            return fabrickApi.getListaTransazioni(request);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    public String createBonifico(BonificoRequest request) {
        try {

            log.trace("init create Bonifico");

            return fabrickApi.doBonifico(request);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
