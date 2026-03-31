package com.clickpass.experiencia.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_viagem")
public class Viagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String origem;
    private String destino;
    private LocalDateTime horarioPartida;
    private String plataforma;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }
    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }
    public LocalDateTime getHorarioPartida() { return horarioPartida; }
    public void setHorarioPartida(LocalDateTime horarioPartida) { this.horarioPartida = horarioPartida; }
    public String getPlataforma() { return plataforma; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }
}