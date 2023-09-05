package eleonora.project.domain;

import eleonora.project.application.ApplicationException;
import eleonora.project.domain.model.Transazione;
import eleonora.project.domain.model.request.BonificoRequest;
import eleonora.project.domain.model.request.ListaTransazioniRequest;
import eleonora.project.domain.model.response.BonificoResponse;
import eleonora.project.domain.model.response.LetturaSaldoResponse;
import eleonora.project.domain.model.response.ListaTransazioniResponse;
import eleonora.project.infrastructure.Repository;
import eleonora.project.rest.FabrickApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
//@Sl4j
public class BusinessLogic {

    @Autowired
    private Repository repository;

    @Autowired
    private FabrickApi fabrickApi;

    public LetturaSaldoResponse retrieveSaldo(Long accountId) {
        try {

            // LOG TRACE

            // CREATE BUSINESS OBJECT FOR CALL

            // CALL METHOD FOR FABRICK API
            fabrickApi.getLetturaSaldo(accountId);

            // LOG TRACE

            return null;

        } catch (Exception e) {
            // log ERROR
            throw new ApplicationException(e.getMessage());
        }
    }

    public ListaTransazioniResponse retrieveTransazioni(ListaTransazioniRequest request) {
        try {

            // LOG TRACE

            // CREATE BUSINESS OBJECT FOR CALL

            // CALL METHOD FOR FABRICK API
            fabrickApi.getListaTransazioni(request);

            // LOG TRACE

            return null;

        } catch (Exception e) {
            // log ERROR
            throw new ApplicationException(e.getMessage());
        }
    }

    public BonificoResponse createBonifico(BonificoRequest request) {
        try {

            // LOG TRACE

            // CREATE BUSINESS OBJECT FOR CALL

            // CALL METHOD FOR FABRICK API
            fabrickApi.doBonifico(request);

            // LOG TRACE

            return null;

        } catch (Exception e) {
            // log ERROR
            throw new ApplicationException(e.getMessage());
        }
    }

    public void writeListaTransazioni(List<Transazione> listaTransazioni) {
        try {
            // LOG TRACE

            // CONNECT DB IN MEMORY

            // WRITE

            // IF SUCCESS LOG DEBUG

            // LOG TRACE

        } catch (Exception e) {
            // LOG ERROR
            throw new ApplicationException(e.getMessage());
        }
    }

}
