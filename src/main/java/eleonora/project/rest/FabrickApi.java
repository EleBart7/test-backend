package eleonora.project.rest;
import eleonora.project.ConfigProperties;
import eleonora.project.application.ApplicationException;
import eleonora.project.domain.model.request.BonificoRequest;
import eleonora.project.domain.model.request.ListaTransazioniRequest;
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

    public String getLetturaSaldo(Long accountId) {
        try {
            log.trace("start lettura saldo");
            String url = configProperties.getLetturaSaldoUrl();
            url = url.replace("{accountId}", accountId.toString());
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = getHeaders();
            HttpEntity<String> httpEntity = new HttpEntity<>("no body", headers);
            String res = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class).getBody();
            log.debug("Response: {}", res);
            return res;
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    public String getListaTransazioni(ListaTransazioniRequest request) {
        try {
            log.trace("start lista transazioni");
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

            String res = restTemplate.exchange(urlTemplate, HttpMethod.GET, httpEntity, String.class, params).getBody();
            log.debug("Response: {}", res);
            return res;
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    public String doBonifico(BonificoRequest request) {
        try {
            log.trace("start bonifico");
            String url = configProperties.getBonificoUrl();
            url = url.replace("{accountId}", request.getAccountId().toString());
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = getHeaders();
            HttpEntity<BonificoRequest> httpEntity = new HttpEntity<>(request, headers);
            String res = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class).getBody();
            log.debug("Response: {}", res);
            return res;
        } catch (Exception e) {
            throw e;
        }
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
