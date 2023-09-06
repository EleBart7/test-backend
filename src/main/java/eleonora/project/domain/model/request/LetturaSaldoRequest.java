package eleonora.project.domain.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LetturaSaldoRequest extends InfoRequest {

    @NotNull
    @Schema(description = "ID del conto")
    private Long accountId;
}
