package br.com.gustavo.sustentacao.ecommerce.service.impl;

import br.com.gustavo.sustentacao.ecommerce.domain.User;
import br.com.gustavo.sustentacao.ecommerce.domain.UserLogin;
import br.com.gustavo.sustentacao.ecommerce.repository.UserRepository;
import br.com.gustavo.sustentacao.ecommerce.security.JwtService;
import br.com.gustavo.sustentacao.ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> save(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
           return Optional.empty();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setId(null);

        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<User> update(String id, User user) {
        if (userRepository.findById(id).isEmpty()) {
            return Optional.empty();
        }
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
        }

        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return Optional.of(userRepository.save(user));
    }

    @Override
    public Void deleteById(String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autenticado");
        }
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        userRepository.deleteById(id);
        return null;
    }

    @Override
    public Optional<UserLogin> autenticarUsuario(UserLogin userLogin) {
        System.out.println("Tentando autenticar usuário: " + userLogin.getEmail());

        try {
            // Verificar se o usuário existe no banco
            Optional<User> userExists = userRepository.findByEmail(userLogin.getEmail());
            if (userExists.isEmpty()) {
                System.out.println("Usuário não encontrado no banco: " + userLogin.getEmail());
                return Optional.empty();
            }

            System.out.println("Usuário encontrado, tentando autenticar...");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));

            System.out.println("Autenticação bem-sucedida para: " + userLogin.getEmail());
            return userRepository.findByEmail(userLogin.getEmail())
                    .map(usuario -> construirRespostaLogin(userLogin, usuario));

        } catch (Exception e) {
            System.out.println("Erro na autenticação: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public UserLogin construirRespostaLogin(UserLogin userLogin, User user) {
        userLogin.setId(user.getId());
        userLogin.setNome(user.getName());
        userLogin.setPassword("");
        userLogin.setToken(gerarToken(user.getEmail()));
        return userLogin;
    }

    @Override
    public String gerarToken(String usuario) {
        return "Bearer " + jwtService.generateToken(usuario);
    }
}
