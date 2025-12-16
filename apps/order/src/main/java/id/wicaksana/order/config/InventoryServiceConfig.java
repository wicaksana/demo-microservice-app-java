package id.wicaksana.order.config;

import id.wicaksana.order.service.InventoryServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration(proxyBeanMethods = false)
@ImportHttpServices(group = "inventory", types = {InventoryServiceClient.class})
public class InventoryServiceConfig {

    @Value("${spring.http.client.service.group.inventory.base-url}")
    private String baseUrl;

    @Bean
    RestClientHttpServiceGroupConfigurer groupConfigurer() {
        return groups -> {
          groups.filterByName("inventory").forEachClient((_, builder) ->
                  builder.baseUrl(baseUrl));
        };
    }
}
