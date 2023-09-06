package eleonora.project.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eleonora.project.ConfigProperties;
import eleonora.project.application.ApplicationException;
import eleonora.project.domain.model.request.BonificoRequest;
import eleonora.project.domain.model.request.ListaTransazioniRequest;
import eleonora.project.domain.model.response.LetturaSaldoResponse;
import eleonora.project.domain.model.response.ListaTransazioniResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class FabrickApi {

    @Autowired
    ConfigProperties configProperties;

    ObjectMapper om = new ObjectMapper();

    public String getLetturaSaldo(Long accountId) {
        try {
            log.trace("start lettura saldo");
            String url = configProperties.getLetturaSaldoUrl();
            url = url.replace("{accountId}", accountId.toString());
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = getHeaders();
            HttpEntity<String> httpEntity = new HttpEntity<>("no body", headers);
            return restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class).getBody();
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    public String getListaTransazioni(ListaTransazioniRequest request) {
        String url = configProperties.getListaTransazioniUrl();
        url = url.replace("{accountId}", request.getAccountId().toString());
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>("no body", headers);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("fromAccountingDate", "{from}")
                .queryParam("toAccountingDate", "{to}")
                .encode()
                .toUriString();

        Map<String, String> params = new HashMap<>();
        params.put("from", request.getFrom());
        params.put("to", request.getTo());

        return restTemplate.exchange(urlTemplate, HttpMethod.GET, httpEntity, String.class, params).getBody();
    }

    public String doBonifico(BonificoRequest request) {
        String url = configProperties.getBonificoUrl();
        // CREARE IL CLIENT

        // fare chiamata

        return null;
    }

    private HttpHeaders getHeaders() {
        log.trace("insert headers");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        headers.add("Auth-Schema", "S2S");
        return headers;
    }

}
