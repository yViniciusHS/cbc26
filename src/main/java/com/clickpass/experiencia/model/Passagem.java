package com.clickpass.experiencia.model;

import com.clickpass.experiencia.model.enums.StatusPassagem;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_passagem")
public class Passagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "viagem_id")
    private Viagem viagem;

    @Enumerated(EnumType.STRING)
    private StatusPassagem status = StatusPassagem.COMPRADO;

    @OneToMany(mappedBy = "passagem", cascade = CascadeType.ALL)
    private List<Bagagem> bagagens;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Viagem getViagem() { return viagem; }
    public void setViagem(Viagem viagem) { this.viagem = viagem; }
    public StatusPassagem getStatus() { return status; }
    public void setStatus(StatusPassagem status) { this.status = status; }
}