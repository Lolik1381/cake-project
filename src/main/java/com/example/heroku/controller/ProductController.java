package com.example.heroku.controller;

import com.example.heroku.model.Product;
import com.example.heroku.model.ResponsePage;
import com.example.heroku.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/findAll")
    public @ResponseBody ResponsePage<Product> findAll(@RequestParam(required = false) String searchText, Pageable pageable) {
        return productService.getAllProducts(searchText, pageable);
    }

    @GetMapping("/{id}")
    public @ResponseBody Product findById(@PathVariable("id") String id) {
        return productService.findById(id);
    }
}