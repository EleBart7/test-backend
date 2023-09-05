package eleonora.project.domain.model.response;

import eleonora.project.domain.model.Transazione;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ListaTransazioniResponse {

    @Schema(description = "ID del conto")
    private String accountId;

    @Schema(description = "Lista delle transazioni del conto associato")
    private List<Transazione> listaTransazioni;

}
