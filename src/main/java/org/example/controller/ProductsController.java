package org.example.controller;

import org.example.dao.ProductsDAO;
import org.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private final ProductsDAO productsDAO;

    @Autowired
    public ProductsController(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("products", productsDAO.getProducts());
        return "products/products";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productsDAO.getProduct(id));
        return "products/show";
    }

    @PostMapping("/{id}")
    public String update(@RequestParam int quantity, @PathVariable("id") int id) {
        productsDAO.addToBasket(id, quantity);
        return "redirect:/products";
    }
}
