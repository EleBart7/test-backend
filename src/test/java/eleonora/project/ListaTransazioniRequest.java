package eleonora.project;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ListaTransazioniRequest {

    @NotNull
    @Schema(description = "ID del conto")
    private Long accountId;

    @NotNull
    @Schema(description = "Data di inizio per la ricerca di transazioni")
    private String from;

    @NotNull
    @Schema(description = "Data di fine per la ricerca di transazioni")
    private String to;
}
