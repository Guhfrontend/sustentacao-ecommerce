package br.com.gustavo.sustentacao.ecommerce.service.impl;

import br.com.gustavo.sustentacao.ecommerce.domain.TicketSuporte;
import br.com.gustavo.sustentacao.ecommerce.repository.TicketSuporteRepository;
import br.com.gustavo.sustentacao.ecommerce.repository.UserRepository;
import br.com.gustavo.sustentacao.ecommerce.service.TicketSuporteService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;


@Service
public class TicketSuporteServiceImpl implements TicketSuporteService {


    private final TicketSuporteRepository ticketSuporteRepository;

    private final UserRepository userRepository;

    public TicketSuporteServiceImpl(TicketSuporteRepository ticketSuporteRepository, UserRepository userRepository) {
        this.ticketSuporteRepository = ticketSuporteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TicketSuporte> findAll() {
        return ticketSuporteRepository.findAll();
    }

    @Override
    public TicketSuporte findById(String id) {
        return ticketSuporteRepository.findById(id).orElse(null);
    }

    @Override
    public List<TicketSuporte> findByUserId(String userId) {
        return ticketSuporteRepository.findByUserId(userId);
    }

    @Override
    public TicketSuporte create(TicketSuporte ticket) {
        validacaoTicket(ticket);
        if (ticket.getUser() == null || ticket.getUser().getId() == null ||
            !userRepository.existsById(ticket.getUser().getId())) {
            throw new IllegalArgumentException("Usuário não encontrado para o ticket");
        }
        return ticketSuporteRepository.save(ticket);
    }

    @Override
    public TicketSuporte update(String id, TicketSuporte ticket) {
        if (!ticketSuporteRepository.existsById(id)) {
            return null;
        }
        validacaoTicket(ticket);
        ticket.setId(id);
        return ticketSuporteRepository.save(ticket);
    }

    @Override
    public void delete(String id) {
        if (!ticketSuporteRepository.existsById(id)) {
            throw new IllegalArgumentException("Ticket não encontrado");
        }
        ticketSuporteRepository.deleteById(id);
    }

    private void validacaoTicket(TicketSuporte ticket) {
        if (!StringUtils.hasText(ticket.getTitulo())) {
            throw new IllegalArgumentException("Título é obrigatório");
        }
        if (!StringUtils.hasText(ticket.getDescricao())) {
            throw new IllegalArgumentException("Descrição é obrigatória");
        }
        if (ticket.getStatus() == null) {
            throw new IllegalArgumentException("Status é obrigatório");
        }
        if (ticket.getPrioridade() == null) {
            throw new IllegalArgumentException("Prioridade é obrigatória");
        }
    }
}

