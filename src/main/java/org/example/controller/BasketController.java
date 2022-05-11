package org.example.controller;

import org.example.dao.ProductsDAO;
import org.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Date;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
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
        if (date != null && startSlot != null && endSlot != null) {
            List<Transport> transports = productsDAO.findTransportsByCity(Integer.parseInt(city));
            Transport transport = transports.stream()
                    .findAny().orElse(null);
            productsDAO.addSlot(new Slot(0, (int)transport.getId(), Date.valueOf(date), Time.valueOf(startSlot.concat(":00")), Time.valueOf(endSlot.concat(":00"))));
            List<Basket> baskets = productsDAO.getBaskets();
            List<Slot> slots = productsDAO.getSlots();
            Comparator<Slot> comparator = (x1, x2) -> (int)x1.getId() - (int)x2.getId();
            Slot slot = slots.stream().sorted(comparator.reversed()).findAny().orElse(null);
            baskets.forEach(x -> productsDAO.addProductToSlot((int)slot.getId(), x.getProductId()));
            productsDAO.removeAllBasket();
        }
        return "redirect:/baskets";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productsDAO.deleteBasket(id);
        return "redirect:/baskets";
    }
}
