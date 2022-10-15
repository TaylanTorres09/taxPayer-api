package br.com.api.taxpayer.taxpayer.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String allRoutes() {
        String[] message = {
            "Rotas da aplicação",
            "Cadastrar pessoa física: /individual/register",
            "Cadastrar pessoa jurídica: /company/register",
        };
        StringBuilder sb = new StringBuilder();
        for (String line: message) {
            sb.append(line).append(System.lineSeparator());
        }
        String msg = sb.toString();
        return msg;
    }

}
