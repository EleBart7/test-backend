package eleonora.project.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Account {

    @NotBlank
    @Schema(description = "ID del conto")
    private String accountCode;

}
