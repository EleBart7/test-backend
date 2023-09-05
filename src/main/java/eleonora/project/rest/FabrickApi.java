package eleonora.project.rest;

import eleonora.project.ConfigProperties;
import eleonora.project.domain.model.request.BonificoRequest;
import eleonora.project.domain.model.request.ListaTransazioniRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FabrickApi {

    @Autowired
    ConfigProperties configProperties;

    public String getLetturaSaldo(Long accountId) {
        String url = configProperties.getLetturaSaldoUrl();
        // CREARE IL CLIENT

        // fare chiamata

        return null;
    }

    public String getListaTransazioni(ListaTransazioniRequest request) {
        String url = configProperties.getListaTransazioniUrl();
        // CREARE IL CLIENT

        // fare chiamata

        return null;
    }

    public String doBonifico(BonificoRequest request) {
        String url = configProperties.getBonificoUrl();
        // CREARE IL CLIENT

        // fare chiamata

        return null;
    }

}
