package eleonora.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import eleonora.project.domain.BusinessLogic;
import eleonora.project.domain.model.Account;
import eleonora.project.domain.model.Creditor;
import eleonora.project.rest.Controller;
import eleonora.project.rest.FabrickApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controller.class)
@Import({BusinessLogic.class, FabrickApi.class, ConfigProperties.class})
public class TestRestController {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper om = new ObjectMapper();

    @Test
    public void getSaldo() throws Exception
    {

        HttpHeaders headers = getHttpHeaders();

        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/letturaSaldo/{accountId}", "14537780")
                .accept(MediaType.APPLICATION_JSON)
                .headers(headers)
                      //.param("accountId", "14537780")
                )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.payload.accountId").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.payload.holderName").exists());
    }

    @Test
    public void postListaTransazioni() throws Exception
    {
        HttpHeaders headers = getHttpHeaders();

        ListaTransazioniRequest req = new ListaTransazioniRequest();
        req.setAccountId(Long.valueOf("14537780"));
        req.setFrom("2019-01-01");
        req.setTo("2019-12-01");

        mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/listaTransazioni")
                .accept(MediaType.APPLICATION_JSON)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(req))
                )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.payload.list[*].transactionId").isNotEmpty());
    }

    @Test
    public void postBonifico() throws Exception
    {
        HttpHeaders headers = getHttpHeaders();

        BonificoRequest req = new BonificoRequest();
        req.setAccountId(Long.valueOf("14537780"));
        req.setAmount("1");
        req.setDescription("Payment invoice 75/2017");
        Creditor c = new Creditor();
        Account a = new Account();
        a.setAccountCode("IT40L0326822311052923800661");
        c.setAccount(a);
        c.setName("John Doe");
        req.setCreditor(c);
        req.setCurrency("EUR");
        req.setExecutionDate("2023-09-07");

        mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/bonifico")
                .accept(MediaType.APPLICATION_JSON)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(req))
                )
            .andDo(print())
            .andExpect(status().is(400))
            .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("400"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.description").exists());
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("requestId", "1");
        headers.add("authSchema", "S2S");
        headers.add("apiKey", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        return headers;
    }

}