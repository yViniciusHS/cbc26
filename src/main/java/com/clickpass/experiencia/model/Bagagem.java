package com.clickpass.experiencia.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_bagagem")
public class Bagagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String qrCodeHash;
    private Double pesoEstimado;

    @ManyToOne
    @JoinColumn(name = "passagem_id")
    private Passagem passagem;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getQrCodeHash() { return qrCodeHash; }
    public void setQrCodeHash(String qrCodeHash) { this.qrCodeHash = qrCodeHash; }
    public Double getPesoEstimado() { return pesoEstimado; }
    public void setPesoEstimado(Double pesoEstimado) { this.pesoEstimado = pesoEstimado; }
    public Passagem getPassagem() { return passagem; }
    public void setPassagem(Passagem passagem) { this.passagem = passagem; }
}