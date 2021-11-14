package com.example.heroku.controller;

import com.example.heroku.model.ProductType;
import com.example.heroku.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/productType")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @GetMapping("/all")
    public @ResponseBody List<ProductType> findAll() {
        return productTypeService.findAll();
    }

    @GetMapping("/findByName")
    public @ResponseBody ProductType findByName(@RequestParam String name) {
        return productTypeService.findByName(name);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ProductType create(@RequestBody ProductType productType) {
        return productTypeService.create(productType);
    }
}