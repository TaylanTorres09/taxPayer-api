package br.com.api.taxpayer.taxpayer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.taxpayer.taxpayer.models.Company;
import br.com.api.taxpayer.taxpayer.services.CompanyService;


@RestController
public class HomeController {

    @Autowired
    private CompanyService companyService;

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
