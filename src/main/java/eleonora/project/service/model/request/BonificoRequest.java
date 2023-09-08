package eleonora.project.service.model.request;

import eleonora.project.service.model.Creditor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BonificoRequest extends InfoRequest {

    @NotNull
    @Schema(description = "ID del conto")
    private Long accountId;

    @NotNull
    @Schema(description = "Creditore")
    private Creditor creditor;

    @NotBlank
    @Schema(description = "Descrizione dell'operazione")
    private String description;

    @NotBlank
    @Schema(description = "Valuta")
    private String currency;

    @NotBlank
    @Schema(description = "Importo dell'operazione")
    private String amount;

    @NotBlank
    @Schema(description = "Data di emissione del bonifico")
    private String executionDate;

}
