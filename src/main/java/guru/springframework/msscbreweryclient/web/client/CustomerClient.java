package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class CustomerClient {

    private final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
    private String apiHost;
    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CustomerDto getCustomerById(UUID customerId) {
        return restTemplate.getForObject(getApiPath(customerId), CustomerDto.class);
    }

    public URI createNewCustomer(CustomerDto customerDto) {
        return restTemplate.postForLocation(getApiPath(), customerDto);
    }

    public void updateCustomer(CustomerDto customerDto) {
        restTemplate.put(getApiPath(), customerDto);
    }

    public void deleteCustomerById(UUID uuid) {
        restTemplate.delete(getApiPath(uuid));
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    private String getApiPath() {
        return apiHost + CUSTOMER_PATH_V1;
    }

    private String getApiPath(UUID uuid) {
        return apiHost + CUSTOMER_PATH_V1 + uuid.toString();
    }
}
