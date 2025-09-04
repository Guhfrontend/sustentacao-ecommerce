package br.com.gustavo.sustentacao.ecommerce.domain;

import br.com.gustavo.sustentacao.ecommerce.Enum.PrioridadeTicket;
import br.com.gustavo.sustentacao.ecommerce.Enum.StatusTicket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Objects;

@Document(collection = "tickets_suporte")
public class TicketSuporte implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Schema(description = "ID do ticket", example = "507f1f77bcf86cd799439011")
    private String id;

    @NotBlank
    @Schema(description = "Título do ticket", example = "Problema no pagamento")
    private String titulo;

    @NotBlank
    @Schema(description = "Descrição detalhada do problema", example = "Não consigo finalizar a compra com cartão de crédito.")
    private String descricao;

    @NotNull
    @Schema(description = "Status do ticket", example = "ABERTO")
    private StatusTicket status;

    @NotNull
    @Schema(description = "Prioridade do ticket", example = "ALTA")
    private PrioridadeTicket prioridade;

    @DBRef(lazy = true)
    @Schema(description = "Usuário associado ao ticket")
    private User user;

    public TicketSuporte() {
    }

    public TicketSuporte(String id, String titulo, String descricao, StatusTicket status, PrioridadeTicket prioridade, User user) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.prioridade = prioridade;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusTicket getStatus() {
        return status;
    }

    public void setStatus(StatusTicket status) {
        this.status = status;
    }

    public PrioridadeTicket getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeTicket prioridade) {
        this.prioridade = prioridade;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TicketSuporte that = (TicketSuporte) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TicketSuporte{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                ", prioridade=" + prioridade +
                ", user=" + (user != null ? user.getId() : null) +
                '}';
    }
}
