package eleonora.project.controller;

import eleonora.project.service.BusinessLogicService;
import eleonora.project.service.model.request.BonificoRequest;
import eleonora.project.service.model.request.LetturaSaldoRequest;
import eleonora.project.service.model.request.ListaTransazioniRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("ControllerV1")
@RequestMapping("api/v1")
public class Controller {

    @Autowired
    private BusinessLogicService businessLogicService;

    @Operation(summary = "LETTURA SALDO", tags = {"letturaSaldo"})
    @GetMapping(value = "/letturaSaldo/{accountId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> letturaSaldo(
            @RequestHeader(value = "requestId", required = true) String requestId,
            @PathVariable Long accountId
    ) {
        LetturaSaldoRequest request = new LetturaSaldoRequest();
        request.setAccountId(accountId);
        request.setRequestId(requestId);
        String res = businessLogicService.retrieveSaldo(request);

        return ResponseEntity.ok(res);
    }

    @Operation(summary = "LISTA TRANSAZIONI", tags = {"listaTransazioni"})
    @PostMapping(value = "/listaTransazioni", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> listaTransazioni(
            @RequestHeader(value = "requestId", required = true) String requestId,
            @RequestBody ListaTransazioniRequest request
    ) {
        request.setRequestId(requestId);
        String res = businessLogicService.retrieveTransazioni(request);

        return ResponseEntity.ok(res);
    }

    @Operation(summary = "BONIFICO", tags = {"bonifico"})
    @PostMapping(value = "/bonifico", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> bonifico(
            @RequestHeader(value = "requestId", required = true) String requestId,
            @RequestBody BonificoRequest request
    ) {
        request.setRequestId(requestId);
        String res = businessLogicService.createBonifico(request);

        return ResponseEntity.ok(res);
    }

}
