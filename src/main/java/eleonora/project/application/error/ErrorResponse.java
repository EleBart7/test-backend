package eleonora.project.application.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    @Schema(description = "Codice del messaggio")
    private String code;

    @Schema(description = "Descrizione aggiuntiva")
    private String description;

}
