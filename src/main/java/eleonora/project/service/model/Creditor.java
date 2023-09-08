package eleonora.project.service.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Creditor {

    @NotBlank
    @Schema(description = "Nome del creditore")
    private String name;

    @NotNull
    @Schema(description = "Conto del creditore")
    private Account account;
}
