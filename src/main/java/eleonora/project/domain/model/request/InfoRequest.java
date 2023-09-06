package eleonora.project.domain.model.request;

import lombok.Data;

@Data
public class InfoRequest {

    protected String requestId;
    protected String apiKey;
    protected String authSchema;

}
