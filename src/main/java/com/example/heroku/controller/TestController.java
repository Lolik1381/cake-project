package com.example.heroku.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.example.heroku.entity.CompanyEntity;
import com.example.heroku.repository.CompanyRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {

    private final CompanyRepository companyRepository;

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("/db")
    public String test(Map<String, Object> model) {
        List<CompanyEntity> all = companyRepository.findAll();

        model.put("records", all.stream().map(CompanyEntity::getBrandingName).collect(Collectors.toList()));
        return "db";
    }

    @GetMapping("/addRow")
    public String addRow() {
        CompanyEntity companyEntity = CompanyEntity.builder()
                .prmId(22L)
                .brandingName("hell")
                .build();

        companyRepository.save(companyEntity);
        return "db";
    }
}