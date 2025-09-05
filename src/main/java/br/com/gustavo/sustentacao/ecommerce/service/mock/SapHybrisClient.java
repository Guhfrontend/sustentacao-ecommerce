package br.com.gustavo.sustentacao.ecommerce.service.mock;

import br.com.gustavo.sustentacao.ecommerce.DTO.ProdutoHybrisDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class SapHybrisClient {

    private final RestTemplate restTemplate;
    private final String hybrisApiUrl;

    @Autowired
    public SapHybrisClient(RestTemplate restTemplate, @Value("${hybris.api.url}") String hybrisApiUrl) {
        this.restTemplate = restTemplate;
        this.hybrisApiUrl = hybrisApiUrl;
    }

    public ProdutoHybrisDTO buscarProdutoPorSku(String sku) {
        String url = hybrisApiUrl + "/products/" + sku;
        try {
            return restTemplate.getForObject(url, ProdutoHybrisDTO.class);
        } catch (Exception e) {
            // Logar o erro e/ou lançar uma exceção customizada
            System.err.println("Erro ao buscar produto no Hybris: " + e.getMessage());
            return null;
        }
    }
}

