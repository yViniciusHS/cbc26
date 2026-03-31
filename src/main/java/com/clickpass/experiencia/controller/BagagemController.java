package com.clickpass.experiencia.controller;

import com.clickpass.experiencia.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bagagens")
public class BagagemController {

    @Autowired
    private QrCodeService qrCodeService;

    @GetMapping("/gerar-etiqueta/{passagemId}")
    public String gerarEtiqueta(@PathVariable Long passagemId) {
        String dadosEtiqueta = "CLICKPASS-BAGGAGE-ID-" + passagemId;

        // Agora passamos a largura e altura exigidas pelo seu novo QrCodeService
        return qrCodeService.gerarQrCodeBase64(dadosEtiqueta, 250, 250);
    }
}