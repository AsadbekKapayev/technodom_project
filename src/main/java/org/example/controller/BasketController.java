package org.example.controller;

import org.example.dao.ProductsDAO;
import org.example.model.Basket;
import org.example.model.Product;
import org.example.model.ProductBasket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/baskets")
public class BasketController {
    private final ProductsDAO productsDAO;

    @Autowired
    public BasketController(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }

    @GetMapping()
    public String index(Model model) {
        List<Basket> baskets = productsDAO.getBaskets();
        List<ProductBasket> productBaskets = baskets.stream()
                .map(x -> new ProductBasket(x.getId(), productsDAO.getProduct(x.getProductId()), x.getQuantity()))
                .collect(Collectors.toList());
        model.addAttribute("baskets", productBaskets);
        model.addAttribute("cities", productsDAO.getCities());
        return "products/basket";
    }

    @PostMapping()
    public String createOrder(@RequestParam String city,
                              @RequestParam String date,
                              @RequestParam String startSlot,
                              @RequestParam String endSlot) {
        System.out.println(date.isEmpty());
        System.out.println("hello");
        return null;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productsDAO.deleteBasket(id);
        return "redirect:/baskets";
    }
}
