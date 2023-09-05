package eleonora.project.domain.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ListaTransazioniRequest {

    @NotNull
    @Schema(description = "ID del conto")
    private Long accountId;

    @NotNull
    @Schema(description = "Data di inizio per la ricerca di transazioni")
    private Date fromAccountingDate;

    @NotNull
    @Schema(description = "Data di fine per la ricerca di transazioni")
    private Date toAccountingDate;
}