package br.com.gustavo.sustentacao.ecommerce.controller;

import br.com.gustavo.sustentacao.ecommerce.domain.User;
import br.com.gustavo.sustentacao.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    void start() {
        userRepository.deleteAll();

    }

    @Test
    @DisplayName("Cadastrando um Usuario")
    public void deveCriarUmUsuario() {

        HttpEntity<User> corpoRequisicao = new HttpEntity<>(new User("--", "Gustavo", "gustavo@gmail.com","12345678"));

        ResponseEntity<User> corpoResposta = testRestTemplate.exchange("/users/cadastrar", HttpMethod.POST, corpoRequisicao, User.class);

        assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
    }

    @Test
    @DisplayName("Listando todos os usuários sem token")
    public void deveMostrarTodosUsuarios() {

        HttpEntity<User> corpoRequisicao = new HttpEntity<>(new User("-", "Gustavo", "gustavo@gmail.com", "12345678"));
        HttpEntity<User> corpoRequisicao1 = new HttpEntity<>(new User("--", "Gustavo Ribeiro", "gustavor@gmail.com","12345678"));

        ResponseEntity<String> corpoResposta = testRestTemplate.exchange("/users", HttpMethod.GET, null, String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, corpoResposta.getStatusCode());
    }

}
