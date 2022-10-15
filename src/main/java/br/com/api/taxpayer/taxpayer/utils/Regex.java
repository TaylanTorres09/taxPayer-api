package br.com.api.taxpayer.taxpayer.utils;

import org.springframework.stereotype.Component;

@Component
// Classe com regex
public class Regex {
    
    public String regexEmail() {
        String[] message = {
            "Só é permitido: ",
            "Valores numéricos de 0 a 9.",
            "Letras maiúsculas e minúsculas de a a z são permitidas.",
            "São permitidos sublinhado '_', hífen '-' e ponto '.'",
            "O ponto não é permitido no início e no final da parte local.",
            "Pontos consecutivos não são permitidos.",
            "Para a parte local, são permitidos no máximo 64 caracteres."
        };
        StringBuilder sb = new StringBuilder();
        for (String line: message) {
            sb.append(line).append(System.lineSeparator());
        }
        String msg = sb.toString();
        return msg;
    }
}
