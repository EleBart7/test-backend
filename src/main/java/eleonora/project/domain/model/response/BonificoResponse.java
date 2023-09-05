package eleonora.project.domain.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BonificoResponse {

    @Schema(description = "Codice risultato operazione")
    private String code;

    @Schema(description = "Descrizione risultato operazione")
    private String description;

}
