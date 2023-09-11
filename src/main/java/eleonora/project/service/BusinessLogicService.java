package eleonora.project.service;

import eleonora.project.service.model.request.BonificoRequest;
import eleonora.project.service.model.request.LetturaSaldoRequest;
import eleonora.project.service.model.request.ListaTransazioniRequest;
import eleonora.project.controller.FabrickApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BusinessLogicService {

    private final FabrickApi fabrickApi;

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
