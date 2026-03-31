package com.clickpass.experiencia.controller;

import com.clickpass.experiencia.model.Passagem;
import com.clickpass.experiencia.model.enums.StatusPassagem;
import com.clickpass.experiencia.repository.PassagemRepository;
import com.clickpass.experiencia.service.RoteamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/passagens")
public class PassagemController {

    @Autowired
    private PassagemRepository passagemRepository;

    @Autowired
    private RoteamentoService roteamentoService;

    // Rota da Fase 2: Atualização via Biometria
    @PutMapping("/{id}/status")
    public ResponseEntity<String> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusPassagem novoStatus) {

        Optional<Passagem> passagemOpt = passagemRepository.findById(id);

        if (passagemOpt.isPresent()) {
            Passagem passagem = passagemOpt.get();
            passagem.setStatus(novoStatus);
            passagemRepository.save(passagem);
            return ResponseEntity.ok("Status da passagem atualizado para: " + novoStatus);
        }
        return ResponseEntity.notFound().build();
    }

    // Rota da Fase 3: Lógica de Orquestração e Camadas
    @GetMapping("/{id}/tempo-embarque")
    public ResponseEntity<String> calcularNotificacaoEmbarque(
            @PathVariable Long id,
            @RequestParam String localizacaoPassageiro) {

        // O destino simulado é a Plataforma 12 conforme o PDF
        int tempoCaminhada = roteamentoService.calcularTempoAtePortao(localizacaoPassageiro, "PLATAFORMA_12");

        String mensagem = "Local atual: " + localizacaoPassageiro +
                ". Tempo ate o portao: " + tempoCaminhada + " min. " +
                (tempoCaminhada > 3 ? "CAMADA 1: Inicie o deslocamento!" : "CAMADA 2: Aguarde chamada próxima.");

        return ResponseEntity.ok(mensagem);
    }
}