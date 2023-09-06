package eleonora.project.domain.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LetturaSaldoResponse {

    @Schema(description = "ID del conto")
    private String accountId;

    @Schema(description = "IBAN del conto associato")
    private String iban;

    @Schema(description = "Nome dell'intestatario del conto")
    private String holderName;

}
