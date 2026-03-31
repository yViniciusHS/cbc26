package com.clickpass.experiencia.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RoteamentoService {

    // Mapa representativo da rodoviária: Origem -> (Destino, Tempo em minutos)
    private final Map<String, Map<String, Integer>> grafo = new HashMap<>();

    public RoteamentoService() {
        // Definindo os pontos da rodoviária conforme o PDF [cite: 30, 82, 88]
        adicionarCaminho("ENTRADA", "PRACA_ALIMENTACAO", 2);
        adicionarCaminho("PRACA_ALIMENTACAO", "PLATAFORMA_12", 3);
        adicionarCaminho("ENTRADA", "PLATAFORMA_12", 5);
        adicionarCaminho("TOTEM_BAGAGEM", "PLATAFORMA_12", 4);
    }

    private void adicionarCaminho(String origem, String destino, int tempo) {
        grafo.computeIfAbsent(origem, k -> new HashMap<>()).put(destino, tempo);
        grafo.computeIfAbsent(destino, k -> new HashMap<>()).put(origem, tempo);
    }

    // Algoritmo de Dijkstra simplificado para o protótipo [cite: 42, 81]
    public int calcularTempoAtePortao(String localAtual, String destino) {
        if (localAtual.equals(destino)) return 0;

        // No protótipo, buscamos o peso direto ou caminhos simples
        return grafo.getOrDefault(localAtual, new HashMap<>()).getOrDefault(destino, 99);
    }
}