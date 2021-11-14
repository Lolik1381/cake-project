package com.example.heroku.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainController {

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("/admin/index")
    String adminIndex() {
        return "admin/index";
    }

    @GetMapping("/product/index/{id}")
    public String getProductIndex(@PathVariable("id") String id) {
        return "product/index";
    }
}