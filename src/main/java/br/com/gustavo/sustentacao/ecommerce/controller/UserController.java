package br.com.gustavo.sustentacao.ecommerce.controller;

import br.com.gustavo.sustentacao.ecommerce.domain.User;
import br.com.gustavo.sustentacao.ecommerce.domain.UserLogin;
import br.com.gustavo.sustentacao.ecommerce.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable @Valid String id) {
        User user = userService.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<User> save(@Valid @RequestBody User user) {
        try {
            Optional<User> userSaved = userService.save(user);
            return userSaved
                    .map(value -> ResponseEntity.status(HttpStatus.CREATED).body(value))
                    .orElse(ResponseEntity.status(HttpStatus.CONFLICT).body(null));
        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/logar")
    public ResponseEntity<UserLogin> autenticar(@Valid @RequestBody UserLogin userLogin) {
        return userService.autenticarUsuario(userLogin)
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Optional<User>> update(@PathVariable @Valid String id, @RequestBody @Valid User user) {
        try {
            Optional<User> userUpdated = userService.update(id, user);
            return ResponseEntity.ok(userUpdated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<Optional<User>> partialUpdate(@PathVariable @Valid String id, @RequestBody @Valid User user) {

        User existingUser = userService.findById(id).orElse(null);

        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }
        try {
            Optional<User> updated = userService.save(existingUser);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable @Valid String id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
