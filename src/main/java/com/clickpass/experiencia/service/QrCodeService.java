package com.clickpass.experiencia.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

@Service
public class QrCodeService {

    public String gerarQrCodeBase64(String conteudo, int largura, int altura) {
        try {
            // 1. O ZXing Core apenas calcula onde ficam os pontos pretos e brancos
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(conteudo, BarcodeFormat.QR_CODE, largura, altura);

            // 2. WORKAROUND: Usamos o Java nativo para criar uma imagem em branco
            BufferedImage image = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

            // 3. Pintamos a imagem pixel por pixel (Preto = 0xFF000000, Branco = 0xFFFFFFFF)
            for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            // 4. Transformamos a imagem em PNG e depois em Base64 para o Frontend
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", outputStream);

            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (Exception e) {
            return "Erro ao gerar QR Code: " + e.getMessage();
        }
    }
}