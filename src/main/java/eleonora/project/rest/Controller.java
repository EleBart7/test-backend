package eleonora.project.rest;

import eleonora.project.domain.BusinessLogic;
import eleonora.project.domain.model.request.BonificoRequest;
import eleonora.project.domain.model.request.LetturaSaldoRequest;
import eleonora.project.domain.model.request.ListaTransazioniRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("ControllerV1")
@RequestMapping("api/v1")
public class Controller {

    @Autowired
    BusinessLogic businessLogic;

    @Operation(summary = "LETTURA SALDO", tags = {"letturaSaldo"})
    @GetMapping(value = "/letturaSaldo/{accountId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> letturaSaldo(
            @RequestHeader(value = "requestId", required = true) String requestId,
            @RequestHeader(value = "apiKey", required = true) String apiKey,
            @RequestHeader(value = "authSchema", required = true) String authSchema,
            @PathVariable Long accountId
    ) {
        LetturaSaldoRequest request = new LetturaSaldoRequest();
        request.setAccountId(accountId);
        request.setRequestId(requestId);
        request.setApiKey(apiKey);
        request.setAuthSchema(authSchema);
        String res = businessLogic.retrieveSaldo(request);

        return ResponseEntity.ok(res);
    }

    @Operation(summary = "LISTA TRANSAZIONI", tags = {"listaTransazioni"})
    @PostMapping(value = "/listaTransazioni", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> listaTransazioni(
            @RequestHeader(value = "requestId", required = true) String requestId,
            @RequestHeader(value = "apiKey", required = true) String apiKey,
            @RequestHeader(value = "authSchema", required = true) String authSchema,
            @RequestBody ListaTransazioniRequest request
    ) {
        request.setRequestId(requestId);
        request.setApiKey(apiKey);
        request.setAuthSchema(authSchema);
        String res = businessLogic.retrieveTransazioni(request);

        return ResponseEntity.ok(res);
    }

    @Operation(summary = "BONIFICO", tags = {"bonifico"})
    @PostMapping(value = "/bonifico", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> bonifico(
            @RequestHeader(value = "requestId", required = true) String requestId,
            @RequestHeader(value = "apiKey", required = true) String apiKey,
            @RequestHeader(value = "authSchema", required = true) String authSchema,
            @RequestBody BonificoRequest request
    ) {
        request.setRequestId(requestId);
        request.setApiKey(apiKey);
        request.setAuthSchema(authSchema);
        String res = businessLogic.createBonifico(request);

        return ResponseEntity.ok(res);
    }

}
