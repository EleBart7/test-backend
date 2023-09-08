package eleonora.project.service.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LetturaSaldoRequest extends InfoRequest {

    @NotNull
    @Schema(description = "ID del conto")
    private Long accountId;
}
