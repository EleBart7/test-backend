package eleonora.project;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

/**
 * Contiene tutte le configurazioni dell'applicazione
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@PropertySource("classpath:application.yml")
public class ConfigProperties {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${api.letturaSaldo.url}")
    private String letturaSaldoUrl;
    @Value("${api.listaTransazioni.url}")
    private String listaTransazioniUrl;
    @Value("${api.bonifico.url}")
	private String bonificoUrl;

}
