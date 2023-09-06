package eleonora.project.rest;
import eleonora.project.ConfigProperties;
import eleonora.project.application.error.ApplicationException;
import eleonora.project.domain.model.request.BonificoRequest;
import eleonora.project.domain.model.request.InfoRequest;
import eleonora.project.domain.model.request.LetturaSaldoRequest;
import eleonora.project.domain.model.request.ListaTransazioniRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
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

    public String getLetturaSaldo(LetturaSaldoRequest request) {
        try {
            log.trace("start lettura saldo");
            String url = configProperties.getLetturaSaldoUrl();
            url = url.replace("{accountId}", request.getAccountId().toString());
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = getHeaders(request);
            HttpEntity<String> httpEntity = new HttpEntity<>("no body", headers);
            String res = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class).getBody();
            log.debug("Response: {}", res);
            return res;
        } catch (HttpClientErrorException ex) {
            throw new ApplicationException(ex.getStatusCode(), ex.getResponseBodyAsString());
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
            HttpHeaders headers = getHeaders(request);
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
        } catch (HttpClientErrorException ex) {
            throw new ApplicationException(ex.getStatusCode(), ex.getResponseBodyAsString());
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
            HttpHeaders headers = getHeaders(request);
            HttpEntity<BonificoRequest> httpEntity = new HttpEntity<>(request, headers);
            String res = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class).getBody();
            log.debug("Response: {}", res);
            return res;
        } catch (HttpClientErrorException ex) {
            throw new ApplicationException(ex.getStatusCode(), ex.getResponseBodyAsString());
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    private HttpHeaders getHeaders(InfoRequest req) {
        log.trace("insert headers");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Api-Key", req.getApiKey());
        headers.add("Auth-Schema", req.getAuthSchema());
        return headers;
    }

}
