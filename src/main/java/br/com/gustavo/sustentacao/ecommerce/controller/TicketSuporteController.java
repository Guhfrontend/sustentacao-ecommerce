package br.com.gustavo.sustentacao.ecommerce.controller;

import br.com.gustavo.sustentacao.ecommerce.domain.TicketSuporte;
import br.com.gustavo.sustentacao.ecommerce.service.TicketSuporteService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketSuporteController {


    private final TicketSuporteService ticketSuporteService;

    public TicketSuporteController(TicketSuporteService ticketSuporteService) {
        this.ticketSuporteService = ticketSuporteService;
    }

    @GetMapping
    public List<TicketSuporte> getAll() {
        return ticketSuporteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketSuporte> getById(@PathVariable String id) {
        TicketSuporte ticket = ticketSuporteService.findById(id);
        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/user/{userId}")
    public List<TicketSuporte> getByUserId(@PathVariable String userId) {
        return ticketSuporteService.findByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody TicketSuporte ticket) {
        try {
            TicketSuporte created = ticketSuporteService.create(ticket);
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @Validated @RequestBody TicketSuporte ticket) {
        try {
            TicketSuporte updated = ticketSuporteService.update(id, ticket);
            if (updated == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            ticketSuporteService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
