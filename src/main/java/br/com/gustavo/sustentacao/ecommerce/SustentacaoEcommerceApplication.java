package br.com.gustavo.sustentacao.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class SustentacaoEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SustentacaoEcommerceApplication.class, args);
    }

}
