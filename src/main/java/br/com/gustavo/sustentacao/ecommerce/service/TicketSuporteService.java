package br.com.gustavo.sustentacao.ecommerce.service;

import br.com.gustavo.sustentacao.ecommerce.domain.TicketSuporte;
import java.util.List;

public interface TicketSuporteService {

    List<TicketSuporte> findAll();

    TicketSuporte findById(String id);

    List<TicketSuporte> findByUserId(String userId);

    TicketSuporte create(TicketSuporte ticket);

    TicketSuporte update(String id, TicketSuporte ticket);

    void delete(String id);
}

