package br.com.gustavo.sustentacao.ecommerce.repository;

import br.com.gustavo.sustentacao.ecommerce.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

}
