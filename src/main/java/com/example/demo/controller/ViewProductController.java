package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/view/products")
public class ViewProductController {
    private final ProductService productService;

    @Autowired
    ViewProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ModelAndView getProducts(final ModelAndView modelAndView) {
        modelAndView.addObject("products", productService.getAll());
        modelAndView.setViewName("products");
        return modelAndView;
    }
}
