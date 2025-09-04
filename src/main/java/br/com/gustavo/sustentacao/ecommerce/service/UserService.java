package br.com.gustavo.sustentacao.ecommerce.service;

import br.com.gustavo.sustentacao.ecommerce.domain.User;
import br.com.gustavo.sustentacao.ecommerce.domain.UserLogin;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(String id);

    Optional<User> save(User user);

    Optional<User> update(String id, User user);

    Void deleteById(String id);

    Optional<UserLogin> autenticarUsuario (UserLogin userLogin);

    UserLogin construirRespostaLogin(UserLogin usuarioLogin, User usuario);

    String gerarToken(String usuario);
}
