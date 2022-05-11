package org.example.controller;

import org.example.dao.ProductsDAO;
import org.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Date;
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
    public String createOrder(@RequestParam int city,
                              @RequestParam Date date,
                              @RequestParam Time startSlot,
                              @RequestParam Time endSlot) {
        if (date != null && startSlot != null && endSlot != null) {
            List<Transport> transports = productsDAO.findTransportsByCity(city);
            Transport transport = transports.stream()
                    .findAny().orElse(null);
            productsDAO.addSlot(new Slot(0, (int)transport.getId(), date, startSlot, endSlot));
        }
        return "redirect:/baskets";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productsDAO.deleteBasket(id);
        return "redirect:/baskets";
    }
}
