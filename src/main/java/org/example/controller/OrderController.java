package org.example.controller;

import org.example.dao.ProductsDAO;
import org.example.model.Product;
import org.example.model.ProductSlot;
import org.example.model.ProductSlotMapper;
import org.example.model.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final ProductsDAO productsDAO;

    @Autowired
    public OrderController(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }

    @GetMapping()
    public String index(Model model) {
        List<ProductSlot> productSlots = productsDAO.getProductSlots();
        List<ProductSlotMapper> mappers = new ArrayList<>();
        for (ProductSlot productSlot : productSlots) {
            Product product = productsDAO.getProduct(productSlot.getProduct_id());
            Slot slot = productsDAO.getSlot(productSlot.getSlot_id());
            mappers.add(new ProductSlotMapper(product, slot));
        }
        model.addAttribute("mappers", mappers);
        return "products/my_order";
    }
}
