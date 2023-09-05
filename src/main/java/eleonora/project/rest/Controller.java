package eleonora.project.rest;

import eleonora.project.application.error.ErrorResponse;
import eleonora.project.domain.BusinessLogic;
import eleonora.project.domain.model.request.BonificoRequest;
import eleonora.project.domain.model.request.ListaTransazioniRequest;
import eleonora.project.domain.model.response.BonificoResponse;
import eleonora.project.domain.model.response.LetturaSaldoResponse;
import eleonora.project.domain.model.response.ListaTransazioniResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = LetturaSaldoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request/Validation Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping(value = "/letturaSaldo", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<LetturaSaldoResponse> letturaSaldo(
            @RequestHeader(value = "requestId", required = true) String requestId,
            @RequestParam @Valid Long accountId
    ) {
        LetturaSaldoResponse res = new LetturaSaldoResponse();

        res = businessLogic.retrieveSaldo(accountId);

        return ResponseEntity.ok(res);
    }

    @Operation(summary = "LISTA TRANSAZIONI", tags = {"listaTransazioni"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ListaTransazioniResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request/Validation Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping(value = "/listaTransazioni", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ListaTransazioniResponse> listaTransazioni(
            @RequestHeader(value = "requestId", required = true) String requestId,
            @RequestBody @Valid ListaTransazioniRequest request
    ) {
        ListaTransazioniResponse res = new ListaTransazioniResponse();

        res = businessLogic.retrieveTransazioni(request);

        return ResponseEntity.ok(res);
    }

    @Operation(summary = "BONIFICO", tags = {"bonifico"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = BonificoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request/Validation Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping(value = "/bonifico", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BonificoResponse> bonifico(
            @RequestHeader(value = "requestId", required = true) String requestId,
            @RequestBody @Valid BonificoRequest request
    ) {
        BonificoResponse res = new BonificoResponse();

        res = businessLogic.createBonifico(request);

        return ResponseEntity.ok(res);
    }

}
