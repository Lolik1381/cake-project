package com.example.heroku.controller;

import com.example.heroku.model.Product;
import com.example.heroku.model.ProductType;
import com.example.heroku.model.ResponsePage;
import com.example.heroku.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/findAll")
    public @ResponseBody ResponsePage<Product> findAll(@RequestParam(required = false) String searchText,
                                                       @RequestParam(required = false) List<String> productTypes,
                                                       Pageable pageable) {
        return productService.getAllProducts(searchText, productTypes, pageable);
    }

    @GetMapping("/{id}")
    public @ResponseBody Product findById(@PathVariable("id") String id) {
        return productService.findById(id);
    }

    @PostMapping("/create")
    public @ResponseBody Product create(@RequestBody Product product) {
        return productService.create(product);
    }
}