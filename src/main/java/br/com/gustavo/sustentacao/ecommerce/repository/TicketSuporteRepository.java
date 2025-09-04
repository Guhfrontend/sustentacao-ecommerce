package br.com.gustavo.sustentacao.ecommerce.repository;

import br.com.gustavo.sustentacao.ecommerce.domain.TicketSuporte;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TicketSuporteRepository extends MongoRepository<TicketSuporte, String> {

    List<TicketSuporte> findByUserId(String userId);
}

